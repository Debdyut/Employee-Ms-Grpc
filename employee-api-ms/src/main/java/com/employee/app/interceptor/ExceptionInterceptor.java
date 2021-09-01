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

package com.employee.app.interceptor;


import org.springframework.stereotype.Component;

import com.employee.app.exception.ExceptionUtils;

import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.StatusRuntimeException;

/**
 * 
 * {@code ExceptionInterceptor} intercepts and handles grpc exceptions
 * 
 * @author Debdyut Hajra
 *
 */
@Component
public class ExceptionInterceptor implements ServerInterceptor {

	@Override
	// RQT - Request Type; RST - Response Type
	public <RQT, RST> ServerCall.Listener<RQT> interceptCall(ServerCall<RQT, RST> serverCall, Metadata metadata,
			ServerCallHandler<RQT, RST> serverCallHandler) {
		ServerCall.Listener<RQT> listener = serverCallHandler.startCall(serverCall, metadata);
		return new ExceptionHandlingServerCallListener<>(listener, serverCall, metadata);
	}

	private class ExceptionHandlingServerCallListener<RQT, RST>
			extends ForwardingServerCallListener.SimpleForwardingServerCallListener<RQT> {

		private final ServerCall<RQT, RST> serverCall;
		private final Metadata metadata;

		ExceptionHandlingServerCallListener(ServerCall.Listener<RQT> listener, ServerCall<RQT, RST> serverCall,
				Metadata metadata) {
			super(listener);
			this.serverCall = serverCall;
			this.metadata = metadata;
		}

		@Override
		public void onHalfClose() {
			try {
				super.onHalfClose();
			} catch (RuntimeException e) {
				handleException(e, serverCall, metadata);
				throw e;
			}
		}

		@Override
		public void onReady() {
			try {
				super.onReady();
			} catch (RuntimeException e) {
				handleException(e, serverCall, metadata);
				throw e;
			}
		}

		private void handleException(RuntimeException e, ServerCall<RQT, RST> serverCall, Metadata metadata) {
			StatusRuntimeException status = ExceptionUtils.traceException(e);
			serverCall.close(status.getStatus(), metadata);
		}
	}
}
