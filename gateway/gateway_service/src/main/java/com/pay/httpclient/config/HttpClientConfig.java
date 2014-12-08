package com.pay.httpclient.config;

public class HttpClientConfig {
	private int maxPerRoute = 20;
	private int maxTotal = 200;
	private int socketTimeout = 10; // 单位:秒
	private int idleTimeout= 10; // 单位:秒
	
	private int cleanBadConnInterval = 10; // 删除无效连接间隔    单位:秒
	
	private int connectionRequestTimeout = 10; // 单位秒
	private int connectTimeout = 30; // 单位秒
	public int getMaxPerRoute() {
		return maxPerRoute;
	}
	public void setMaxPerRoute(int maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public int getSocketTimeout() {
		return socketTimeout;
	}
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	public int getIdleTimeout() {
		return idleTimeout;
	}
	public void setIdleTimeout(int idleTimeout) {
		this.idleTimeout = idleTimeout;
	}
	public int getCleanBadConnInterval() {
		return cleanBadConnInterval;
	}
	public void setCleanBadConnInterval(int cleanBadConnInterval) {
		this.cleanBadConnInterval = cleanBadConnInterval;
	}
	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}
	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	
	
	
	
}
