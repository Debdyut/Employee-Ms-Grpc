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

package com.employee.app.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.employee.app.interceptor.ExceptionInterceptor;
import com.employee.app.service.impl.EmployeeService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * 
 * {@code GrpcServer} handles starting and stopping of grpc server.
 * 
 * @author Debdyut Hajra
 *
 */
@Component
public class GrpcServer {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Value("${grpc.port:8080}")
	private int port;

	private Server server;
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ExceptionInterceptor exceptionInterceptor;

	public void start() throws IOException, InterruptedException {
		LOG.info("gRPC server is starting on port: {}.", port);
		server = ServerBuilder.forPort(port).addService(employeeService).intercept(exceptionInterceptor).build()
				.start();
		LOG.info("gRPC server started and listening on port: {}.", port);
		LOG.info("Following service are available: ");
		server.getServices().stream().forEach(s -> LOG.info("Service Name: {}", s.getServiceDescriptor().getName()));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOG.info("Shutting down gRPC server.");
			GrpcServer.this.stop();
			LOG.info("gRPC server shut down successfully.");
		}));
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	public void block() throws InterruptedException {
		if (server != null) {
			// received the request until application is terminated
			server.awaitTermination();
		}
	}

}
