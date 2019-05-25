package com.vng.security;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: AuthService.proto")
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final String SERVICE_NAME = "com.vng.security.AuthService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.vng.security.AuthServiceOuterClass.LoginRequest,
      com.vng.security.AuthServiceOuterClass.TokenResponse> METHOD_LOGIN =
      io.grpc.MethodDescriptor.<com.vng.security.AuthServiceOuterClass.LoginRequest, com.vng.security.AuthServiceOuterClass.TokenResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.vng.security.AuthService", "login"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.vng.security.AuthServiceOuterClass.LoginRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.vng.security.AuthServiceOuterClass.TokenResponse.getDefaultInstance()))
          .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("login"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.vng.security.AuthServiceOuterClass.Message,
      com.vng.security.AuthServiceOuterClass.Message> METHOD_LOGOUT =
      io.grpc.MethodDescriptor.<com.vng.security.AuthServiceOuterClass.Message, com.vng.security.AuthServiceOuterClass.Message>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.vng.security.AuthService", "logout"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.vng.security.AuthServiceOuterClass.Message.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.vng.security.AuthServiceOuterClass.Message.getDefaultInstance()))
          .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("logout"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.vng.security.AuthServiceOuterClass.Message,
      com.vng.security.AuthServiceOuterClass.Message> METHOD_CHECK_TOKEN =
      io.grpc.MethodDescriptor.<com.vng.security.AuthServiceOuterClass.Message, com.vng.security.AuthServiceOuterClass.Message>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.vng.security.AuthService", "checkToken"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.vng.security.AuthServiceOuterClass.Message.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.vng.security.AuthServiceOuterClass.Message.getDefaultInstance()))
          .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("checkToken"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    return new AuthServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AuthServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AuthServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AuthServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(com.vng.security.AuthServiceOuterClass.LoginRequest request,
        io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.TokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOGIN, responseObserver);
    }

    /**
     */
    public void logout(com.vng.security.AuthServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.Message> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOGOUT, responseObserver);
    }

    /**
     */
    public void checkToken(com.vng.security.AuthServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.Message> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CHECK_TOKEN, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LOGIN,
            asyncUnaryCall(
              new MethodHandlers<
                com.vng.security.AuthServiceOuterClass.LoginRequest,
                com.vng.security.AuthServiceOuterClass.TokenResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            METHOD_LOGOUT,
            asyncUnaryCall(
              new MethodHandlers<
                com.vng.security.AuthServiceOuterClass.Message,
                com.vng.security.AuthServiceOuterClass.Message>(
                  this, METHODID_LOGOUT)))
          .addMethod(
            METHOD_CHECK_TOKEN,
            asyncUnaryCall(
              new MethodHandlers<
                com.vng.security.AuthServiceOuterClass.Message,
                com.vng.security.AuthServiceOuterClass.Message>(
                  this, METHODID_CHECK_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class AuthServiceStub extends io.grpc.stub.AbstractStub<AuthServiceStub> {
    private AuthServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void login(com.vng.security.AuthServiceOuterClass.LoginRequest request,
        io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.TokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOGIN, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(com.vng.security.AuthServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.Message> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOGOUT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkToken(com.vng.security.AuthServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.Message> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK_TOKEN, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthServiceBlockingStub extends io.grpc.stub.AbstractStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.vng.security.AuthServiceOuterClass.TokenResponse login(com.vng.security.AuthServiceOuterClass.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOGIN, getCallOptions(), request);
    }

    /**
     */
    public com.vng.security.AuthServiceOuterClass.Message logout(com.vng.security.AuthServiceOuterClass.Message request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOGOUT, getCallOptions(), request);
    }

    /**
     */
    public com.vng.security.AuthServiceOuterClass.Message checkToken(com.vng.security.AuthServiceOuterClass.Message request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CHECK_TOKEN, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthServiceFutureStub extends io.grpc.stub.AbstractStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vng.security.AuthServiceOuterClass.TokenResponse> login(
        com.vng.security.AuthServiceOuterClass.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOGIN, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vng.security.AuthServiceOuterClass.Message> logout(
        com.vng.security.AuthServiceOuterClass.Message request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOGOUT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vng.security.AuthServiceOuterClass.Message> checkToken(
        com.vng.security.AuthServiceOuterClass.Message request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK_TOKEN, getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_LOGOUT = 1;
  private static final int METHODID_CHECK_TOKEN = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.vng.security.AuthServiceOuterClass.LoginRequest) request,
              (io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.TokenResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((com.vng.security.AuthServiceOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.Message>) responseObserver);
          break;
        case METHODID_CHECK_TOKEN:
          serviceImpl.checkToken((com.vng.security.AuthServiceOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<com.vng.security.AuthServiceOuterClass.Message>) responseObserver);
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

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.vng.security.AuthServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AuthServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(METHOD_LOGIN)
              .addMethod(METHOD_LOGOUT)
              .addMethod(METHOD_CHECK_TOKEN)
              .build();
        }
      }
    }
    return result;
  }
}
