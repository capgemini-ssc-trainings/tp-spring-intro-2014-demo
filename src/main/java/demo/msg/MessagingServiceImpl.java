package demo.msg;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessagingServiceImpl implements MessagingService{
	
	@Autowired
	JmsTemplate template;

	@Override
	public void sendMessage(final String message) {
		template.send("mail-queue", new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		
	}

}
