/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aliyuncs.cms.transform.v20150801;

import java.util.ArrayList;
import java.util.List;

import com.aliyuncs.cms.model.v20150801.ListMetricStreamResponse;
import com.aliyuncs.transform.UnmarshallerContext;


public class ListMetricStreamResponseUnmarshaller {

	public static ListMetricStreamResponse unmarshall(ListMetricStreamResponse listMetricStreamResponse, UnmarshallerContext context) {
		
		listMetricStreamResponse.setCode(context.stringValue("ListMetricStreamResponse.Code"));
		listMetricStreamResponse.setMessage(context.stringValue("ListMetricStreamResponse.Message"));
		listMetricStreamResponse.setSuccess(context.stringValue("ListMetricStreamResponse.Success"));
		listMetricStreamResponse.setTraceId(context.stringValue("ListMetricStreamResponse.TraceId"));

		List<String> datapoints = new ArrayList<String>();
		for (int i = 0; i < context.lengthValue("ListMetricStreamResponse.Datapoints.Length"); i++) {
			datapoints.add(context.stringValue("ListMetricStreamResponse.Datapoints["+ i +"]"));
		}
		listMetricStreamResponse.setDatapoints(datapoints);
	 
	 	return listMetricStreamResponse;
	}
}