// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resources.proto

package group19.ssd.p2p.grpc;

public final class BlockchainGRPCProto {
  private BlockchainGRPCProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_Ping_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_Ping_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_Pong_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_Pong_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_IPAddres_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_IPAddres_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_Block_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_Block_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_TransactionsList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_TransactionsList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_BlockChain_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_BlockChain_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_Transaction_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_Transaction_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_Status_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_Status_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_FindNode_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_FindNode_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_Node_GRPC_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_Node_GRPC_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ledger_KBucket_GRPC_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ledger_KBucket_GRPC_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017resources.proto\022\006ledger\",\n\004Ping\022\n\n\002id\030" +
      "\001 \001(\t\022\n\n\002ip\030\002 \001(\t\022\014\n\004port\030\003 \001(\005\"<\n\004Pong\022" +
      "\014\n\004pong\030\001 \001(\010\022&\n\nblockchain\030\002 \001(\0132\022.ledg" +
      "er.BlockChain\"$\n\010IPAddres\022\n\n\002ip\030\001 \001(\t\022\014\n" +
      "\004port\030\002 \001(\t\"[\n\005Block\022\r\n\005index\030\001 \001(\005\022\024\n\014p" +
      "reviousHash\030\002 \001(\t\022\014\n\004hash\030\003 \001(\t\022\014\n\004data\030" +
      "\004 \001(\t\022\021\n\ttimestamp\030\005 \001(\003\"@\n\020Transactions" +
      "List\022,\n\017transactionList\030\001 \003(\0132\023.ledger.T" +
      "ransaction\"a\n\nBlockChain\022\034\n\005chain\030\001 \003(\0132" +
      "\r.ledger.Block\0225\n\023pendingTransactions\030\002 " +
      "\001(\0132\030.ledger.TransactionsList\"\225\001\n\013Transa" +
      "ction\022\014\n\004hash\030\001 \001(\t\022\020\n\010senderPK\030\002 \001(\t\022\022\n" +
      "\nreceiverPK\030\003 \001(\t\022\021\n\tsignature\030\004 \001(\014\022\021\n\t" +
      "timestamp\030\005 \001(\003\022\016\n\006amount\030\006 \001(\005\022\014\n\004misc\030" +
      "\007 \001(\t\022\016\n\006nodeId\030\010 \001(\t\"\031\n\006Status\022\017\n\007succe" +
      "ss\030\001 \001(\010\"a\n\010FindNode\022\n\n\002id\030\001 \001(\t\022\n\n\002ip\030\002" +
      " \001(\t\022\014\n\004port\030\003 \001(\005\022\r\n\005proof\030\004 \001(\005\022\016\n\006pub" +
      "Key\030\005 \001(\t\022\020\n\010targetId\030\006 \001(\t\"1\n\tNode_GRPC" +
      "\022\n\n\002id\030\001 \001(\t\022\n\n\002ip\030\002 \001(\t\022\014\n\004port\030\003 \001(\005\"2" +
      "\n\014KBucket_GRPC\022\"\n\007kbucket\030\001 \003(\0132\021.ledger" +
      ".Node_GRPC2\230\002\n\021BlockchainService\022\"\n\004ping" +
      "\022\014.ledger.Ping\032\014.ledger.Pong\0220\n\017broadcas" +
      "t_block\022\r.ledger.Block\032\016.ledger.Status\022<" +
      "\n\025broadcast_transaction\022\023.ledger.Transac" +
      "tion\032\016.ledger.Status\022:\n\024broadcast_blockc" +
      "hain\022\022.ledger.BlockChain\032\016.ledger.Status" +
      "\0223\n\tfindNodes\022\020.ledger.FindNode\032\024.ledger" +
      ".KBucket_GRPCB2\n\024group19.ssd.p2p.grpcB\023B" +
      "lockchainGRPCProtoP\001\242\002\002BCb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_ledger_Ping_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ledger_Ping_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_Ping_descriptor,
        new java.lang.String[] { "Id", "Ip", "Port", });
    internal_static_ledger_Pong_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ledger_Pong_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_Pong_descriptor,
        new java.lang.String[] { "Pong", "Blockchain", });
    internal_static_ledger_IPAddres_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ledger_IPAddres_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_IPAddres_descriptor,
        new java.lang.String[] { "Ip", "Port", });
    internal_static_ledger_Block_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_ledger_Block_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_Block_descriptor,
        new java.lang.String[] { "Index", "PreviousHash", "Hash", "Data", "Timestamp", });
    internal_static_ledger_TransactionsList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_ledger_TransactionsList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_TransactionsList_descriptor,
        new java.lang.String[] { "TransactionList", });
    internal_static_ledger_BlockChain_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_ledger_BlockChain_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_BlockChain_descriptor,
        new java.lang.String[] { "Chain", "PendingTransactions", });
    internal_static_ledger_Transaction_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_ledger_Transaction_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_Transaction_descriptor,
        new java.lang.String[] { "Hash", "SenderPK", "ReceiverPK", "Signature", "Timestamp", "Amount", "Misc", "NodeId", });
    internal_static_ledger_Status_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_ledger_Status_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_Status_descriptor,
        new java.lang.String[] { "Success", });
    internal_static_ledger_FindNode_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_ledger_FindNode_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_FindNode_descriptor,
        new java.lang.String[] { "Id", "Ip", "Port", "Proof", "PubKey", "TargetId", });
    internal_static_ledger_Node_GRPC_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_ledger_Node_GRPC_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_Node_GRPC_descriptor,
        new java.lang.String[] { "Id", "Ip", "Port", });
    internal_static_ledger_KBucket_GRPC_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_ledger_KBucket_GRPC_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ledger_KBucket_GRPC_descriptor,
        new java.lang.String[] { "Kbucket", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
