package com.pay.web.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public final class SerialNumUtils {
	
	
	
	public static final String createSerialNum(){
		
        String dateString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).substring(3);
        StringBuffer serialString = new StringBuffer(dateString);
        Integer ramdonNum =null;
		try {
			ramdonNum = SecureRandom.getInstance("SHA1PRNG").nextInt(9999);
		} catch (NoSuchAlgorithmException e) {
		}
		
        String ramdonNumString = ramdonNum.toString();
        
        while(ramdonNumString.length() < 4){
        	ramdonNumString = "0" + ramdonNumString;
        }
        serialString.append(ramdonNumString);
        return serialString.toString();
	}
	
	public static String createToken(String ramdonStr){
		StringBuilder rs = new StringBuilder();
		
		// 8位日期
		SimpleDateFormat sf = new SimpleDateFormat("ssmmHHddMMyy"); 
		String timePart = Long.toString(Long.valueOf(sf.format(new Date())),36);
        rs.append(StringUtils.leftPad(timePart, 8, '0'));
        
        // 1位机器代码
        int sum = 0;
        for (int i = 0; ramdonStr != null && i < ramdonStr.length(); i ++){
			char c = ramdonStr.charAt(i);
			sum += Character.getNumericValue(c);
		}
        String hostPart = Long.toString(sum%36 ,36);
        rs.append(hostPart);
        
        // 2位随机数
        String randomPart = Long.toString(Math.round( 36 * 36 * Math.random()),36);
		rs.append(StringUtils.leftPad(randomPart, 2, '0'));
		
		return "TOKEN_" + rs.toString();
	}
	
	    public static long ramdon (){
	    	return Math.round(new Random(System.currentTimeMillis()).nextDouble() * 1000000);
	    }
	    
	
}
