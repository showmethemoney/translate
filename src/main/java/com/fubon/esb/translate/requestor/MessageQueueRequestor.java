package com.fubon.esb.translate.requestor;

import java.io.StringReader;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.fubon.esb.MessageQueueFactoryConfig;
import com.fubon.esb.bean.TransactionMessage;
import com.fubon.esb.translate.TransformContent;

public abstract class MessageQueueRequestor implements MessageListener
{

	protected static final Logger logger = LoggerFactory.getLogger( MessageQueueRequestor.class );
	private TransformContent transformer = null;
	@Autowired
	@Qualifier(MessageQueueFactoryConfig.NAMED_QUEUE_SESSION)
	private QueueSession session = null;
	@Autowired
	private Jaxb2Marshaller marshaller = null;

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				StopWatch stopWatch = new StopWatch();
				stopWatch.start();

				TransactionMessage instance = null;

				instance = (TransactionMessage) marshaller.unmarshal( new StreamSource( new StringReader( ((TextMessage) message).getText() ) ) );

				String result = transformer.transform( instance );

				stopWatch.stop();
				logger.info( "Time : {} - {} - {}", stopWatch.getTime(), (Queue) message.getJMSReplyTo(), result );

				MessageProducer producer = session.createProducer( (Queue) message.getJMSReplyTo() );
				TextMessage replyMessage = session.createTextMessage( result );
				producer.send( replyMessage );
			}
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}

	public TransformContent getTransformer() {
		return transformer;
	}

	public void setTransformer(TransformContent transformer) {
		this.transformer = transformer;
	}

}
