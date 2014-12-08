package com.pay.third;

import java.math.BigDecimal;
import java.util.Date;

public abstract class ThirdCallbackGWOrderBridge<T> {
	protected T t;
	public ThirdCallbackGWOrderBridge(T t) {
		this.t = t;
	}
	
    public abstract String getOrderNo() ;
	
    public abstract String getParterSerialNum() ;

    public abstract String exceptCurrentStatus() ;

    public abstract String toStatus() ;

	public abstract BigDecimal getTotalFee();

	public abstract Date getPaymentDate();
}
