package com.fubon.esb.translate.render.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fubon.esb.bean.TransactionMessage;
import com.fubon.esb.translate.TransformContent;
import com.fubon.esb.tx.render.xml.RenderToXML;

public class RenderToXml implements TransformContent
{
	protected static final Logger logger = LoggerFactory.getLogger( RenderToXml.class );

	@Override
	public String transform(TransactionMessage message) {
		try {
			return new RenderToXML().render( message.getParameter1(), message.getParameter2(), message.getParameter3(), message.getParameter4(),
			        message.getParameter5(), message.getParameter6(), message.getParameter7(), message.getParameter8() );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
		
		return null;
	}

}
