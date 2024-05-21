package group19.ssd.blockchain;

import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.miscellaneous.Miscellaneous;
import group19.ssd.miscellaneous.Configuration;
import java.util.ArrayList;

public class MerkleTree {
    ArrayList<Transaction> list;
    String root;

    public MerkleTree(ArrayList<Transaction> list, String root) {
        this.list = list;
        this.root = "";
    }

    public String getRoot(){
        return this.root;
    }
}
