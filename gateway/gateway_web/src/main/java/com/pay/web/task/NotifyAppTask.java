package com.pay.web.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.pay.constant.RedisConstants;
import com.pay.exception.NotifyAppException;
import com.pay.service.AppNotifyService;

public class NotifyAppTask {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NotifyAppTask.class);

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private AppNotifyService notifyService;

	private int batchSize = 10;

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		if (logger.isInfoEnabled()) {
			logger.info("开始执行通知应用系统任务");
		}

		for (int i = 0; i < batchSize; i++) {
			String orderNo = (String)redisTemplate.execute(new RedisCallback<String>() {

				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					byte[] b = connection.lPop(redisTemplate
							.getStringSerializer().serialize(
									RedisConstants.NOTIFY_APP_QUEUE_NAME));
					return (String) redisTemplate.getStringSerializer()
							.deserialize(b);
				}
			});
			
			if (orderNo == null){
				break;
			}else{
				try {
					notifyService.notify(orderNo);
				} catch (NotifyAppException e) {
					logger.error("通知应用失败,订单号:" + orderNo,e);
				}
			}
			
		}
		
		

	}
}
