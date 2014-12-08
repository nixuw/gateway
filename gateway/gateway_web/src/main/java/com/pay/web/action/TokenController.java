
package com.pay.web.action;

import static com.pay.web.cons.Constants.MD5_SPLIT;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.pay.dao.entity.GatewayAppAccount;
import com.pay.service.AccountService;
import com.pay.utils.Md5Utility;
import com.pay.web.mvc.ResponseJson;
import com.pay.web.util.SerialNumUtils;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("token")
public class TokenController {
	
	@Autowired  
	private RedisTemplate redisTemplate; 
	
	@Autowired
	private AccountService accoutService;
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TokenController.class);

	

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "applytoken")
    public View getToken(String appAccount,final String bizCode, String md5,ModelMap model)  {
    	if (logger.isInfoEnabled()){
    		logger.info(appAccount + MD5_SPLIT + bizCode + MD5_SPLIT + md5 + "request a token");
    	}
    	
    	View result = new MappingJacksonJsonView();
    	
    	if (StringUtils.isEmpty(appAccount) || StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(md5)){
    		ResponseJson.ERROR_ACCOUNT.build(model);
    		return result;
    	}
    	
    	// 检查账户是否存在
    	GatewayAppAccount account = accoutService.getAccount(appAccount);
    	if (account == null){
    		ResponseJson.ERROR_ACCOUNT.build(model);
    		return result;
    	}
    	
    	// 验签
    	if (!validate(account,bizCode,md5)){
    		ResponseJson.ERROR_ACCOUNT.build(model);
    		return result;
    	}

    	final String token = SerialNumUtils.createToken(bizCode);
    	model.put("token", token);
    	
    	if (logger.isInfoEnabled()){
    		logger.info("gen token for: " + appAccount + MD5_SPLIT + bizCode + MD5_SPLIT + md5 + MD5_SPLIT + token);
    	}
    	
    	/*// 以PRE+token为key,往redis中放入bizcode,支付请求时bizcode必须一致,时效2个小时
    	redisTemplate.execute(new RedisCallback<String>() {

			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.setEx( redisTemplate.getStringSerializer().serialize("PRE" + token), 60*60*2, redisTemplate.getStringSerializer().serialize(bizCode));
				return "PRE" + token;
			}
		});*/
    	
    	ResponseJson.SUCCESS.build(model);
    	return result;
    	
    }



	private boolean validate(GatewayAppAccount account, String bizCode,
			String md5) {

		return StringUtils.equalsIgnoreCase(md5,
				md5(account.getAccount(), account.getAccountKey(), bizCode));
	}
    
	private String md5(String account,String accountKey, String bizCode){
		StringBuffer buf = new StringBuffer(account);
		buf.append(MD5_SPLIT).append(bizCode).append(MD5_SPLIT).append(accountKey);
		return Md5Utility.getMD5String(buf.toString());
		
	}
    
    
   
}
