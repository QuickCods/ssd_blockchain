package group19.ssd.p2p.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.48.0)",
    comments = "Source: resources.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BlockchainServiceGrpc {

  private BlockchainServiceGrpc() {}

  public static final String SERVICE_NAME = "ledger.BlockchainService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Ping,
      group19.ssd.p2p.grpc.Pong> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = group19.ssd.p2p.grpc.Ping.class,
      responseType = group19.ssd.p2p.grpc.Pong.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Ping,
      group19.ssd.p2p.grpc.Pong> getPingMethod() {
    io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Ping, group19.ssd.p2p.grpc.Pong> getPingMethod;
    if ((getPingMethod = BlockchainServiceGrpc.getPingMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getPingMethod = BlockchainServiceGrpc.getPingMethod) == null) {
          BlockchainServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<group19.ssd.p2p.grpc.Ping, group19.ssd.p2p.grpc.Pong>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.Ping.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.Pong.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Block,
      group19.ssd.p2p.grpc.Status> getBroadcastBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "broadcast_block",
      requestType = group19.ssd.p2p.grpc.Block.class,
      responseType = group19.ssd.p2p.grpc.Status.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Block,
      group19.ssd.p2p.grpc.Status> getBroadcastBlockMethod() {
    io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Block, group19.ssd.p2p.grpc.Status> getBroadcastBlockMethod;
    if ((getBroadcastBlockMethod = BlockchainServiceGrpc.getBroadcastBlockMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getBroadcastBlockMethod = BlockchainServiceGrpc.getBroadcastBlockMethod) == null) {
          BlockchainServiceGrpc.getBroadcastBlockMethod = getBroadcastBlockMethod =
              io.grpc.MethodDescriptor.<group19.ssd.p2p.grpc.Block, group19.ssd.p2p.grpc.Status>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "broadcast_block"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.Block.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.Status.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("broadcast_block"))
              .build();
        }
      }
    }
    return getBroadcastBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Transaction,
      group19.ssd.p2p.grpc.Status> getBroadcastTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "broadcast_transaction",
      requestType = group19.ssd.p2p.grpc.Transaction.class,
      responseType = group19.ssd.p2p.grpc.Status.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Transaction,
      group19.ssd.p2p.grpc.Status> getBroadcastTransactionMethod() {
    io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.Transaction, group19.ssd.p2p.grpc.Status> getBroadcastTransactionMethod;
    if ((getBroadcastTransactionMethod = BlockchainServiceGrpc.getBroadcastTransactionMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getBroadcastTransactionMethod = BlockchainServiceGrpc.getBroadcastTransactionMethod) == null) {
          BlockchainServiceGrpc.getBroadcastTransactionMethod = getBroadcastTransactionMethod =
              io.grpc.MethodDescriptor.<group19.ssd.p2p.grpc.Transaction, group19.ssd.p2p.grpc.Status>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "broadcast_transaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.Transaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.Status.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("broadcast_transaction"))
              .build();
        }
      }
    }
    return getBroadcastTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.BlockChain,
      group19.ssd.p2p.grpc.Status> getBroadcastBlockchainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "broadcast_blockchain",
      requestType = group19.ssd.p2p.grpc.BlockChain.class,
      responseType = group19.ssd.p2p.grpc.Status.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.BlockChain,
      group19.ssd.p2p.grpc.Status> getBroadcastBlockchainMethod() {
    io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.BlockChain, group19.ssd.p2p.grpc.Status> getBroadcastBlockchainMethod;
    if ((getBroadcastBlockchainMethod = BlockchainServiceGrpc.getBroadcastBlockchainMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getBroadcastBlockchainMethod = BlockchainServiceGrpc.getBroadcastBlockchainMethod) == null) {
          BlockchainServiceGrpc.getBroadcastBlockchainMethod = getBroadcastBlockchainMethod =
              io.grpc.MethodDescriptor.<group19.ssd.p2p.grpc.BlockChain, group19.ssd.p2p.grpc.Status>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "broadcast_blockchain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.BlockChain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.Status.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("broadcast_blockchain"))
              .build();
        }
      }
    }
    return getBroadcastBlockchainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.FindNode,
      group19.ssd.p2p.grpc.KBucket_GRPC> getFindNodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findNodes",
      requestType = group19.ssd.p2p.grpc.FindNode.class,
      responseType = group19.ssd.p2p.grpc.KBucket_GRPC.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.FindNode,
      group19.ssd.p2p.grpc.KBucket_GRPC> getFindNodesMethod() {
    io.grpc.MethodDescriptor<group19.ssd.p2p.grpc.FindNode, group19.ssd.p2p.grpc.KBucket_GRPC> getFindNodesMethod;
    if ((getFindNodesMethod = BlockchainServiceGrpc.getFindNodesMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getFindNodesMethod = BlockchainServiceGrpc.getFindNodesMethod) == null) {
          BlockchainServiceGrpc.getFindNodesMethod = getFindNodesMethod =
              io.grpc.MethodDescriptor.<group19.ssd.p2p.grpc.FindNode, group19.ssd.p2p.grpc.KBucket_GRPC>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findNodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.FindNode.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  group19.ssd.p2p.grpc.KBucket_GRPC.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("findNodes"))
              .build();
        }
      }
    }
    return getFindNodesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BlockchainServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceStub>() {
        @java.lang.Override
        public BlockchainServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockchainServiceStub(channel, callOptions);
        }
      };
    return BlockchainServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BlockchainServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceBlockingStub>() {
        @java.lang.Override
        public BlockchainServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockchainServiceBlockingStub(channel, callOptions);
        }
      };
    return BlockchainServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BlockchainServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceFutureStub>() {
        @java.lang.Override
        public BlockchainServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockchainServiceFutureStub(channel, callOptions);
        }
      };
    return BlockchainServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class BlockchainServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ping to check if the node is alive
     * </pre>
     */
    public void ping(group19.ssd.p2p.grpc.Ping request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Pong> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     * <pre>
     *broadcast block to all nodes
     * </pre>
     */
    public void broadcastBlock(group19.ssd.p2p.grpc.Block request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBroadcastBlockMethod(), responseObserver);
    }

    /**
     */
    public void broadcastTransaction(group19.ssd.p2p.grpc.Transaction request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBroadcastTransactionMethod(), responseObserver);
    }

    /**
     */
    public void broadcastBlockchain(group19.ssd.p2p.grpc.BlockChain request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBroadcastBlockchainMethod(), responseObserver);
    }

    /**
     */
    public void findNodes(group19.ssd.p2p.grpc.FindNode request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.KBucket_GRPC> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindNodesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPingMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                group19.ssd.p2p.grpc.Ping,
                group19.ssd.p2p.grpc.Pong>(
                  this, METHODID_PING)))
          .addMethod(
            getBroadcastBlockMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                group19.ssd.p2p.grpc.Block,
                group19.ssd.p2p.grpc.Status>(
                  this, METHODID_BROADCAST_BLOCK)))
          .addMethod(
            getBroadcastTransactionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                group19.ssd.p2p.grpc.Transaction,
                group19.ssd.p2p.grpc.Status>(
                  this, METHODID_BROADCAST_TRANSACTION)))
          .addMethod(
            getBroadcastBlockchainMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                group19.ssd.p2p.grpc.BlockChain,
                group19.ssd.p2p.grpc.Status>(
                  this, METHODID_BROADCAST_BLOCKCHAIN)))
          .addMethod(
            getFindNodesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                group19.ssd.p2p.grpc.FindNode,
                group19.ssd.p2p.grpc.KBucket_GRPC>(
                  this, METHODID_FIND_NODES)))
          .build();
    }
  }

  /**
   */
  public static final class BlockchainServiceStub extends io.grpc.stub.AbstractAsyncStub<BlockchainServiceStub> {
    private BlockchainServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockchainServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockchainServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * ping to check if the node is alive
     * </pre>
     */
    public void ping(group19.ssd.p2p.grpc.Ping request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Pong> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *broadcast block to all nodes
     * </pre>
     */
    public void broadcastBlock(group19.ssd.p2p.grpc.Block request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBroadcastBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void broadcastTransaction(group19.ssd.p2p.grpc.Transaction request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBroadcastTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void broadcastBlockchain(group19.ssd.p2p.grpc.BlockChain request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBroadcastBlockchainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findNodes(group19.ssd.p2p.grpc.FindNode request,
        io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.KBucket_GRPC> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindNodesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BlockchainServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<BlockchainServiceBlockingStub> {
    private BlockchainServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockchainServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockchainServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * ping to check if the node is alive
     * </pre>
     */
    public group19.ssd.p2p.grpc.Pong ping(group19.ssd.p2p.grpc.Ping request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *broadcast block to all nodes
     * </pre>
     */
    public group19.ssd.p2p.grpc.Status broadcastBlock(group19.ssd.p2p.grpc.Block request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBroadcastBlockMethod(), getCallOptions(), request);
    }

    /**
     */
    public group19.ssd.p2p.grpc.Status broadcastTransaction(group19.ssd.p2p.grpc.Transaction request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBroadcastTransactionMethod(), getCallOptions(), request);
    }

    /**
     */
    public group19.ssd.p2p.grpc.Status broadcastBlockchain(group19.ssd.p2p.grpc.BlockChain request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBroadcastBlockchainMethod(), getCallOptions(), request);
    }

    /**
     */
    public group19.ssd.p2p.grpc.KBucket_GRPC findNodes(group19.ssd.p2p.grpc.FindNode request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindNodesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BlockchainServiceFutureStub extends io.grpc.stub.AbstractFutureStub<BlockchainServiceFutureStub> {
    private BlockchainServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockchainServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockchainServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * ping to check if the node is alive
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<group19.ssd.p2p.grpc.Pong> ping(
        group19.ssd.p2p.grpc.Ping request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *broadcast block to all nodes
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<group19.ssd.p2p.grpc.Status> broadcastBlock(
        group19.ssd.p2p.grpc.Block request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBroadcastBlockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<group19.ssd.p2p.grpc.Status> broadcastTransaction(
        group19.ssd.p2p.grpc.Transaction request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBroadcastTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<group19.ssd.p2p.grpc.Status> broadcastBlockchain(
        group19.ssd.p2p.grpc.BlockChain request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBroadcastBlockchainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<group19.ssd.p2p.grpc.KBucket_GRPC> findNodes(
        group19.ssd.p2p.grpc.FindNode request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindNodesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_BROADCAST_BLOCK = 1;
  private static final int METHODID_BROADCAST_TRANSACTION = 2;
  private static final int METHODID_BROADCAST_BLOCKCHAIN = 3;
  private static final int METHODID_FIND_NODES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BlockchainServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BlockchainServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((group19.ssd.p2p.grpc.Ping) request,
              (io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Pong>) responseObserver);
          break;
        case METHODID_BROADCAST_BLOCK:
          serviceImpl.broadcastBlock((group19.ssd.p2p.grpc.Block) request,
              (io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status>) responseObserver);
          break;
        case METHODID_BROADCAST_TRANSACTION:
          serviceImpl.broadcastTransaction((group19.ssd.p2p.grpc.Transaction) request,
              (io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status>) responseObserver);
          break;
        case METHODID_BROADCAST_BLOCKCHAIN:
          serviceImpl.broadcastBlockchain((group19.ssd.p2p.grpc.BlockChain) request,
              (io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.Status>) responseObserver);
          break;
        case METHODID_FIND_NODES:
          serviceImpl.findNodes((group19.ssd.p2p.grpc.FindNode) request,
              (io.grpc.stub.StreamObserver<group19.ssd.p2p.grpc.KBucket_GRPC>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BlockchainServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BlockchainServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return group19.ssd.p2p.grpc.BlockchainGRPCProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BlockchainService");
    }
  }

  private static final class BlockchainServiceFileDescriptorSupplier
      extends BlockchainServiceBaseDescriptorSupplier {
    BlockchainServiceFileDescriptorSupplier() {}
  }

  private static final class BlockchainServiceMethodDescriptorSupplier
      extends BlockchainServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BlockchainServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BlockchainServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BlockchainServiceFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getBroadcastBlockMethod())
              .addMethod(getBroadcastTransactionMethod())
              .addMethod(getBroadcastBlockchainMethod())
              .addMethod(getFindNodesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
