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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * {@code GrpcClientRunner} starts the grpc client during application startup.
 * 
 * @author Debdyut Hajra
 *
 */
@Profile("!test")
@Component
public class GrpcClientRunner implements CommandLineRunner {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	GrpcClient client;

	@Override
	public void run(String... args) {
		client.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				client.shutdown();
			} catch (InterruptedException e) {
				LOG.error("Client stopped with error: {}", e.getMessage());
			}
		}));
	}
}
