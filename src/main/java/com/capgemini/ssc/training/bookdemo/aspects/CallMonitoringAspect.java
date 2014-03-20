package com.capgemini.ssc.training.bookdemo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.StopWatch;

/**
 * Simple aspect that monitors call count and call invocation time. It uses JMX
 * annotations and therefore can be monitored using any JMX console such as the
 * jConsole
 */
@Aspect
@ManagedResource("bookdemo:type=CallMonitor")
public class CallMonitoringAspect {

    final Logger logger = LoggerFactory.getLogger(CallMonitoringAspect.class);

    private boolean enabled = true;

    private int callCount = 0;

    private long accumulatedCallTime = 0;

    @ManagedAttribute
    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    @ManagedAttribute
    public boolean isEnabled() {
	return enabled;
    }

    @ManagedOperation
    public void reset() {
	this.callCount = 0;
	this.accumulatedCallTime = 0;
    }

    @ManagedAttribute
    public int getCallCount() {
	return callCount;
    }

    @ManagedAttribute
    public long getCallTime() {
	return (this.callCount > 0 ? this.accumulatedCallTime / this.callCount
		: 0);
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    void inRepository() {
    }

    @Around("inRepository()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
	if (this.enabled) {
	    logger.info("Monitoring of "
		    + joinPoint.getSignature().toShortString() + " started");
	    StopWatch sw = new StopWatch(joinPoint.toShortString());

	    sw.start(joinPoint.getSignature().getName());
	    try {
		return joinPoint.proceed();
	    } finally {
		sw.stop();
		synchronized (this) {
		    this.callCount++;
		    this.accumulatedCallTime += sw.getTotalTimeMillis();
		}
		logger.info(sw.prettyPrint());
		logger.info("Monitoring of "
			+ joinPoint.getSignature().toShortString()
			+ " finished");
	    }
	} else {
	    return joinPoint.proceed();
	}
    }
}
