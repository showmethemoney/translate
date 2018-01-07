package com.fubon.esb;

import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.tibco.tibjms.TibjmsQueueConnectionFactory;

@Configuration
@PropertySources({ @PropertySource("classpath:ems.properties") })
public class MessageQueueFactoryConfig
{

	public static final String NAMED_QUEUE_CONNECTION_FACTORY = "QueueConnectionFactory";
	public static final String NAMED_QUEUE_SESSION = "queueSession";

	@Autowired
	Environment env = null;

	@Bean(NAMED_QUEUE_CONNECTION_FACTORY)
	public QueueConnectionFactory queueConnectionFactory() {
		QueueConnectionFactory factory = new TibjmsQueueConnectionFactory( env.getProperty( "queue.connection.url" ) );
		
		return factory;
	}

	@Bean(NAMED_QUEUE_SESSION)
	public QueueSession queueSession() throws Throwable {
		return queueConnectionFactory().createQueueConnection( "", "" ).createQueueSession( false, javax.jms.Session.AUTO_ACKNOWLEDGE );
	}
}
