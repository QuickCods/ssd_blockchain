package group19.ssd.blockchain.auctions;

import group19.ssd.blockchain.transactions.Wallet;
import group19.ssd.blockchain.utils.StringUtil;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;

public class Auction {

    private final byte[] itemId;
    private final long timeout;

    private  final byte[] sellerPublicKey;
    private final byte[] hash;
    private final byte[] signature;

    public Auction(byte[] itemId, long timeout, byte[] sellerPublicKey, byte[] hash, byte[] signature) {
        this.itemId = itemId;
        this.timeout = timeout;
        this.sellerPublicKey = sellerPublicKey;
        this.hash = hash;
        this.signature = signature;
    }

    public Auction(byte[] itemId, long timeout, byte[] hash, byte[] signature, Wallet seller) {
        this.itemId = itemId;
        this.timeout = timeout;
        this.sellerPublicKey = seller.getPublicKey().getEncoded();
        this.hash = hash;
        this.signature = signature;
    }
    public boolean isValidAuction() {
        try {
            PublicKey publicKey = KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(sellerPublicKey));
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(getAuctionData().getBytes());
            boolean signatureValid = signature.verify(Base64.getDecoder().decode(this.signature));

            return signatureValid && System.currentTimeMillis() <= timeout;
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    private String getAuctionData() {
        return Base64.getEncoder().encodeToString(itemId) +
                timeout +
                Base64.getEncoder().encodeToString(sellerPublicKey);
    }


    // quando criar um auction, adicionar ao auctionMap!!!!!

    public byte[] getItemId() {
        return itemId;
    }

    public long getTimeout() {
        return timeout;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getSignature() {
        return signature;
    }
    // Helper method to consolidate auction data for hashing and signing


}
