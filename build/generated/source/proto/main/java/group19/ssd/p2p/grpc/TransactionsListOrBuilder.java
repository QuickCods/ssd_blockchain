// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resources.proto

package group19.ssd.p2p.grpc;

public interface TransactionsListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:blockchain.TransactionsList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .blockchain.Transaction transactionList = 1;</code>
   */
  java.util.List<group19.ssd.p2p.grpc.Transaction> 
      getTransactionListList();
  /**
   * <code>repeated .blockchain.Transaction transactionList = 1;</code>
   */
  group19.ssd.p2p.grpc.Transaction getTransactionList(int index);
  /**
   * <code>repeated .blockchain.Transaction transactionList = 1;</code>
   */
  int getTransactionListCount();
  /**
   * <code>repeated .blockchain.Transaction transactionList = 1;</code>
   */
  java.util.List<? extends group19.ssd.p2p.grpc.TransactionOrBuilder> 
      getTransactionListOrBuilderList();
  /**
   * <code>repeated .blockchain.Transaction transactionList = 1;</code>
   */
  group19.ssd.p2p.grpc.TransactionOrBuilder getTransactionListOrBuilder(
      int index);
}
