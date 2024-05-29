package group19.ssd.p2p;

import group19.ssd.blockchain.Block;
import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.miscellaneous.Configuration;

import java.util.Hashtable;

public class Ledger {
    Hashtable<String, Long> users = new Hashtable<>();
    long minCoin = 15;

    public Ledger() {
        users.put(KademliaClient.publicKey, minCoin);
    }

    public Long getBalance(String pubKey) {
        return users.get(pubKey);
    }

    public void reset() {
        users.clear();
        users.put(KademliaClient.publicKey, (long) minCoin);
    }

    public void restartLedger() {
        reset();
        for (Block block : KademliaClient.blockchain.getChain()) {
            updateLedger(block);
        }
    }

    public void updateLedger(Block block) {
        if (block == null) {
            System.out.println("Block is null, cannot update ledger.");
            return;
        }

        if (block.getTransactions() == null) {
            System.out.println("Block transactions are null, cannot update ledger.");
            return;
        }

        for (Transaction transaction : block.getTransactions()) {
            if (transaction.source == null) {
                System.out.println("Transaction sender or receiver is null, skipping this transaction.");
                continue;
            }
            System.out.println("sender --------------------------------------------------------------------- " + transaction.source);

            Long senderAmount = users.getOrDefault(transaction.source, minCoin);


            users.put(transaction.sender, senderAmount - 0);
        }

        if (block.publicKey != null) {
            users.put(block.publicKey, users.getOrDefault(block.publicKey, minCoin) + Configuration.MINING_REWARD);
        } else {
            System.out.println("Block public key is null, cannot update miner's reward.");
        }
    }

}
