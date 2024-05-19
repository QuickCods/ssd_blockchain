package group19.ssd.p2p;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;
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

    public void start() throws IOException{
        server = ServerBuilder.forPort(this.port)
                .addService(broker)
                .build()
                .start();
        logger.info("Server started, listening on " + ip);

        broker = new PeerImplementation();

        Runnable task = () -> {

        };
    }
}
