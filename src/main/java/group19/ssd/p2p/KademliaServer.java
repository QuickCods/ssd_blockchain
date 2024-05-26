package group19.ssd.p2p;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import group19.ssd.blockchain.BCConverter;
import group19.ssd.blockchain.Blockchain;
import group19.ssd.blockchain.GRPCConverter;
import group19.ssd.p2p.grpc.*;
import group19.ssd.p2p.grpc.Status;
import io.grpc.*;
import io.grpc.internal.ServerImpl;
import io.grpc.stub.StreamObserver;

public class KademliaServer {
    private static final Logger logger = Logger.getLogger(KademliaServer.class.getName());
    private Server server;
    public String ip;
    public int port;
    PeerImplementation broker = new PeerImplementation();
    private final Set<String> activeClients = new HashSet<>();

    public KademliaServer(String ip, int port) {
        this.ip = ip + ":" + port;
        this.port = port;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(this.port)
                .addService(broker)
                .intercept(new ClientInterceptor(this))
                .build()
                .start();
        logger.info("Server started, listening on " + ip);

        broker = new PeerImplementation();

        Runnable task = () -> {

        };

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    KademliaServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("*** server shut down");


            }
        });
    }
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(15, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private synchronized void logClientConnect(String clientAddress) {
        if (activeClients.add(clientAddress)) {
            logger.info("Client connected: " + clientAddress);
        }
    }

    private synchronized void logClientDisconnect(String clientAddress) {
        if (activeClients.remove(clientAddress)) {
            logger.info("Client disconnected: " + clientAddress);
        }
    }

    class PeerImplementation extends PeerGrpc.PeerImplBase{
        @Override
        public void ping(Ping request, StreamObserver<Pong> responseObserver) {
            KademliaClient.kbucket.checkNode(new Node(request.getId(), request.getIp(), request.getPort()), request.getProof(), request.getPubKey());
            BlockChain blockchain = GRPCConverter.mkBlockChain(KademliaClient.blockchain);
            responseObserver.onNext(Pong.newBuilder().setPong(true).setBlockchain(blockchain).build());
            responseObserver.onCompleted();
        }

        @Override
        public void broadcastBlock(Block request, StreamObserver<Status> responseObserver) {
            group19.ssd.blockchain.Block new_block = BCConverter.mkBlock(request);
            if(!(KademliaClient.blockchain.getLatestBlock().hash).equals(new_block.getHash())) {
                KademliaClient.blockchain.addBlock(new_block);
                KademliaClient.kbucket.getNode(request.getNodeId()).addSuccessfullInterations();
                KademliaClient.blockchain.addBlock(new_block);
                KademliaClient.ledger.updateLedger(new_block);
                KademliaClient.shareBlock(new_block, request.getNodeId());
            }else{
                responseObserver.onNext(Status.newBuilder().setStatus("Block already exists").build());
                responseObserver.onCompleted();
                return;
            }
        }

        @Override
        public void broadcastTransaction(Transaction request, StreamObserver<Status> responseObserver){
            group19.ssd.blockchain.transactions.Transaction new_transaction = BCConverter.mkTransaction(request);
            if(Blockchain.pendingList.isEmpty()){
                KademliaClient.blockchain.pendingList.add(new_transaction);
            } else if(!KademliaClient.blockchain.pendingList.get(KademliaClient.blockchain.pendingList.size() - 1).equals(new_transaction)){
                KademliaClient.blockchain.pendingList.add(new_transaction);
            } else{
                responseObserver.onNext(Status.newBuilder().setStatus("Transaction already exists").build());
                responseObserver.onCompleted();
                return;
            }
            responseObserver.onNext(Status.newBuilder().setStatus("Sent").build());
            responseObserver.onCompleted();
        }

        @Override
        public void broadcastBlockchain(BlockChain request, StreamObserver<Status> responseObserver){
            super.broadcastBlockchain(request, responseObserver);
        }

        @Override
        public void findNodes(FindNode request, StreamObserver<KBucket_GRPC> responseObserver){
            if(KademliaClient.kbucket.checkNode(new Node(request.getId(), request.getIp(), request.getPort()), request.getProof(), request.getPubKey())){
                responseObserver.onNext(NodeSerializable.KBucket_to_GRPC(KademliaClient.kbucket.getNeighboursByDistance(request.getTargetId(), KademliaClient.kbucket.identifiedLast)));
            }
            responseObserver.onCompleted();
        }
    }

    class ClientInterceptor implements ServerInterceptor {
        private final KademliaServer server;

        public ClientInterceptor(KademliaServer server) {
            this.server = server;
        }

        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
                ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            String clientAddress = call.getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR).toString();
            server.logClientConnect(clientAddress);
            ServerCall.Listener<ReqT> listener = next.startCall(call, headers);

            return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
                @Override
                public void onComplete() {
                    server.logClientDisconnect(clientAddress);
                    super.onComplete();
                }

                @Override
                public void onCancel() {
                    server.logClientDisconnect(clientAddress);
                    super.onCancel();
                }
            };
        }
    }
}

