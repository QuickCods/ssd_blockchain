package group19.ssd.blockchain.transactions;
import org.example.utils.StringUtil;

import java.security.*;
import java.util.Base64;

public class Transaction {


    private Wallet source;
    private Wallet destination;
    private Long amount;
    private String signature; // This will store our signature
    private String transactionId; // This will store the hash of the transaction
    public Transaction(Wallet source, Wallet destination, Long amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.transactionId = calculateHash(); // Calculate hash when the transaction is created
    }

    // Method to generate a digital signature for the transaction
    public void generateSignature(PrivateKey senderprivateKey) {
        String srcEncoded = Base64.getEncoder().encodeToString(source.getPublicKey().getEncoded());
        String destEncoded = Base64.getEncoder().encodeToString(destination.getPublicKey().getEncoded());
        String data = srcEncoded + destEncoded + amount.toString();
        signature = StringUtil.applyECDSASig(senderprivateKey, data);
    }

    // Calculate the hash of the transaction which serves as its ID
    private String calculateHash() {
        String srcEncoded = Base64.getEncoder().encodeToString(source.getPublicKey().getEncoded());
        String destEncoded = Base64.getEncoder().encodeToString(destination.getPublicKey().getEncoded());
        String data = srcEncoded + destEncoded + amount + signature; // Include the signature to ensure integrity
        return StringUtil.applySha256(data);
    }


    // Method to verify the signature
    public boolean verifySignature(PublicKey publicKey) {
        String srcEncoded = Base64.getEncoder().encodeToString(source.getPublicKey().getEncoded());
        String destEncoded = Base64.getEncoder().encodeToString(destination.getPublicKey().getEncoded());
        String data = srcEncoded + destEncoded + amount.toString();
        return StringUtil.verifyECDSASig(publicKey, data, signature);
    }

    // Validates the transaction data integrity and signature
    public boolean isValid(PublicKey senderPublicKey) {
        // Check for a non-negative amount
        if (amount < 0) {
            System.out.println("Transaction amount cannot be negative.");
            return false;
        }

        // Verify that the transaction signature is correct
        if (!verifySignature(senderPublicKey)) {
            System.out.println("Transaction signature is invalid.");
            return false;
        }

        return true;
    }


    public String getFormattedData() {
        return source + ":" + destination + ":" + amount;
    }

    public Wallet getSource() {
        return source;
    }

    public Wallet getDestination() {
        return destination;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public String getTransactionId() {
        return transactionId;
    }

}

