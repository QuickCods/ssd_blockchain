// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resources.proto

package group19.ssd.p2p.grpc;

public interface TransactionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:blockchain.Transaction)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string hash = 1;</code>
   * @return The hash.
   */
  java.lang.String getHash();
  /**
   * <code>string hash = 1;</code>
   * @return The bytes for hash.
   */
  com.google.protobuf.ByteString
      getHashBytes();

  /**
   * <code>string sender = 2;</code>
   * @return The sender.
   */
  java.lang.String getSender();
  /**
   * <code>string sender = 2;</code>
   * @return The bytes for sender.
   */
  com.google.protobuf.ByteString
      getSenderBytes();

  /**
   * <code>string receiver = 3;</code>
   * @return The receiver.
   */
  java.lang.String getReceiver();
  /**
   * <code>string receiver = 3;</code>
   * @return The bytes for receiver.
   */
  com.google.protobuf.ByteString
      getReceiverBytes();

  /**
   * <code>string signature = 4;</code>
   * @return The signature.
   */
  java.lang.String getSignature();
  /**
   * <code>string signature = 4;</code>
   * @return The bytes for signature.
   */
  com.google.protobuf.ByteString
      getSignatureBytes();

  /**
   * <code>int64 timestamp = 5;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>int32 amount = 6;</code>
   * @return The amount.
   */
  int getAmount();

  /**
   * <code>string misc = 7;</code>
   * @return The misc.
   */
  java.lang.String getMisc();
  /**
   * <code>string misc = 7;</code>
   * @return The bytes for misc.
   */
  com.google.protobuf.ByteString
      getMiscBytes();

  /**
   * <code>string nodeId = 8;</code>
   * @return The nodeId.
   */
  java.lang.String getNodeId();
  /**
   * <code>string nodeId = 8;</code>
   * @return The bytes for nodeId.
   */
  com.google.protobuf.ByteString
      getNodeIdBytes();
}
