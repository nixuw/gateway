package com.pay.httpclient;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.pay.httpclient.config.HttpClientConfig;

/**
 * 创建单例的httpclient, httpclient线程安全
 * 当不再需要时,需要关闭(全局行为)
 * 
 * @author wuxin
 *
 */
public class HttpClientFactory implements Closeable {

	private CloseableHttpClient httpClient;
	private PoolingHttpClientConnectionManager cm;

	private HttpClientConfig config;

	public HttpClientFactory(HttpClientConfig config) {
		this.config = config;
		init();
	}

	public CloseableHttpClient getHttpClient() {
		return this.httpClient;
	}

	private void init() {
		cm = new PoolingHttpClientConnectionManager();
		cm.setDefaultMaxPerRoute(config.getMaxPerRoute()); // 每个域最大的连接数
		cm.setMaxTotal(config.getMaxTotal()); // 所有域最大连接数

		SocketConfig sc = SocketConfig.custom()
				.setSoTimeout(config.getSocketTimeout() * 1000).build();
		cm.setDefaultSocketConfig(sc);

		this.httpClient = HttpClients.custom().setConnectionManager(cm).build();

		// 启动连接管理进程
		new ManagerBadConnection().start();
	}

	private boolean isClose() {
		return httpClient == null;
	}

	public void close() throws IOException {
		if (httpClient != null) {

			try {
				httpClient.close();
			} catch (Throwable t) {
			} finally {
				httpClient = null;
			}
		}
	}

	/**
	 * 管理无效的连接
	 *
	 */
	private class ManagerBadConnection {
		private Thread taskThread;

		ManagerBadConnection() {
			taskThread = new Thread(cleanBadConnTask());
			taskThread.setDaemon(true);
			taskThread.setName("HttpClientFactory_con_manager");

		}

		private void start() {
			taskThread.start();
		}

		/**
		 * 删除无效连接任务
		 * 
		 * @return
		 */
		private Runnable cleanBadConnTask() {
			return new Runnable() {

				public void run() {

					while (!isClose()) {
						try {
							Thread.sleep(config.getCleanBadConnInterval() * 1000);

							cm.closeIdleConnections(config.getIdleTimeout(),
									TimeUnit.SECONDS);
							cm.closeExpiredConnections();
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}

				}

			};
		}

	}

}
