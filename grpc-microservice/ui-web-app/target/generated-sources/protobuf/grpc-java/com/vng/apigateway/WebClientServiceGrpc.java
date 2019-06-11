package com.vng.apigateway;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: WebClientService.proto")
public final class WebClientServiceGrpc {

  private WebClientServiceGrpc() {}

  public static final String SERVICE_NAME = "com.vng.apigateway.WebClientService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.LoginRequest,
      com.vng.apigateway.WebClientServiceOuterClass.TokenResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = com.vng.apigateway.WebClientServiceOuterClass.LoginRequest.class,
      responseType = com.vng.apigateway.WebClientServiceOuterClass.TokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.LoginRequest,
      com.vng.apigateway.WebClientServiceOuterClass.TokenResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.LoginRequest, com.vng.apigateway.WebClientServiceOuterClass.TokenResponse> getLoginMethod;
    if ((getLoginMethod = WebClientServiceGrpc.getLoginMethod) == null) {
      synchronized (WebClientServiceGrpc.class) {
        if ((getLoginMethod = WebClientServiceGrpc.getLoginMethod) == null) {
          WebClientServiceGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<com.vng.apigateway.WebClientServiceOuterClass.LoginRequest, com.vng.apigateway.WebClientServiceOuterClass.TokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.vng.apigateway.WebClientService", "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.TokenResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WebClientServiceMethodDescriptorSupplier("login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message,
      com.vng.apigateway.WebClientServiceOuterClass.Message> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logout",
      requestType = com.vng.apigateway.WebClientServiceOuterClass.Message.class,
      responseType = com.vng.apigateway.WebClientServiceOuterClass.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message,
      com.vng.apigateway.WebClientServiceOuterClass.Message> getLogoutMethod() {
    io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message, com.vng.apigateway.WebClientServiceOuterClass.Message> getLogoutMethod;
    if ((getLogoutMethod = WebClientServiceGrpc.getLogoutMethod) == null) {
      synchronized (WebClientServiceGrpc.class) {
        if ((getLogoutMethod = WebClientServiceGrpc.getLogoutMethod) == null) {
          WebClientServiceGrpc.getLogoutMethod = getLogoutMethod = 
              io.grpc.MethodDescriptor.<com.vng.apigateway.WebClientServiceOuterClass.Message, com.vng.apigateway.WebClientServiceOuterClass.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.vng.apigateway.WebClientService", "logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new WebClientServiceMethodDescriptorSupplier("logout"))
                  .build();
          }
        }
     }
     return getLogoutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message,
      com.vng.apigateway.WebClientServiceOuterClass.Message> getCheckTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkToken",
      requestType = com.vng.apigateway.WebClientServiceOuterClass.Message.class,
      responseType = com.vng.apigateway.WebClientServiceOuterClass.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message,
      com.vng.apigateway.WebClientServiceOuterClass.Message> getCheckTokenMethod() {
    io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message, com.vng.apigateway.WebClientServiceOuterClass.Message> getCheckTokenMethod;
    if ((getCheckTokenMethod = WebClientServiceGrpc.getCheckTokenMethod) == null) {
      synchronized (WebClientServiceGrpc.class) {
        if ((getCheckTokenMethod = WebClientServiceGrpc.getCheckTokenMethod) == null) {
          WebClientServiceGrpc.getCheckTokenMethod = getCheckTokenMethod = 
              io.grpc.MethodDescriptor.<com.vng.apigateway.WebClientServiceOuterClass.Message, com.vng.apigateway.WebClientServiceOuterClass.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.vng.apigateway.WebClientService", "checkToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new WebClientServiceMethodDescriptorSupplier("checkToken"))
                  .build();
          }
        }
     }
     return getCheckTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message,
      com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo> getGetWebsocketInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getWebsocketInfo",
      requestType = com.vng.apigateway.WebClientServiceOuterClass.Message.class,
      responseType = com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message,
      com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo> getGetWebsocketInfoMethod() {
    io.grpc.MethodDescriptor<com.vng.apigateway.WebClientServiceOuterClass.Message, com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo> getGetWebsocketInfoMethod;
    if ((getGetWebsocketInfoMethod = WebClientServiceGrpc.getGetWebsocketInfoMethod) == null) {
      synchronized (WebClientServiceGrpc.class) {
        if ((getGetWebsocketInfoMethod = WebClientServiceGrpc.getGetWebsocketInfoMethod) == null) {
          WebClientServiceGrpc.getGetWebsocketInfoMethod = getGetWebsocketInfoMethod = 
              io.grpc.MethodDescriptor.<com.vng.apigateway.WebClientServiceOuterClass.Message, com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.vng.apigateway.WebClientService", "getWebsocketInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new WebClientServiceMethodDescriptorSupplier("getWebsocketInfo"))
                  .build();
          }
        }
     }
     return getGetWebsocketInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WebClientServiceStub newStub(io.grpc.Channel channel) {
    return new WebClientServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WebClientServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WebClientServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WebClientServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WebClientServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class WebClientServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(com.vng.apigateway.WebClientServiceOuterClass.LoginRequest request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.TokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void logout(com.vng.apigateway.WebClientServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    /**
     */
    public void checkToken(com.vng.apigateway.WebClientServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckTokenMethod(), responseObserver);
    }

    /**
     */
    public void getWebsocketInfo(com.vng.apigateway.WebClientServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getGetWebsocketInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.vng.apigateway.WebClientServiceOuterClass.LoginRequest,
                com.vng.apigateway.WebClientServiceOuterClass.TokenResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.vng.apigateway.WebClientServiceOuterClass.Message,
                com.vng.apigateway.WebClientServiceOuterClass.Message>(
                  this, METHODID_LOGOUT)))
          .addMethod(
            getCheckTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.vng.apigateway.WebClientServiceOuterClass.Message,
                com.vng.apigateway.WebClientServiceOuterClass.Message>(
                  this, METHODID_CHECK_TOKEN)))
          .addMethod(
            getGetWebsocketInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.vng.apigateway.WebClientServiceOuterClass.Message,
                com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo>(
                  this, METHODID_GET_WEBSOCKET_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class WebClientServiceStub extends io.grpc.stub.AbstractStub<WebClientServiceStub> {
    private WebClientServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WebClientServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebClientServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WebClientServiceStub(channel, callOptions);
    }

    /**
     */
    public void login(com.vng.apigateway.WebClientServiceOuterClass.LoginRequest request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.TokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(com.vng.apigateway.WebClientServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.Message> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkToken(com.vng.apigateway.WebClientServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.Message> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getWebsocketInfo(com.vng.apigateway.WebClientServiceOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetWebsocketInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WebClientServiceBlockingStub extends io.grpc.stub.AbstractStub<WebClientServiceBlockingStub> {
    private WebClientServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WebClientServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebClientServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WebClientServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.vng.apigateway.WebClientServiceOuterClass.TokenResponse login(com.vng.apigateway.WebClientServiceOuterClass.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.vng.apigateway.WebClientServiceOuterClass.Message logout(com.vng.apigateway.WebClientServiceOuterClass.Message request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.vng.apigateway.WebClientServiceOuterClass.Message checkToken(com.vng.apigateway.WebClientServiceOuterClass.Message request) {
      return blockingUnaryCall(
          getChannel(), getCheckTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo getWebsocketInfo(com.vng.apigateway.WebClientServiceOuterClass.Message request) {
      return blockingUnaryCall(
          getChannel(), getGetWebsocketInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WebClientServiceFutureStub extends io.grpc.stub.AbstractStub<WebClientServiceFutureStub> {
    private WebClientServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WebClientServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebClientServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WebClientServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vng.apigateway.WebClientServiceOuterClass.TokenResponse> login(
        com.vng.apigateway.WebClientServiceOuterClass.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vng.apigateway.WebClientServiceOuterClass.Message> logout(
        com.vng.apigateway.WebClientServiceOuterClass.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vng.apigateway.WebClientServiceOuterClass.Message> checkToken(
        com.vng.apigateway.WebClientServiceOuterClass.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo> getWebsocketInfo(
        com.vng.apigateway.WebClientServiceOuterClass.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getGetWebsocketInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_LOGOUT = 1;
  private static final int METHODID_CHECK_TOKEN = 2;
  private static final int METHODID_GET_WEBSOCKET_INFO = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WebClientServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WebClientServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.vng.apigateway.WebClientServiceOuterClass.LoginRequest) request,
              (io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.TokenResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((com.vng.apigateway.WebClientServiceOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.Message>) responseObserver);
          break;
        case METHODID_CHECK_TOKEN:
          serviceImpl.checkToken((com.vng.apigateway.WebClientServiceOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.Message>) responseObserver);
          break;
        case METHODID_GET_WEBSOCKET_INFO:
          serviceImpl.getWebsocketInfo((com.vng.apigateway.WebClientServiceOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<com.vng.apigateway.WebClientServiceOuterClass.WebsocketInfo>) responseObserver);
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

  private static abstract class WebClientServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WebClientServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.vng.apigateway.WebClientServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WebClientService");
    }
  }

  private static final class WebClientServiceFileDescriptorSupplier
      extends WebClientServiceBaseDescriptorSupplier {
    WebClientServiceFileDescriptorSupplier() {}
  }

  private static final class WebClientServiceMethodDescriptorSupplier
      extends WebClientServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WebClientServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (WebClientServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WebClientServiceFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getLogoutMethod())
              .addMethod(getCheckTokenMethod())
              .addMethod(getGetWebsocketInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
