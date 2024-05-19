package group19.ssd.blockchain.auctions;

import org.example.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class AuctionManager {
    private final List<Pair<Auction, List<Bid>>> auctions = new ArrayList<>();

    public void addAuction(Auction auction) {
        auctions.add(new Pair<>(auction, new ArrayList<>()));  // Add auction with an empty list of bids
    }

    public void addBidToAuction(Bid bid, byte[] auctionHash) {
        for (Pair<Auction, List<Bid>> pair : auctions) {
            if (java.util.Arrays.equals(pair.getFirst().getHash(), auctionHash)) {
                pair.getSecond().add(bid);
                break;
            }
        }
    }

    public Auction findAuctionByHash(byte[] hash) {
        for (Pair<Auction, List<Bid>> pair : auctions) {
            if (java.util.Arrays.equals(pair.getFirst().getHash(), hash)) {
                return pair.getFirst();
            }
        }
        System.out.println("auction not found");
        return null;  // Auction not found
    }

    public List<Pair<Auction, List<Bid>>> getAuctions() {
        return auctions;
    }
}
