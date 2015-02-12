package demo.msg;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

@Configuration
public class MessagingConfiguration {
	
	@Bean
	public Receiver getrecReceiver() {
		return new Receiver();
	}
	
	@Bean
    MessageListenerAdapter adapter(Receiver receiver) {
        MessageListenerAdapter messageListener
                = new MessageListenerAdapter(receiver);
        messageListener.setDefaultListenerMethod("receiveMessage");
        return messageListener;
    }

    @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter messageListener,
                                             ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(messageListener);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("mail-queue");
        return container;
    }

}
