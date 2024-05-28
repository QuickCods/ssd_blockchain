package group19.ssd.p2p;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import group19.ssd.blockchain.BCConverter;
import group19.ssd.blockchain.Blockchain;
import group19.ssd.blockchain.GRPCConverter;
import group19.ssd.p2p.grpc.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.internal.ServerImpl;
import io.grpc.stub.StreamObserver;

public class KademliaServer {
    private static final Logger logger = Logger.getLogger(KademliaServer.class.getName());
    private Server server;
    public String ip;
    public int port;
    PeerImplementation broker = new PeerImplementation();

    public KademliaServer(String ip, int port) {
        this.ip = ip + ":" + port;
        this.port = port;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(this.port)
                .addService(broker)
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

    class PeerImplementation extends PeerGrpc.PeerImplBase{
        @Override
        public void ping(Ping request, StreamObserver<Pong> responseObserver) {
            KademliaClient.kbucket.checkNode(new Node(request.getId(), request.getIp(), request.getPort()), request.getProof(), request.getPubKey());
            BlockChain blockchain = GRPCConverter.mkBlockChain(KademliaClient.blockchain);
            try {
                responseObserver.onNext(Pong.newBuilder().setPong(true).setBlockchain(blockchain).build());
            } catch (Exception e) {
                e.printStackTrace();
                responseObserver.onError(e);
            }
            responseObserver.onCompleted();
        }

        @Override
        public void broadcastBlock(Block request, StreamObserver<Status> responseObserver) {
            try {
                group19.ssd.blockchain.Block newBlock = BCConverter.mkBlock(request);

                // Get the current blockchain length
                int initialLength = KademliaClient.blockchain.getChain().size();

                // Add the block
                KademliaClient.blockchain.addBlock(newBlock);

                // Get the new blockchain length
                int newLength = KademliaClient.blockchain.getChain().size();

                // Check if the block was successfully added by comparing the lengths
                if (newLength > initialLength) {
                    // Successfully added block
                    responseObserver.onNext(Status.newBuilder().setStatus("success").build());
                } else {
                    responseObserver.onNext(Status.newBuilder().setStatus("failure").build());
                }
            } catch (Exception e) {
                e.printStackTrace();
                responseObserver.onNext(Status.newBuilder().setStatus("error").build());
            } finally {
                responseObserver.onCompleted();
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
        public void broadcastBlockchain(BlockChain request, StreamObserver<Status> responseObserver) {
            try {
                group19.ssd.blockchain.Blockchain receivedBlockchain = BCConverter.mkBlockchain(request);

                if (receivedBlockchain != null && receivedBlockchain.isChainValid() &&
                        receivedBlockchain.getChain().size() > KademliaClient.blockchain.getChain().size()) {
                    KademliaClient.blockchain = receivedBlockchain;
                    KademliaClient.ledger.restartLedger();
                    responseObserver.onNext(Status.newBuilder().setStatus("success").build());
                } else {
                    responseObserver.onNext(Status.newBuilder().setStatus("failure").build());
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
                responseObserver.onNext(Status.newBuilder().setStatus("error").build());
            } finally {
                responseObserver.onCompleted();
            }
        }

        @Override
        public void findNodes(FindNode request, StreamObserver<KBucket_GRPC> responseObserver){
            if(KademliaClient.kbucket.checkNode(new Node(request.getId(), request.getIp(), request.getPort()), request.getProof(), request.getPubKey())){
                responseObserver.onNext(NodeSerializable.KBucket_to_GRPC(KademliaClient.kbucket.getNeighboursByDistance(request.getTargetId(), KademliaClient.kbucket.identifiedLast)));
            }
            responseObserver.onCompleted();
        }
    }
}