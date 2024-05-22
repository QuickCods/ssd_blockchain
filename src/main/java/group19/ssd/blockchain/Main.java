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

            for (int i = 1; i <= 2; i++) {
                // Create a sample auction
                byte[] itemId = Base64.getEncoder().encode(("Item" + i).getBytes());
                long timeout = System.currentTimeMillis() + 5000; // Auction expires in 10 seconds

                // Create hash for the auction (simplistically using itemID here)
                String auctionData = Base64.getEncoder().encodeToString(itemId) + timeout + Base64.getEncoder().encodeToString(sellerWallet.getPublicKey().getEncoded());
                byte[] auctionHash = StringUtil.applySha256(auctionData).getBytes();
                byte[] auctionSignature = StringUtil.applyECDSASig(sellerWallet.getPrivateKey(), auctionData);

                Auction auction = new Auction(itemId, timeout, auctionHash, auctionSignature, sellerWallet);
                auctionManager.startAuction(auction, sellerWallet);

                /*
                // Testing End auction
                // Simulate waiting for auction to end
                try {
                    Thread.sleep(6000);  // Wait 6 seconds to pass the auction timeout
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // End the auction
                auctionManager.endAuction(auction.getHash(), sellerWallet);
*/

                //adding Bids
                for(int p = 1; p <= 9; p++) {
                    // Place a bid
                    byte[] transactionId = ("tx_id"+p).getBytes(); // This should be generated
                    long bidTimestamp = System.currentTimeMillis();
                    Bid bid = new Bid(transactionId, auction.getHash(), bidTimestamp);
                    auctionManager.placeBid(bid, bidderWallet);
                }
            }

            // Check Blockchain's transaction list size
            if (Blockchain.pendingList.size() > 4) {
                System.out.println("All transactions were successfully added.\n");
            } else {
                System.out.println("Expected 5 transactions but found: " + Blockchain.pendingList.size());
            }


            // Verify blockchain is valid after adding a block
            if (Blockchain.isChainValid()) {
                System.out.println("Blockchain is valid after adding auctions.");
            } else {
                System.out.println("Blockchain validation failed.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}