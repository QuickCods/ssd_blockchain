package group19.ssd.p2p;

import java.io.IOException;
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
    private static final Logger logger = Logger.getLogger(KademliaServer.getName());
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
    private void stop() throws InterruptedException {
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
            KademliaClient.kbucket.checkNodeExistence(new Node(request.getId(), request.getIp(), request.getPort(), request.getProof(), request.getPubKey()));
            Blockchain blockchain = GRPCConverter.mkBlockchain(KademliaClient.blockchain);
            responseObserver.onNext(Pong.newBuilder().setPong(true).setBlockchain(blockchain).build());
            responseObserver.onCompleted();
        }

        @Override
        public void broadcastBlock(Block request, StreamObserver<Status> responseObserver) {
            group19.ssd.blockchain.Block new_block = BCConverter.mkblock(r)
        }
    }
}

