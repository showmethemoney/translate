package com.fubon.esb.translate.parse.txt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fubon.esb.bean.TransactionMessage;
import com.fubon.esb.translate.TransformContent;
import com.fubon.esb.tx.parse.ParseFromTXT;

public class ParseFromTxt implements TransformContent
{
	protected static final Logger logger = LoggerFactory.getLogger( ParseFromTxt.class );

	@Override
	public String transform(TransactionMessage message) {
		try {
			return new ParseFromTXT().parse( message.getParameter1(), message.getParameter2(), Boolean.valueOf( message.getParameter3() ),
			        message.getParameter4(), message.getParameter5(), message.getParameter6(), message.getParameter7() );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return null;
	}

}
