package com.pay.async;


/**
 * 
 * 异步服务工厂类
 * 2013-7-30-上午11:15:33
 * @author wuxin
 * @version 1.0.0
 * 
 */
public class AsyncExecuteServiceFactory {
	private AsyncExecuteService service;
	private static AsyncExecuteServiceFactory factory = new AsyncExecuteServiceFactory();
	private AsyncExecuteServiceFactory() {
	}
	
	public static AsyncExecuteServiceFactory getInstance(){
		return factory;
	}

	
	public void setAsyncExecuteService(AsyncExecuteService service){
		this.service = service;
	}
	
	public AsyncExecuteService getService(){
		return service;
	}
	
	
}
