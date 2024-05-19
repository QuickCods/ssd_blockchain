package group19.ssd.blockchain;

import java.security.KeyPair;
import java.util.*;

import com.google.gson.GsonBuilder;
import org.example.Transactions.Transaction;
import org.example.Transactions.Wallet;
import org.example.auctions.Auction;
import org.example.auctions.AuctionManager;
import org.example.auctions.Bid;
import org.example.utils.StringUtil;

public class Main {
    public static void main(String[] args) {
        // Initialize the AuctionManager
        AuctionManager auctionManager = new AuctionManager();
        Bid.setAuctionManager(auctionManager);

        // Generate KeyPairs (normally you'd handle exceptions here)
        try {
            // Sample Wallets for sellers and bidders
            Wallet sellerWallet = new Wallet();
            Wallet bidderWallet = new Wallet();

            // Create a sample auction
            byte[] itemId = Base64.getEncoder().encode("Item1".getBytes());
            long timeout = System.currentTimeMillis() + 1000; // Auction expires in 10 seconds

            // Create hash for the auction (simplistically using itemID here)
            String auctionData = Base64.getEncoder().encodeToString(itemId) + timeout + Base64.getEncoder().encodeToString(sellerWallet.getPublicKey().getEncoded());
            byte[] auctionHash = StringUtil.applySha256(auctionData).getBytes();
            byte[] auctionSignature = StringUtil.applyECDSASig(sellerWallet.getPrivateKey(), auctionData).getBytes();

            Auction auction = new Auction(itemId, timeout, sellerWallet.getPublicKey().getEncoded(), auctionHash, auctionSignature);
            auctionManager.addAuction(auction);

            // Adding a valid bid
            long bidTimestamp = System.currentTimeMillis();
            Bid validBid = new Bid(Base64.getEncoder().encode("Transaction1".getBytes()), auctionHash, bidderWallet.getPublicKey().getEncoded(), bidTimestamp);
            auctionManager.addBidToAuction(validBid, auctionHash);

            // Check validity of the auction and bid
            boolean isAuctionValid = auction.isValidAuction();
            boolean isBidValid = validBid.isValidBid();

            System.out.println("Is Auction Valid? " + isAuctionValid);
            System.out.println("Is Bid Valid? " + isBidValid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}