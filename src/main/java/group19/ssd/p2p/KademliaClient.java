package group19.ssd.p2p;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import group19.ssd.miscellaneous.Configuration;
import group19.ssd.miscellaneous.Miscellaneous;

public class KademliaClient {
    public static String id;
    public static String ip;
    public static int port;
    public static int proof;
    public static String publicKey;
    public static KBucket kbucket = new KBucket();
    // public static Wallet wallet = new Wallet();
    // public static Blockchain blockchain;
    // public static Ledger ledger;
    // public static MineBlockThread mineBlockThread = new MineBlockThread();
    // private static boolean alreadyRunningMineBlockThread = false;
    // public static KeepAliveThread keepAliveThread = new KeepAliveThread();

    public String getHash() {
        return id;
    }

    public KademliaClient(){

    }

    // public void setup(int node_port, String node_ip) throws InvalidKeySpecException, NoSuchAlgorithmException{}

    private static String getNodeId(){
        return calculateHash(KademliaClient.ip, KademliaClient.proof,KademliaClient.port,KademliaClient.publicKey);
    }

    private static String calculateHash(String ip, int proof,int port, String publicKey){
        return Miscellaneous.applyEncryption(ip + port + proof + publicKey);
    }

    public static String calculateNeighbourHash(String ip, int port, int proof, String publicKey){
        return Miscellaneous.applyEncryption(ip + port + proof + publicKey);
    }

    // public static void evaluateTrust(){}

    // public static void shareBlock(Block block, String sender){}

    // public static void shareTransaction(Transaction transaction, String sender){}

    // public static void startMining() {
    //     if (!alreadyRunningMineBlockThread) {
    //         alreadyRunningMineBlockThread = true;
    //         KademliaClient.mineBlockThread.start();
    //     } else {
    //         System.out.println("Already Mining");
    //     }
    // }

    // public static void startPinging(){
    //     KademliaClient.keepAliveThread.start();
    // }

    // private static class MineBlockThread extends Thread{
    //     public MineBlockThread(){}

    // }

    // private static class KeepAliveThread extends Thread{
    //     public KeepAliveThread(){}

    // }
}
