package com.pay.httpclient;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

public interface OCCHttpClient extends Closeable {
	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws OCCHttpException
	 */
	public String get(String url) throws OCCHttpException;

	/**
	 * post请求
	 * 
	 * @param url
	 * @param params 请求参数 key,value
	 * @return
	 * @throws IOException
	 * @throws OCCHttpException
	 */
	public String post(String url, Map<String, String> params)
			throws OCCHttpException;

	/**
	 * post请求
	 * @param url
	 * @param params 请求参数 key,value
	 * @param header  请求头 key,value
	 * @return
	 * @throws OCCHttpException
	 */
	public String post(String url, Map<String, String> params,Map<String, String> header) throws OCCHttpException;
			

}
