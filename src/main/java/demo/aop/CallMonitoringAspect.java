package demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class CallMonitoringAspect {

	@Autowired
	CallMonitor callMonitor;

	Logger logger = org.slf4j.LoggerFactory
			.getLogger(CallMonitoringAspect.class);

	@Pointcut("execution(* demo..BookService.*(..))")
	public void repositiryMethods() {
	}
	
	@Pointcut("@annotation(demo.aop.Monitorable)")
	public void monitorable() {
		
	}

	@Around("repositiryMethods()")
	//@Around("monitorable()")
	public Object monitor(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

		if (callMonitor.isEnabled()) {

			logger.info("Monitoring of " + proceedingJoinPoint.toString()
					+ " started");
			StopWatch sw = new StopWatch(proceedingJoinPoint.toString());

			try {
				sw.start(proceedingJoinPoint.toShortString());
				return proceedingJoinPoint.proceed();

			} finally {

				synchronized (this) {
					callMonitor.registerCall(1);
				}

				sw.stop();
				logger.info(sw.prettyPrint());
				logger.info("Monitoring of " + proceedingJoinPoint.toString()
						+ " finished after " + sw.getLastTaskTimeMillis()
						+ " ms");
			}
		} else {
			return proceedingJoinPoint.proceed();
		}

	}
	
	public void setCallMonitor(CallMonitor callMonitor) {
		this.callMonitor = callMonitor;
	}

}
