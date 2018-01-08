package com.sift.apis.aspect;

import java.util.Calendar;
import java.util.Properties;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sift.apis.beans.BaseWrapper;
import com.sift.apis.beans.ResponseMessage;
import com.sift.apis.constants.Constants;

@Aspect
@Component
public class BaseAspect {

	@Autowired
	Properties responseMessageProperties;
	
	@Autowired
	Properties configProperties;
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAspect.class);
	
	@Pointcut(Constants.BASE_POINTCUT)
	public void basePointCut() { }
	
	@Pointcut(Constants.EXCEPTION_POINTCUT)
	public void exceptionPointCut() { }
	
	@Pointcut(Constants.CACHE_POINTCUT)
	public void cachePointCut() { }
	
	@Before("basePointCut()")
	public void beforeMethod(JoinPoint jp) {
//		logger.info("Entering Method : " + jp.getSignature());
	}
	
	@After("basePointCut()")
	public void doAfterMethodExecution(JoinPoint jp) {
//		logger.info("Exiting Method : " + jp.getSignature());
	}
	
	@AfterThrowing(pointcut="basePointCut()", throwing="exception")
	public void doAfterThrowingException(Exception exception) {
	}
	
	@Around("exceptionPointCut()")
	public Object doAroundExecutionForApp(ProceedingJoinPoint pjp){
		Long startTimestamp = Calendar.getInstance().getTimeInMillis();
		BaseWrapper response = new BaseWrapper();
		try {
				response = (BaseWrapper)pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			String eMssg = e.getMessage();
			String mssg = null;
			if(null == eMssg || eMssg.isEmpty() || eMssg.matches("^[a-zA-Z]*$")) {
				mssg = responseMessageProperties.getProperty("608");
			} else {
				mssg = responseMessageProperties.getProperty(eMssg);
			}
			logger.debug("EXCP MSSG : " + eMssg);
			logger.debug("RESPOSNE MSSG : " + mssg);
			response.setResponseMessage(new ResponseMessage(HttpStatus.PAYMENT_REQUIRED.toString(), 
					mssg, configProperties.getProperty("api.version")));
			return new ResponseEntity<Object>(response, HttpStatus.PAYMENT_REQUIRED);
		}

		if(null != response && null == response.getResponseMessage()) {
			response.setResponseMessage(new ResponseMessage(HttpStatus.OK.toString(), Constants.SUCCESS_OK,
					 configProperties.getProperty("api.version")));
		}
		logger.info("Execution Time In Millisecs : " + (Calendar.getInstance().getTimeInMillis() - startTimestamp));
		return response;
		
	}
	
}
