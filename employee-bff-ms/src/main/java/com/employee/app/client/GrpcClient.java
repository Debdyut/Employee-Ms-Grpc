package com.employee.app.client;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.employee.app.grpc.EmployeeServiceGrpc;
import com.employee.app.grpc.EmployeeServiceGrpc.EmployeeServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class GrpcClient {

	private static final Logger LOG = LoggerFactory.getLogger(GrpcClient.class);
	@Value("${grpc.server.host:localhost}")
	private String host;

	@Value("${grpc.server.port:8080}")
	private int port;

	private ManagedChannel channel;
	private EmployeeServiceBlockingStub employeeServiceStub;

	public void start() {
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		employeeServiceStub = EmployeeServiceGrpc.newBlockingStub(channel);
		LOG.info("gRPC client connected to {}:{}", host, port);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
		LOG.info("gRPC client disconnected successfully.");
	}

	public EmployeeServiceBlockingStub getSourceServiceStub() {
		return this.employeeServiceStub;
	}
}
