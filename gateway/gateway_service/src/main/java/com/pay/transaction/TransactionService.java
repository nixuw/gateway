package com.pay.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionUsageException;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * 功能: 封装事务操作
 * <p>
 * 用法:
 * 
 * @version 1.0
 */
public class TransactionService {
	public final static int DEFAULT_TIMEOUT = 120; // timeout in seconds
	
	private final PlatformTransactionManager transactionManager;
	
	public TransactionService(PlatformTransactionManager transactionManager) {
		if ( transactionManager == null ){
			throw new IllegalArgumentException("TransactionManager can not be null. Please check the transactionManager properties.");
		}
		this.transactionManager = transactionManager;
	}
	
	/**
	 * 开始事务（PROPAGATION_REQUIRED 默认超时(120秒)） 
	 */
	public TransactionStatus begin(){
		return begin(DEFAULT_TIMEOUT);
	}
	
	/**
	 * 开始事务（PROPAGATION_REQUIRED)
	 * @param timeout 超时时间
	 * @return
	 */
	public TransactionStatus begin(int timeout){
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		definition.setTimeout(timeout);
		return transactionManager.getTransaction(definition);
	}
	
	
	/**
	 * 开始一个新的事务（PROPAGATION_REQUIRES_NEW 默认超时(120秒)） 
	 * @return
	 */
	public TransactionStatus beginNew(){
		return beginNew(DEFAULT_TIMEOUT);
	}
	
	/**
	 * 开始一个新的事务（PROPAGATION_REQUIRES_NEW）
	 * @param timeout
	 * @return
	 */
	public TransactionStatus beginNew(int timeout){
		
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		definition.setTimeout(timeout); //会检查参数有效性,所以前面不需要检查
		return transactionManager.getTransaction(definition);
	}

	/**
	 * 按给定的传播机制和超时时间开始一个新的事务
	 * @param propagation 传播属性
	 * @param timeout 超时时间
	 * @return 
	 */
	public TransactionStatus begin(int propagation,int timeout){
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition(); 
		definition.setPropagationBehavior(propagation); //会检查参数有效性,所以前面不需要检查
		definition.setTimeout(timeout); 
		return transactionManager.getTransaction(definition);
	}
	
	/**
	 * 提交事务
	 */
	public void commit(TransactionStatus status){
		if (status == null){
			throw new TransactionUsageException("TransactionStatus can not be null.");
		}
		transactionManager.commit(status);
	}
	/**
	 * 回滚事务
	 */
	public void rollback(TransactionStatus status){
		if (status == null){
			throw new TransactionUsageException("TransactionStatus can not be null.");
		}
		transactionManager.rollback(status);
	}

}
