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
package com.aliyuncs.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Map.Entry;

import com.aliyuncs.utils.ParameterHelper;

public class HttpRequest {
	
	protected static final String CONTENT_TYPE = "Content-Type";
	protected static final String CONTENT_MD5 = "Content-MD5";
	protected static final String CONTENT_LENGTH ="Content-Length";
	
	private String url = null;
	private MethodType method = null;
	protected FormatType contentType = null;
	protected byte[] content = null;
	protected String encoding = null;
	protected Map<String, String> headers = null;
	
	public HttpRequest(String strUrl) {
		this.url = strUrl;
		this.headers = new HashMap<String, String>();
	}
	
	public HttpRequest(String strUrl, Map<String, String> tmpHeaders) {
		this.url = strUrl;
		if (null != tmpHeaders)
			this.headers = tmpHeaders;
	}
	
	public HttpRequest() {
	}
	
	public String getUrl() {
		return url;
	}

	protected void setUrl(String url) {
		this.url = url;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public FormatType getContentType() {
		return contentType;
	}
	
	public void setContentType(FormatType contentType) {
		this.contentType = contentType;
		if (null != this.content || null != contentType) {
			this.headers.put(CONTENT_TYPE, getContentTypeValue(this.contentType, this.encoding));
		}
		else { 
			this.headers.remove(CONTENT_TYPE);
		}
	}
	
	public MethodType getMethod() {
		return method;
	}
	
	public void setMethod(MethodType method) {
		this.method = method;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public String getHeaderValue(String name) {
		return this.headers.get(name);
	}
	
	public void putHeaderParameter(String name, String value) {
		if (null != name && null != value)
			this.headers.put(name, value);
	}
	
	public void setContent(byte[] content, String encoding, FormatType format) {
		
		if (null == content) {
			this.headers.remove(CONTENT_MD5);
			this.headers.remove(CONTENT_LENGTH);
			this.headers.remove(CONTENT_TYPE);
			this.contentType = null;
			this.content = null;
			this.encoding = null;
			return;
		}
		this.content = content;
		this.encoding = encoding;
		String contentLen =String.valueOf(content.length);
		String strMd5 = ParameterHelper.md5Sum(content);
		if (null != format) {
			this.contentType = format;
		}
		else {
			this.contentType = FormatType.RAW;
		}
		this.headers.put(CONTENT_MD5, strMd5);
		this.headers.put(CONTENT_LENGTH, contentLen);
		this.headers.put(CONTENT_TYPE, getContentTypeValue(contentType, encoding));
	}
	
	public Map<String, String> getHeaders() {
		return Collections.unmodifiableMap(headers);
	}
	
	public HttpURLConnection getHttpConnection() throws IOException {
		Map<String, String> mappedHeaders = this.headers;
		String strUrl = url;
		if (null == strUrl || null == this.method){
			return null;
		}
		URL url = null;
		String[] urlArray = null;
		if(MethodType.POST.equals(this.method) && null == getContent()){
			urlArray =	strUrl.split("\\?");
			url = new URL(urlArray[0]); 
		}
		else {
			url = new URL(strUrl);
		}
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod(this.method.toString());
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setUseCaches(false);
	
		for(Entry<String, String> entry: mappedHeaders.entrySet()) {
			httpConn.setRequestProperty(entry.getKey(), entry.getValue());
		}

		if(null != getHeaderValue(CONTENT_TYPE)){
			httpConn.setRequestProperty(CONTENT_TYPE, getHeaderValue(CONTENT_TYPE));
		}
		else {
			String contentTypeValue = getContentTypeValue(contentType, encoding);
			if(null != contentTypeValue){
				httpConn.setRequestProperty(CONTENT_TYPE, contentTypeValue);
			}
		}
		
		if(MethodType.POST.equals(this.method) && null != urlArray && urlArray.length == 2){
			httpConn.getOutputStream().write(urlArray[1].getBytes());
		}
		return httpConn;
	}
	
	private String getContentTypeValue(FormatType contentType, String encoding) {
		if(null != contentType && null != encoding){
			return FormatType.mapFormatToAccept(contentType) + 
					";charset=" + encoding.toLowerCase();
		}
		else if (null != contentType) {
			return FormatType.mapFormatToAccept(contentType);
		}
		return null;
	}
	
}
