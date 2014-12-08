
package com.pay.async.impl;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.pay.async.AsyncExecuteService;
import com.pay.async.exception.AsyncExecuteServiceException;
import com.pay.async.task.AsyncTask;

public class AsyncExecuteServiceImpl implements AsyncExecuteService {

	private ThreadPoolExecutor threadPoolExecutor;
	
	private int corePoolSize;
	private int maximumPoolSize;
	private int keepAliveTime; // 当超过核心池数量时，工作线程无任务的等待时间，单位S
	private int workQueueSize;
	private RejectedExecutionHandler rejectHandler;
	
	
	public AsyncExecuteServiceImpl(Properties config){
		if (config == null){
			throw new AsyncExecuteServiceException("异步服务初始化参数为空");
		}
		
		this.corePoolSize = Integer.parseInt(config.getProperty("aes_core_pool_size",String.valueOf(Runtime.getRuntime().availableProcessors())));
		this.maximumPoolSize = Integer.parseInt(config.getProperty("aes_max_pool_size",String.valueOf(Runtime.getRuntime().availableProcessors() * 2)));
		this.keepAliveTime = Integer.parseInt(config.getProperty("aes_keep_alive_time",String.valueOf(60*5)));
		this.workQueueSize = Integer.parseInt(config.getProperty("aes_work_queue_size",String.valueOf(10000)));
	}
	
	
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public void setWorkQueueSize(int workQueueSize) {
		this.workQueueSize = workQueueSize;
	}

	public void setRejectHandler(RejectedExecutionHandler rejectHandler) {
		this.rejectHandler = rejectHandler;
	}

	@Override
	public <V> Future<V> submitTask(AsyncTask<V> asyncTask) {
		return threadPoolExecutor.submit(asyncTask);
	}

	@Override
	public void stop() {
		threadPoolExecutor.shutdown();
	}

	@Override
	public void start() {
		if (corePoolSize <= 0 || maximumPoolSize <= 0 || keepAliveTime <=0 || workQueueSize <= 0){
			throw new AsyncExecuteServiceException("AsyncExecuteService初始化失败，参数corePoolSize、maximumPoolSize、keepAliveTime、workQueueSize需大于0");
		}
		
		maximumPoolSize = Math.max(corePoolSize, maximumPoolSize);
		
		if (rejectHandler == null){
			rejectHandler = new ThreadPoolExecutor.CallerRunsPolicy();
		}
		
		threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(workQueueSize), rejectHandler);
		
	}


	@Override
	public int getWorkerSize() {
		return threadPoolExecutor.getActiveCount();
	}

	@Override
	public int getWaitTaskSize() {
		return threadPoolExecutor.getQueue().size();
	}
	

	@Override
	public long getCompletedTaskCount() {
		return threadPoolExecutor.getCompletedTaskCount();
	}
	
	@Override
	public boolean isRunning() {
		return !threadPoolExecutor.isShutdown();
	}
	



}
