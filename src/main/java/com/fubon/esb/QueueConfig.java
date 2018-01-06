package com.fubon.esb;

import javax.jms.Queue;
import javax.jms.QueueSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

	@Autowired
	@Qualifier(MessageQueueFactoryConfig.NAMED_QUEUE_SESSION)
	private QueueSession queueSession = null;

	public static final String NAMED_PARSE_TO_TXT_QUEUE = "";
	public static final String NAMED_PARSE_TO_TXT_1_QUEUE = "";
	public static final String NAMED_RENDER_TO_TXT_QUEUE = "";
	public static final String NAMED_RENDER_TO_TXT_1_QUEUE = "";
	public static final String NAMED_RENDER_TO_XML_QUEUE = "";
	public static final String NAMED_RENDER_TO_XML_1_QUEUE = "";

	@Bean(NAMED_PARSE_TO_TXT_QUEUE)
	public Queue parseToTxtQueue() throws Throwable {
		return queueSession.createQueue(NAMED_PARSE_TO_TXT_QUEUE);
	}

	@Bean(NAMED_PARSE_TO_TXT_1_QUEUE)
	public Queue parseToTxtQueue_1() throws Throwable {
		return queueSession.createQueue(NAMED_PARSE_TO_TXT_1_QUEUE);
	}

	@Bean(NAMED_RENDER_TO_TXT_QUEUE)
	public Queue renderToTxtQueue() throws Throwable {
		return queueSession.createQueue(NAMED_RENDER_TO_TXT_QUEUE);
	}

	@Bean(NAMED_RENDER_TO_TXT_1_QUEUE)
	public Queue renderToTxtQueue_1() throws Throwable {
		return queueSession.createQueue(NAMED_RENDER_TO_TXT_1_QUEUE);
	}

	@Bean(NAMED_RENDER_TO_XML_QUEUE)
	public Queue renderToXmlQueue() throws Throwable {
		return queueSession.createQueue(NAMED_RENDER_TO_XML_QUEUE);
	}

	@Bean(NAMED_RENDER_TO_XML_QUEUE)
	public Queue renderToXmlQueue_1() throws Throwable {
		return queueSession.createQueue(NAMED_RENDER_TO_XML_1_QUEUE);
	}
}
