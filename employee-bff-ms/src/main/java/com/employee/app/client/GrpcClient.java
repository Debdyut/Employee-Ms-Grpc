/*
 * Copyright 2021-2021 the original author or authors.
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

/**
 * {@code GrpcClient} provides mechanisms to connect to grpc server.
 * 
 * @author Debdyut Hajra
 *
 */
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
