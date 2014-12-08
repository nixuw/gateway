package com.pay.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;

import com.pay.httpclient.config.HttpClientConfig;

public class HttpContextFactory {
	private HttpClientConfig config ;
	
	public HttpContextFactory(HttpClientConfig config){
		this.config = config;
	}
	public HttpClientContext createHttpContext() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(config.getConnectionRequestTimeout() * 1000)
				.setConnectTimeout(config.getConnectTimeout()  * 1000).build();

		HttpClientContext context = HttpClientContext.create();
		context.setRequestConfig(requestConfig);
		return context;
	}

}
