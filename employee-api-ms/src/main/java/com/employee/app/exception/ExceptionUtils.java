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

package com.employee.app.exception;

import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;

import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;

/**
 * 
 * {@code ExceptionUtils} handles the grpc exception response
 * 
 * @author Debdyut Hajra
 *
 */
@Component
public class ExceptionUtils {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionUtils.class);

	public static StatusRuntimeException traceException(Throwable e) {
		return traceException(e, null);
	}

	public static <T extends com.google.protobuf.GeneratedMessageV3> StatusRuntimeException traceException(Throwable e,
			T defaultInstance) {
		com.google.rpc.Status status;
		StatusRuntimeException statusRuntimeException;
		if (e instanceof StatusRuntimeException) {
			statusRuntimeException = (StatusRuntimeException) e;
		} else {
			Throwable cause = e;
			if (cause != null && cause.getCause() != null && cause.getCause() != cause) {
				cause = cause.getCause();
			}
			if (cause instanceof SocketException) {
				String errorMessage = "Socket exception: ";
				LOG.info(errorMessage + "{}", e.getMessage());
				status = com.google.rpc.Status.newBuilder().setCode(com.google.rpc.Code.UNAVAILABLE_VALUE)
						.setMessage(errorMessage + cause.getMessage()).addDetails(Any.pack(defaultInstance)).build();
			} else if (cause instanceof FileNotFoundException) {
				String errorMessage = "File not found exception: ";
				LOG.info(errorMessage + "{}", e.getMessage());
				status = com.google.rpc.Status.newBuilder().setCode(com.google.rpc.Code.NOT_FOUND_VALUE)
						.setMessage(errorMessage + cause.getMessage()).addDetails(Any.pack(defaultInstance)).build();
			} else if (cause instanceof StorageException) {
				String errorMessage = "Storage exception: ";
				LOG.info(errorMessage + "{}", e.getMessage());
				status = com.google.rpc.Status.newBuilder().setCode(com.google.rpc.Code.INVALID_ARGUMENT_VALUE)
						.setMessage(errorMessage + cause.getMessage()).addDetails(Any.pack(defaultInstance)).build();
			} else {
				status = com.google.rpc.Status.newBuilder().setCode(com.google.rpc.Code.INTERNAL_VALUE)
						.setMessage("Internal server error").addDetails(Any.pack(defaultInstance)).build();
			}
			LOG.error("Error message {}", e.getMessage());
			statusRuntimeException = StatusProto.toStatusRuntimeException(status);
		}
		return statusRuntimeException;
	}

	public static <T extends GeneratedMessageV3> void observeError(StreamObserver<T> responseObserver, Throwable e) {
		responseObserver.onError(traceException(e));
	}

	public static <T extends GeneratedMessageV3> void observeError(StreamObserver<T> responseObserver, Exception e,
			T defaultInstance) {
		responseObserver.onError(traceException(e, defaultInstance));
	}
}
