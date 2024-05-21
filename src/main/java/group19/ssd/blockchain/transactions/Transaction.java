package group19.ssd.blockchain.transactions;

import group19.ssd.blockchain.utils.StringUtil;

import java.security.*;
import java.util.Base64;
import java.util.Date;

public class Transaction {


    public Wallet source;
    public Wallet destination;
    public String sender;
    public String receiver;
    public Long amount;
    public String signature; // This will store our signature;              Byte[] Type or String Type?
    public String transactionId; // This will store the hash of the transaction
    public String misc = ""; //info adicional
    public long timestamp;

    public Transaction(Wallet source, Wallet destination, Long amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        validateTransaction();
        this.misc = misc;
    }

    public Transaction(String sender, String receiver, Long amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        validateTransaction();
        this.misc = misc;
    }

    public Transaction(String sender, String receiver, byte[] byteArray, long timestamp, long amount, String misc) {
        this.sender = sender;
        this.receiver = receiver;
        this.signature = byteArray.toString();
        this.timestamp = timestamp;
        this.amount = amount;
        this.misc = misc;
    }

    public void validateTransaction() {
        this.timestamp = new Date().getTime();
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
    public String calculateHash() {
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

    public boolean isSigned(){
        return this.signature !=null;
    }

    public void printTransaction() {
        System.out.println("hash:" + this.transactionId);
        System.out.println("from:" + this.source);
        System.out.println("to:" + this.destination);
        System.out.println("amount:" + this.amount);
        if(this.isSigned()){
            System.out.println("Is signed.");
        } else{
            System.out.println("Is not signed.");
        }
        System.out.println("Time:" + this.timestamp);
        System.out.println("Information: " + (((this.misc == null)) ? "''" : this.misc + "\n"));
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

