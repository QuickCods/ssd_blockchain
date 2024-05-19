package group19.ssd.blockchain.transactions;


import org.example.auctions.Auction;
import org.example.auctions.Bid;
import org.example.Blockchain;
import org.example.utils.Pair;
import org.example.utils.StringUtil;

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


    public Wallet() throws NoSuchAlgorithmException {
        KeyPair keypair = StringUtil.generateKeyPair();
         privateKey = keypair.getPrivate();
         publicKey = keypair.getPublic();
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
