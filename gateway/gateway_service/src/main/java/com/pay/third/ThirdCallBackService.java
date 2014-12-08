package com.pay.third;

import javax.servlet.http.HttpServletRequest;

import com.pay.exception.VerifyException;

public interface ThirdCallBackService<T> {
    public T handler(HttpServletRequest request) throws VerifyException;
}
