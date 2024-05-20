package group19.ssd.blockchain;

import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.blockchain.transactions.Wallet;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;

public class Blockchain {

    public static ArrayList<Block> chain = new ArrayList<>(); //blocos em cadeia

    public static ArrayList<Transaction> pendingList = new ArrayList<>(); //before enter in the block
    public static int difficulty = 5;

    public Blockchain(Wallet wallet) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        createGenesisBlock(wallet);
    }
    // create empty blockchain
    public Blockchain() {
        chain = new ArrayList<>();
        //adicionar aqui pendingList = new ArrayList<>();
    }

    public ArrayList<Block> getChain() {
        return chain;
    }

    public void createGenesisBlock(Wallet wallet) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        Wallet tempWallet = new Wallet();
        this.addTransaction(tempWallet, wallet,100);
        this.minePendingTransaction(wallet);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    // Method to add a new block to the blockchain
    public void addBlock(Block block) {
        // Calcular e exibir a Merkle Tree
        System.out.println("Merkle Root: " + block.getMerkleRoot());

        //verify block
        block.verifyBlock();

        // Minerar o bloco
        block.mineBlock(difficulty);

        if (chain.isEmpty()) {
            // If the blockchain is empty, add the new block directly
            chain.add(block);
        } else {
            // If the blockchain is not empty, set the previous hash of the new block
            String previousHash = getLatestBlock().hash;
            block.previousHash = previousHash;
            // Mine the new block
            block.mineBlock(difficulty);
            // Add the new block to the blockchain
            chain.add(block);
        }
    }


    // verify the transactions, checking if it goes to the pendingtransactions
    // Functions addtransaction and pendingtransactions are to manage transaction to go for addBlock
    public void addTransaction(Transaction transaction){
        if(transaction.isValid(transaction.getSource().getPublicKey())){
            pendingtransactions(transaction);
        }else {
            System.out.println("the transaction not passed in the isValid function");
            System.exit(1);     // Stops the execution of the program with an error code 1
        }
    }
    public void pendingtransactions(Transaction transaction){
        pendingList.add(transaction);
        //check if it enters to addBlock or not
        if(pendingList.size() > 5){
            Block nb = new Block(pendingList, getLatestBlock().getHash());
            addBlock(nb);
        }else{
            System.out.println("o pending transaction ainda nao esta full");
            // continue the program
        }
    }

    public ArrayList<Transaction> getPendingTransactions() {
        return pendingList;
    }

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    public void printBlockChain(){
        System.out.println("Blockchain: ");
        for(Block current_block : chain){
            current_block.printBlock();
        }
        String BC_isvalid = this.isChainValid() ? "true" : "false";
        if(BC_isvalid.equals("true")){
            System.out.println("Blockchain is valid");
        } else{
            System.out.println("Blockchain is not valid");
        }
    }




}
