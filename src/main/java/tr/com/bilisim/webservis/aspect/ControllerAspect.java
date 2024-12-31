package tr.com.bilisim.webservis.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* tr.com.bilisim.webservis.restcontroller.*.*(..))")
	public void forAllRestController() {}
	
	@Pointcut("execution(* tr.com.bilisim.webservis.pagecontroller.*.*(..))")
	public void forAllPageController() {}

	@Pointcut("execution(* tr.com.bilisim.webservis.repostories.*.*(..))")
	public void forAllRepository() {}
	
	@Pointcut("execution(* tr.com.bilisim.webservis.service.*.*(..))")
	public void forAllService() {}
	
	@Around(value = "forAllRestController() || forAllPageController() || forAllRepository() || forAllService()" )	
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

		String method = theProceedingJoinPoint.getSignature().toShortString();
		Object[] args = theProceedingJoinPoint.getArgs();
		
		if (args!=null) {
			
			String params = "(";
			int i = 0;
			for (Object tempArg : args) {
				if (tempArg!=null) {
					i++;
					params = params +(i>1?",":"")+ tempArg.toString();
				}
			}
			myLogger.info(""+"=R=======>"+method+params+")");
		}

		Object result = theProceedingJoinPoint.proceed();
		return result;
	}
	
	@Around(value = "forAllRestController() || forAllPageController()" )	
	public Object aroundGetFortuneForTime(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		String method = theProceedingJoinPoint.getSignature().toShortString();
		long begin = System.currentTimeMillis();

		Object result = null;
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			throw e;
		}
		
		long end = System.currentTimeMillis();
		long duration = end - begin;
		myLogger.info(""+"=S=======>"+method+" Duration: " + duration / 1000.0 + " seconds ");

		return result;
	}
	

}
