package com.fubon.esb.jaxb;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fubon.esb.bean.TransactionMessage;

public class TestJaxb
{
	protected static final Logger logger = LoggerFactory.getLogger( TestJaxb.class );

	@Ignore
	@Test
	public void testMarshal() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( TransactionMessage.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

			TransactionMessage message = new TransactionMessage();
			message.setParameter1( "123" );
			message.setParameter2( "456" );
			message.setParameter3( "789" );
			message.setParameter4( "741" );
			message.setParameter5( "852" );
			message.setParameter6( "963" );
			message.setParameter7( "000" );
			message.setParameter8( "999" );

			StringWriter result = new StringWriter();
			jaxbMarshaller.marshal( message, result );
			logger.info( result.toString() );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}

	@Ignore
	@Test
	public void testUnMarshal() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( TransactionMessage.class );
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

			String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + "<MethodParam>\r\n"
			        + "    <Parameter1>123</Parameter1>\r\n" + "    <Parameter2>456</Parameter2>\r\n" + "    <Parameter3>789</Parameter3>\r\n"
			        + "    <Parameter4>741</Parameter4>\r\n" + "    <Parameter5>852</Parameter5>\r\n" + "    <Parameter6>963</Parameter6>\r\n"
			        + "    <Parameter7>000</Parameter7>\r\n" + "    <Parameter8>999</Parameter8>\r\n" + "</MethodParam>";

			TransactionMessage message = (TransactionMessage) jaxbUnMarshaller.unmarshal( IOUtils.toInputStream( xmlString, "UTF-8" ) );
			logger.info( message.getParameter1() );
			logger.info( message.getParameter2() );
			logger.info( message.getParameter3() );
			logger.info( message.getParameter4() );
			logger.info( message.getParameter5() );
			logger.info( message.getParameter6() );
			logger.info( message.getParameter7() );
			logger.info( message.getParameter8() );

		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}
}
