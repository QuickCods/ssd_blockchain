package group19.ssd.blockchain.transactions;

import group19.ssd.p2p.KademliaClient;
import group19.ssd.blockchain.auctions.Auction;
import group19.ssd.blockchain.auctions.Bid;
import group19.ssd.blockchain.Blockchain;
import group19.ssd.blockchain.utils.Pair;
import group19.ssd.blockchain.utils.StringUtil;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    // bids done by this wallet with the auction they belong to
    private final List<Pair<Bid, Auction>> bids = new ArrayList<>();
    // auctions started by this wallet with the bids they have received
    private final List<Pair<Auction, List<Bid>>> auctions = new ArrayList<>();

    private static Blockchain blockchain;


    public Wallet(PublicKey publicKey, PrivateKey privateKey) {
        this.privateKey=privateKey;
        this.publicKey=publicKey;
    }


    public Wallet(){
        try {
            KeyPair keypair = StringUtil.generateKeyPair();
            privateKey = keypair.getPrivate();
            publicKey = keypair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to initialize Wallet: Algorithm not found", e);
        }
    }

    public static void setBlockchain(Blockchain blockchain){
        Wallet.blockchain = blockchain;
    }

    //Getters
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }


}
