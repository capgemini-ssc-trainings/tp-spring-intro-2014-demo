package demo.aop;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;

public interface CallMonitor {
	
	@ManagedAttribute
	boolean isEnabled();
	
	@ManagedOperation
	void setEnabled(boolean enabled);
	
	@ManagedAttribute
	int getCounter();
	
	void registerCall(int count);

}
