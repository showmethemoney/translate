package com.fubon.esb;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import com.fubon.esb.translate.requestor.parse.txt.ParseToTxtMessageRequestor;
import com.fubon.esb.translate.requestor.render.txt.RenderToTxtMessageRequestor;
import com.fubon.esb.translate.requestor.render.xml.RenderToXmlMessageRequestor;

@Configuration
public class QueueRequestorConfig {

	@Autowired
	@Qualifier(MessageQueueFactoryConfig.NAMED_QUEUE_CONNECTION_FACTORY)
	private QueueConnectionFactory queueConnectionFactory = null;

	@Autowired
	@Qualifier(QueueConfig.NAMED_PARSE_TO_TXT_QUEUE)
	private Queue parseToTxtQueue = null;
	@Autowired
	@Qualifier(QueueConfig.NAMED_PARSE_TO_TXT_1_QUEUE)
	private Queue parseToTxt_1_Queue = null;
	@Autowired
	@Qualifier(QueueConfig.NAMED_RENDER_TO_TXT_QUEUE)
	private Queue renderToTxtQueue = null;
	@Autowired
	@Qualifier(QueueConfig.NAMED_RENDER_TO_TXT_1_QUEUE)
	private Queue renderToTxt_1_Queue = null;
	@Autowired
	@Qualifier(QueueConfig.NAMED_RENDER_TO_XML_QUEUE)
	private Queue renderToXmlQueue = null;
	@Autowired
	@Qualifier(QueueConfig.NAMED_RENDER_TO_XML_1_QUEUE)
	private Queue renderToXml_1_Queue = null;
	@Autowired
	private ParseToTxtMessageRequestor parseToTxtMessageRequestor = null;
	@Autowired
	private RenderToTxtMessageRequestor renderToTxtMessageRequestor = null;
	@Autowired
	private RenderToXmlMessageRequestor renderToXmlMessageReuqestor = null;

	@Bean
	public MessageListenerContainer getParseToTxtListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(queueConnectionFactory);
		container.setDestination(parseToTxtQueue);
		container.setMessageListener(parseToTxtMessageRequestor);
		container.setConcurrency( "20-30" );
		
		return container;
	}

	@Bean
	public MessageListenerContainer getParseToTxt_1ListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(queueConnectionFactory);
		container.setDestination(parseToTxt_1_Queue);
		container.setMessageListener(parseToTxtMessageRequestor);
		container.setConcurrency( "20-30" );
		
		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToTxtListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(queueConnectionFactory);
		container.setDestination(renderToTxtQueue);
		container.setMessageListener(renderToTxtMessageRequestor);
		container.setConcurrency( "20-30" );
		
		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToTxt_1ListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(queueConnectionFactory);
		container.setDestination(renderToTxt_1_Queue);
		container.setMessageListener(renderToTxtMessageRequestor);
		container.setConcurrency( "20-30" );
		
		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToXmlListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(queueConnectionFactory);
		container.setDestination(renderToXmlQueue);
		container.setMessageListener(renderToXmlMessageReuqestor);
		container.setConcurrency( "20-30" );
		
		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToXml_1ListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(queueConnectionFactory);
		container.setDestination(renderToXml_1_Queue);
		container.setMessageListener(renderToTxtMessageRequestor);
		container.setConcurrency( "20-30" );
		
		return container;
	}
}
