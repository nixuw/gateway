package com.pay.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.pay.httpclient.config.HttpClientConfig;

public class SimpleOCCHttpClient implements OCCHttpClient {

	private HttpClientFactory clientFactory;
	private HttpContextFactory contextFactory;

	public SimpleOCCHttpClient(HttpClientConfig config) {
		this.clientFactory = new HttpClientFactory(config);
		this.contextFactory = new HttpContextFactory(config);
	}

	public String get(String url) throws OCCHttpException {
		if (StringUtils.isEmpty(url)) {
			throw new OCCHttpException("url is null");
		}

		CloseableHttpClient client = clientFactory.getHttpClient();

		String response;
		try {
			response = client.execute(new HttpGet(url),
					new BasicResponseHandler(),
					contextFactory.createHttpContext());
		} catch (Throwable t) {
			throw new OCCHttpException("get 请求:" + url + " 出现异常", t);
		}

		return response;
	}

	public String post(String url, Map<String, String> params)
			throws OCCHttpException {
		return post(url, params, null);
	}

	public String post(String url, Map<String, String> params,
			Map<String, String> header) throws OCCHttpException {
		if (StringUtils.isEmpty(url)) {
			throw new OCCHttpException("url is null");
		}

		HttpPost httppost = new HttpPost(url);

		// 处理post参数
		if (params != null && params.size() > 0) {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();

			for (Iterator<String> iterator = params.keySet().iterator(); iterator
					.hasNext();) {
				String name = iterator.next();
				formparams.add(new BasicNameValuePair(name, params.get(name)));
			}

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					Consts.UTF_8);
			httppost.setEntity(entity);
		}

		// 处理请求头
		if (header != null && header.size() > 0) {

			for (Iterator<String> iterator = params.keySet().iterator(); iterator
					.hasNext();) {
				String name = iterator.next();
				httppost.addHeader(name, header.get(name));
			}
		}

		CloseableHttpClient client = clientFactory.getHttpClient();

		String response;
		try {
			response = client.execute(httppost, new BasicResponseHandler(),
					contextFactory.createHttpContext());
		} catch (Throwable t) {
			throw new OCCHttpException("post 请求:" + url + " 出现异常", t);
		}

		return response;
	}

	public void close() throws IOException {
		if (clientFactory != null) {
			clientFactory.close();
		}

	}

}
