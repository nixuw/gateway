package com.pay.httpclient;

import org.apache.http.HttpException;

public class OCCHttpException extends HttpException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OCCHttpException() {
		super();
	}
	
	public OCCHttpException(final String message){
		super(message);
	}
	
	public OCCHttpException(final String message, final Throwable cause){
		  super(message);
	        initCause(cause);
	}
	
	public OCCHttpException(final Throwable cause){
	        initCause(cause);
	}

}
