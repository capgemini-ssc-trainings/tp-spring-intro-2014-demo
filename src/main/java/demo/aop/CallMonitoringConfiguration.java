package demo.aop;

import org.aspectj.lang.Aspects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CallMonitoringConfiguration {
	
	//@Bean
	public CallMonitoringAspect callMonitoringAspect(CallMonitor callMonitor) {
		CallMonitoringAspect aspect = Aspects
				.aspectOf(CallMonitoringAspect.class);
		aspect.setCallMonitor(callMonitor);
		return aspect;
	}

}
