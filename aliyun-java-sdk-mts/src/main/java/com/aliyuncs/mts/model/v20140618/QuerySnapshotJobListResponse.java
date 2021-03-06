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
package com.aliyuncs.mts.model.v20140618;

import java.util.List;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.mts.transform.v20140618.QuerySnapshotJobListResponseUnmarshaller;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @author auto create
 * @version 
 */
public class QuerySnapshotJobListResponse extends AcsResponse {

	private String requestId;

	private List<SnapshotJob> snapshotJobList;

	private List<String> nonExistSnapshotJobIds;

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<SnapshotJob> getSnapshotJobList() {
		return this.snapshotJobList;
	}

	public void setSnapshotJobList(List<SnapshotJob> snapshotJobList) {
		this.snapshotJobList = snapshotJobList;
	}

	public List<String> getNonExistSnapshotJobIds() {
		return this.nonExistSnapshotJobIds;
	}

	public void setNonExistSnapshotJobIds(List<String> nonExistSnapshotJobIds) {
		this.nonExistSnapshotJobIds = nonExistSnapshotJobIds;
	}

	public static class SnapshotJob {

		private String id;

		private String userData;

		private String state;

		private String code;

		private String message;

		private String creationTime;

		private Input input;

		private SnapshotConfig snapshotConfig;

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserData() {
			return this.userData;
		}

		public void setUserData(String userData) {
			this.userData = userData;
		}

		public String getState() {
			return this.state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCode() {
			return this.code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getCreationTime() {
			return this.creationTime;
		}

		public void setCreationTime(String creationTime) {
			this.creationTime = creationTime;
		}

		public Input getInput() {
			return this.input;
		}

		public void setInput(Input input) {
			this.input = input;
		}

		public SnapshotConfig getSnapshotConfig() {
			return this.snapshotConfig;
		}

		public void setSnapshotConfig(SnapshotConfig snapshotConfig) {
			this.snapshotConfig = snapshotConfig;
		}

		public static class Input {

			private String bucket;

			private String location;

			private String object;

			public String getBucket() {
				return this.bucket;
			}

			public void setBucket(String bucket) {
				this.bucket = bucket;
			}

			public String getLocation() {
				return this.location;
			}

			public void setLocation(String location) {
				this.location = location;
			}

			public String getObject() {
				return this.object;
			}

			public void setObject(String object) {
				this.object = object;
			}
		}

		public static class SnapshotConfig {

			private String time;

			private OutputFile outputFile;

			public String getTime() {
				return this.time;
			}

			public void setTime(String time) {
				this.time = time;
			}

			public OutputFile getOutputFile() {
				return this.outputFile;
			}

			public void setOutputFile(OutputFile outputFile) {
				this.outputFile = outputFile;
			}

			public static class OutputFile {

				private String bucket;

				private String location;

				private String object;

				public String getBucket() {
					return this.bucket;
				}

				public void setBucket(String bucket) {
					this.bucket = bucket;
				}

				public String getLocation() {
					return this.location;
				}

				public void setLocation(String location) {
					this.location = location;
				}

				public String getObject() {
					return this.object;
				}

				public void setObject(String object) {
					this.object = object;
				}
			}
		}
	}

	@Override
	public QuerySnapshotJobListResponse getInstance(UnmarshallerContext context) {
		return	QuerySnapshotJobListResponseUnmarshaller.unmarshall(this, context);
	}
}
