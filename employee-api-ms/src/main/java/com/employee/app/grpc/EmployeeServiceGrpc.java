/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.employee.app.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.1)",
    comments = "Source: EmployeeService.proto")
public final class EmployeeServiceGrpc {

  private EmployeeServiceGrpc() {}

  public static final String SERVICE_NAME = "com.employee.v1.EmployeeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.employee.app.grpc.EmployeeRequest,
      com.employee.app.grpc.EmployeeRequest.Response> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Create",
      requestType = com.employee.app.grpc.EmployeeRequest.class,
      responseType = com.employee.app.grpc.EmployeeRequest.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.employee.app.grpc.EmployeeRequest,
      com.employee.app.grpc.EmployeeRequest.Response> getCreateMethod() {
    io.grpc.MethodDescriptor<com.employee.app.grpc.EmployeeRequest, com.employee.app.grpc.EmployeeRequest.Response> getCreateMethod;
    if ((getCreateMethod = EmployeeServiceGrpc.getCreateMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getCreateMethod = EmployeeServiceGrpc.getCreateMethod) == null) {
          EmployeeServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<com.employee.app.grpc.EmployeeRequest, com.employee.app.grpc.EmployeeRequest.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.employee.app.grpc.EmployeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.employee.app.grpc.EmployeeRequest.Response.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("Create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.employee.app.grpc.EmployeeRequest,
      com.employee.app.grpc.EmployeeRequest.Response> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Update",
      requestType = com.employee.app.grpc.EmployeeRequest.class,
      responseType = com.employee.app.grpc.EmployeeRequest.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.employee.app.grpc.EmployeeRequest,
      com.employee.app.grpc.EmployeeRequest.Response> getUpdateMethod() {
    io.grpc.MethodDescriptor<com.employee.app.grpc.EmployeeRequest, com.employee.app.grpc.EmployeeRequest.Response> getUpdateMethod;
    if ((getUpdateMethod = EmployeeServiceGrpc.getUpdateMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getUpdateMethod = EmployeeServiceGrpc.getUpdateMethod) == null) {
          EmployeeServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<com.employee.app.grpc.EmployeeRequest, com.employee.app.grpc.EmployeeRequest.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.employee.app.grpc.EmployeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.employee.app.grpc.EmployeeRequest.Response.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("Update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmployeeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceStub>() {
        @java.lang.Override
        public EmployeeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmployeeServiceStub(channel, callOptions);
        }
      };
    return EmployeeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmployeeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceBlockingStub>() {
        @java.lang.Override
        public EmployeeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmployeeServiceBlockingStub(channel, callOptions);
        }
      };
    return EmployeeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmployeeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceFutureStub>() {
        @java.lang.Override
        public EmployeeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmployeeServiceFutureStub(channel, callOptions);
        }
      };
    return EmployeeServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class EmployeeServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Creates a new Employee collection.
     * </pre>
     */
    public void create(com.employee.app.grpc.EmployeeRequest request,
        io.grpc.stub.StreamObserver<com.employee.app.grpc.EmployeeRequest.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Updates an existing Employee collection.
     * </pre>
     */
    public void update(com.employee.app.grpc.EmployeeRequest request,
        io.grpc.stub.StreamObserver<com.employee.app.grpc.EmployeeRequest.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.employee.app.grpc.EmployeeRequest,
                com.employee.app.grpc.EmployeeRequest.Response>(
                  this, METHODID_CREATE)))
          .addMethod(
            getUpdateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.employee.app.grpc.EmployeeRequest,
                com.employee.app.grpc.EmployeeRequest.Response>(
                  this, METHODID_UPDATE)))
          .build();
    }
  }

  /**
   */
  public static final class EmployeeServiceStub extends io.grpc.stub.AbstractAsyncStub<EmployeeServiceStub> {
    private EmployeeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmployeeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmployeeServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new Employee collection.
     * </pre>
     */
    public void create(com.employee.app.grpc.EmployeeRequest request,
        io.grpc.stub.StreamObserver<com.employee.app.grpc.EmployeeRequest.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Updates an existing Employee collection.
     * </pre>
     */
    public void update(com.employee.app.grpc.EmployeeRequest request,
        io.grpc.stub.StreamObserver<com.employee.app.grpc.EmployeeRequest.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EmployeeServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<EmployeeServiceBlockingStub> {
    private EmployeeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmployeeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmployeeServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new Employee collection.
     * </pre>
     */
    public com.employee.app.grpc.EmployeeRequest.Response create(com.employee.app.grpc.EmployeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Updates an existing Employee collection.
     * </pre>
     */
    public com.employee.app.grpc.EmployeeRequest.Response update(com.employee.app.grpc.EmployeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EmployeeServiceFutureStub extends io.grpc.stub.AbstractFutureStub<EmployeeServiceFutureStub> {
    private EmployeeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmployeeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmployeeServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new Employee collection.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.employee.app.grpc.EmployeeRequest.Response> create(
        com.employee.app.grpc.EmployeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Updates an existing Employee collection.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.employee.app.grpc.EmployeeRequest.Response> update(
        com.employee.app.grpc.EmployeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_UPDATE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EmployeeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EmployeeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((com.employee.app.grpc.EmployeeRequest) request,
              (io.grpc.stub.StreamObserver<com.employee.app.grpc.EmployeeRequest.Response>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((com.employee.app.grpc.EmployeeRequest) request,
              (io.grpc.stub.StreamObserver<com.employee.app.grpc.EmployeeRequest.Response>) responseObserver);
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

  private static abstract class EmployeeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EmployeeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.employee.app.grpc.EmployeeServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EmployeeService");
    }
  }

  private static final class EmployeeServiceFileDescriptorSupplier
      extends EmployeeServiceBaseDescriptorSupplier {
    EmployeeServiceFileDescriptorSupplier() {}
  }

  private static final class EmployeeServiceMethodDescriptorSupplier
      extends EmployeeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EmployeeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (EmployeeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmployeeServiceFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getUpdateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
