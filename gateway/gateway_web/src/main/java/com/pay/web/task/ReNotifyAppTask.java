package com.pay.web.task;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.pay.constant.RedisConstants;
import com.pay.dao.entity.GatewayAppNotify;
import com.pay.exception.NotifyAppException;
import com.pay.service.AppNotifyService;

/**
 * 补偿通知
 * 未通知或通知应用失败后重新通知
 * @author wuxin
 *
 */
public class ReNotifyAppTask {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReNotifyAppTask.class);

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private AppNotifyService notifyService;

	private int batchSize = 100;

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		if (logger.isInfoEnabled()) {
			logger.info("开始执行补偿通知应用系统任务");
		}
		List<GatewayAppNotify> failedList = notifyService.queryFailed(batchSize);
		for (Iterator<GatewayAppNotify> iterator = failedList.iterator(); iterator.hasNext();) {
			GatewayAppNotify gatewayAppNotify = (GatewayAppNotify) iterator.next();
			
			if (gatewayAppNotify != null && gatewayAppNotify.getGatewayOrderNo() != null){
				final String orderNo = gatewayAppNotify.getGatewayOrderNo();
				redisTemplate.execute(new RedisCallback<Void>() {

					public Void doInRedis(RedisConnection connection)
							throws DataAccessException {
						connection.lPush( redisTemplate.getStringSerializer().serialize(RedisConstants.NOTIFY_APP_QUEUE_NAME),
								redisTemplate.getStringSerializer().serialize(orderNo));
						return null;
					}
				});
			}
			
		}

	}
}
