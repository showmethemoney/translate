package com.fubon.esb;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;

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
public class QueueRequestorConfig
{

	public static final String NAMED_PARSE_TO_TXT_QUEUE = "fubon.esb.parse.to.txt.transform";
	public static final String NAMED_PARSE_TO_TXT_1_QUEUE = "fubon.esb.parse.to.txt.1.transform";
	public static final String NAMED_PARSE_TO_TXTPad_QUEUE = "fubon.esb.parse.to.txtpad.transform";
	public static final String NAMED_PARSE_TO_TXTPad_1_QUEUE = "fubon.esb.parse.to.txtpad.1.transform";
	public static final String NAMED_RENDER_TO_TXT_QUEUE = "fubon.esb.render.to.txt.transform";
	public static final String NAMED_RENDER_TO_TXT_1_QUEUE = "fubon.esb.render.to.txt.1.transform";
	public static final String NAMED_RENDER_TO_XML_QUEUE = "fubon.esb.render.to.xml.transform";
	public static final String NAMED_RENDER_TO_XML_1_QUEUE = "fubon.esb.render.to.xml.1.transform";

	@Autowired
	@Qualifier(MessageQueueFactoryConfig.NAMED_QUEUE_CONNECTION_FACTORY)
	private QueueConnectionFactory queueConnectionFactory = null;
	@Autowired
	@Qualifier(MessageQueueFactoryConfig.NAMED_QUEUE_SESSION)
	private QueueSession queueSession = null;
	@Autowired
	private ParseToTxtMessageRequestor parseToTxtMessageRequestor = null;
	@Autowired
	private RenderToTxtMessageRequestor renderToTxtMessageRequestor = null;
	@Autowired
	private RenderToXmlMessageRequestor renderToXmlMessageReuqestor = null;

	public Queue parseToTxtQueue() throws Throwable {
		return queueSession.createQueue( NAMED_PARSE_TO_TXT_QUEUE );
	}
	
	public Queue parseToTxt1Queue() throws Throwable {
		return queueSession.createQueue( NAMED_PARSE_TO_TXT_1_QUEUE );
	}

	public Queue parseToTxtPadQueue() throws Throwable {
		return queueSession.createQueue( NAMED_PARSE_TO_TXTPad_QUEUE );
	}

	public Queue parseToTxtPad1Queue() throws Throwable {
		return queueSession.createQueue( NAMED_PARSE_TO_TXTPad_1_QUEUE );
	}

	public Queue renderToTxtQueue() throws Throwable {
		return queueSession.createQueue( NAMED_RENDER_TO_TXT_QUEUE );
	}

	public Queue renderToTxt1Queue() throws Throwable {
		return queueSession.createQueue( NAMED_RENDER_TO_TXT_1_QUEUE );
	}

	public Queue renderToXmlQueue() throws Throwable {
		return queueSession.createQueue( NAMED_RENDER_TO_XML_QUEUE );
	}

	public Queue renderToXml1Queue() throws Throwable {
		return queueSession.createQueue( NAMED_RENDER_TO_XML_1_QUEUE );
	}

	@Bean
	public MessageListenerContainer getParseToTxtListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( parseToTxtQueue() );
		container.setMessageListener( parseToTxtMessageRequestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 );

		return container;
	}

	@Bean
	public MessageListenerContainer getParseToTxt_1ListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( parseToTxt1Queue() );
		container.setMessageListener( parseToTxtMessageRequestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 );

		return container;
	}

	@Bean
	public MessageListenerContainer getParseToTxtPadListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( parseToTxtPadQueue() );
		container.setMessageListener( parseToTxtMessageRequestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 );

		return container;
	}

	@Bean
	public MessageListenerContainer getParseToTxtPad_1ListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( parseToTxtPad1Queue() );
		container.setMessageListener( parseToTxtMessageRequestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 );

		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToTxtListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( renderToTxtQueue() );
		container.setMessageListener( renderToTxtMessageRequestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 );

		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToTxt_1ListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( renderToTxt1Queue() );
		container.setMessageListener( renderToTxtMessageRequestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 );

		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToXmlListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( renderToXmlQueue() );
		container.setMessageListener( renderToXmlMessageReuqestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 );

		return container;
	}

	@Bean
	public MessageListenerContainer getRenderToXml_1ListenerContainer() throws Throwable {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory( queueConnectionFactory );
		container.setDestination( renderToXml1Queue() );
		container.setMessageListener( renderToXmlMessageReuqestor );
		container.setConcurrentConsumers( 20 );
		container.setMaxConcurrentConsumers( 30 ); 

		return container;
	}
}
