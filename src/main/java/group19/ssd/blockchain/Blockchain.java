package group19.ssd.blockchain;

import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.blockchain.transactions.Wallet;
import group19.ssd.miscellaneous.Configuration;
import group19.ssd.p2p.KademliaClient;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Base64;

public class Blockchain {

    public static ArrayList<Block> chain = new ArrayList<>(); //blocos em cadeia

    public static ArrayList<Transaction> pendingList = new ArrayList<>(); //before enter in the block

    public Blockchain(Wallet wallet) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        createGenesisBlock(wallet);
    }
    // create empty blockchain
    public Blockchain() {
        chain = new ArrayList<>();
        pendingList = new ArrayList<>();
    }

    public ArrayList<Block> getChain() {
        return chain;
    }

    public void createGenesisBlock(Wallet wallet) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        Wallet tempWallet = new Wallet();
        this.addTransaction(tempWallet, wallet,100);
        this.minePendingTransaction(wallet);
    }

    // ddefine pending transactions and makes a copy
    public void setPendingTransactions(ArrayList<Transaction> oldTransactionArrayList) {
        for (Transaction curTransaction : oldTransactionArrayList) {
            pendingList.add(new Transaction(curTransaction.sender, curTransaction.receiver, curTransaction.signature.getBytes(), curTransaction.timestamp, curTransaction.amount, curTransaction.misc));
                                                                                                // nao sei se é preciso dar algum encode aqui no signature
        }
    }

    public ArrayList<Transaction> getPendingList() {
        return pendingList;
    }

    public int getPendingListSize() {
        return pendingList.size();
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
        block.mineBlock();

        if (chain.isEmpty()) {
            // If the blockchain is empty, add the new block directly
            chain.add(block);
        } else {
            // If the blockchain is not empty, set the previous hash of the new block
            String previousHash = getLatestBlock().hash;
            block.previousHash = previousHash;
            // Mine the new block
            block.mineBlock();
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

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[Configuration.MINING_DIFFICULTY]).replace('\0', '0');

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
            if(!currentBlock.hash.substring( 0, Configuration.MINING_DIFFICULTY).equals(hashTarget)) {
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

    //Minera as transações pendentes num bloco novo
    public Block minePendingTransaction(Wallet miner) {
        int pendingTransactionsLength = getPendingListSize();
        if (pendingTransactionsLength == 0) {
            System.out.println("Não há transações pendentes");
            return null;
        }

        ArrayList<Transaction> newBlockTransactions = new ArrayList<>();
        if (pendingTransactionsLength >= Configuration.MAX_TRANSACTIONS_PER_BLOCK) {
            for (int i = 0; i < Configuration.MAX_TRANSACTIONS_PER_BLOCK; i++) {
                newBlockTransactions.add(pendingList.get(i));
            }
        } else {
            System.out.println("Não há transições suficientes (" + Configuration.MAX_TRANSACTIONS_PER_BLOCK + "), há apenas " + pendingTransactionsLength);
            newBlockTransactions.addAll(pendingList);
        }

        //criar novo bloco com as transações pendentes e adicona á cadeia
        Block newBlock;
        if (chain.size() == 0) {
            newBlock = new Block(0 + "", newBlockTransactions, "", Base64.getEncoder().encodeToString(KademliaClient.wallet.getPublicKey().getEncoded()));   // Base64... is to convert PublicKey to String
        } else {
            newBlock = new Block(chain.size() + "", newBlockTransactions, this.getLatestBlock().hash, Base64.getEncoder().encodeToString(KademliaClient.wallet.getPublicKey().getEncoded()));
        }
        newBlock.mineBlock();
        chain.add(newBlock);
        pendingList.subList(0, newBlockTransactions.size()).clear();
        System.out.println("FEITO");
        return newBlock;
    }


}
