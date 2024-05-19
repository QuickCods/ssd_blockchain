package group19.ssd.blockchain;

import java.util.*;

import group19.ssd.blockchain.transactions.Wallet;
import group19.ssd.blockchain.auctions.Auction;
import group19.ssd.blockchain.auctions.AuctionManager;
import group19.ssd.blockchain.auctions.Bid;
import group19.ssd.blockchain.utils.StringUtil;

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