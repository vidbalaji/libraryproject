package com.vblearning.libraryweb.libraryweb.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class AopAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(value = " com.vblearning.libraryweb.libraryweb.aspect.CommonJoinPointConfig.allLayerExecution()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("{} returned with value {} with arg {}", joinPoint, result, joinPoint.getArgs());

	}

	@After(value = "com.vblearning.libraryweb.libraryweb.aspect.CommonJoinPointConfig.allLayerExecution()")
	public void after(JoinPoint joinPoint) {
		logger.info("after execution of {}", joinPoint);
	}

	@Before(value = "com.vblearning.libraryweb.libraryweb.aspect.CommonJoinPointConfig.allLayerExecution()")
	public void before(JoinPoint joinPoint) {
		// Advice

		logger.info(" Before execution for {}", joinPoint);
	}

	@Around(value = "com.vblearning.libraryweb.libraryweb.aspect.CommonJoinPointConfig.allLayerExecution()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object returnProceed = joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time Taken by {} is {} ms", joinPoint, timeTaken);

		return returnProceed;
	}
}