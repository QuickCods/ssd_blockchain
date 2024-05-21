package group19.ssd.blockchain;

import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.blockchain.utils.StringUtil;
import group19.ssd.miscellaneous.Configuration;

import java.util.*;

public class Block {

    public String getHash() {
        return hash;
    }

    public String hashId; //identifier
    public String hash;
    public String publicKey;

    public String getPreviousHash() {
        return previousHash;
    }

    public String previousHash;
    public static ArrayList<Transaction> data = new ArrayList<>();

    private String merkleRoot;
    public int nonce;
    public long timestamp;     //as number of milliseconds since 1/1/1970..

    //new block
    public Block(String hashId, ArrayList<Transaction> transactions, String lastBlockHash, String miner) {
        this.hashId = hashId;
        this.data = transactions;
        this.previousHash = lastBlockHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
        this.publicKey = miner;
    }

    // new block from another block received by other node
    public Block(String hashId, String hash, String lastBlockHash, ArrayList<Transaction> transactions, int nonce, long timestamp, String miner) {
        this.hashId = hashId;
        this.data = transactions;
        this.previousHash = lastBlockHash;
        this.timestamp = timestamp;
        this.hash = hash;
        this.publicKey = miner;
        this.nonce = nonce;
    }

    public Block(ArrayList<Transaction> data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.merkleRoot = calculateMerkleRoot(); // Calculate the Merkle Root
        this.hash = calculateHash();
    }

    private String calculateMerkleRoot() {
        List<String> treeList = new ArrayList<>();
        for (Transaction transaction : data) {
            treeList.add(transaction.getFormattedData());
        }
        while (treeList.size() > 1) {
            List<String> newTreeList = new ArrayList<>();
            int index = 0;
            while (index < treeList.size()) {
                // Grab the left and right if available
                String left = treeList.get(index);
                String right = index+1 < treeList.size() ? treeList.get(index+1) : left;
                // Combine and hash them, then add to new list
                String combinedHash = StringUtil.applySha256(left + right);
                newTreeList.add(combinedHash);
                index += 2;
            }
            treeList = newTreeList;
        }
        return treeList.get(0); // Root of the tree
    }

    /// Calculate the block hash using Merkle Root
    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timestamp) +
                        merkleRoot +
                        Integer.toString(nonce)
        );
        return calculatedHash;
    }

    //create a hash with dificulty that i want to impose
    public void mineBlock() {
        String target = new String(new char[Configuration.MINING_DIFFICULTY]).replace('\0', '0'); //Create a string with difficulty * "0"
        System.out.println("Starting to mine with difficulty target: " + target);
        while(!hash.substring( 0, Configuration.MINING_DIFFICULTY).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash + " with nonce: " + nonce);
    }

    public ArrayList<Transaction> getTransactions() {
        return data; // Return a copy of the data array to prevent external modification.
    }

    public boolean verifyBlock() {
        // Verify that the block's hash is correct
        String recalculatedHash = calculateHash();
        if (!hash.equals(recalculatedHash)) {
            System.out.println("Block hash is invalid.");
            return false;
        }

        /*
        // Verify that each transaction is valid
        for (Transaction transaction : data) {
            PublicKey senderPublicKey = keyStore.getKeyPair(transaction.getSourceName()).getPublic();
            if (!transaction.isValid(senderPublicKey)) {
                System.out.println("Invalid transaction found in block.");
                return false;
            }
        }
        */

        // Verify the Merkle root is accurate
        String recalculatedMerkleRoot = calculateMerkleRoot();
        if (!merkleRoot.equals(recalculatedMerkleRoot)) {
            System.out.println("Merkle Root mismatch.");
            return false;
        }

        System.out.println("verifyBlock pass!");
        return true;
    }

    public void printBlock(){
        System.out.println("\nHash: " + this.hash);
        System.out.println("Previous Hash: " + this.previousHash);
        System.out.println("Nonce: " + this.nonce);
        System.out.println("Transactions:");
        for(Transaction transaction : data){
            transaction.printTransaction();
            System.out.println();
        }
    }


    public String getMerkleRoot() {
        return calculateMerkleRoot();
    }
}

