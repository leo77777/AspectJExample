package fr.leo.aspects;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LogAspect {
	
	Logger logger = Logger.getLogger(LogAspect.class.getName());

	@Around("@annotation(fr.leo.aspects.Log)")
	public Object log( ProceedingJoinPoint proceedingJoinPoint ) throws Throwable{
		long t1 = System.currentTimeMillis();
		logger.info("From loggin aspect ... before " 
						+ proceedingJoinPoint.getSignature());	
		Object result = proceedingJoinPoint.proceed();
		logger.info("From loggin aspect ... after " 
				+ proceedingJoinPoint.getSignature());	
		long t2 = System.currentTimeMillis();
		logger.info("Durée d'execution : " + (t2 - t1));
		return result;
	}
}

