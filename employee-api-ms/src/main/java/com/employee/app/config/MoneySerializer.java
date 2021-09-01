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

package com.employee.app.config;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * {@code MoneySerializer} defines how to serialize the salary
 * field while saving to file.
 * 
 * @author Debdyut Hajra
 *
 */
public class MoneySerializer extends JsonSerializer<Double> {
	
	/**
	 * 
	 * {@code serialize} method defines how to serialize the salary
	 * field while saving to file.
	 * 
	 * @author Debdyut Hajra
	 * 
	 */
	@Override
    public void serialize(
    		Double value, 
    		JsonGenerator jgen, 
    		SerializerProvider provider) 
    				throws IOException, JsonProcessingException 
	{        
        jgen.writeNumber(new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    }
	
}
