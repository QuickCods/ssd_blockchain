package group19.ssd.blockchain;

import group19.ssd.p2p.KademliaClient;

public class GRPCConverter {
    //converter bloco
    /*public static group19.ssd.p2p.grpc.Block mkBlock(Block block) {

        return group19.ssd.p2p.grpc.Block.newBuilder()
                .setHashId(block.hashId)
                .setHash(block.hash)
                .setPreviousHash(block.previousHash)
                .setTransactionsList(mkTransactionList(block.transactionsList))
                .setNonce(block.nonce)
                .setTimestamp(block.timestamp)
                .setPublicKey(block.publicKey)
                .setNodeId(KademliaClient.id)
                .build();
    }*/
}
