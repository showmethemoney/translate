package com.fubon.esb.translate.parse.txt;

import com.fubon.esb.bean.TransactionMessage;
import com.fubon.esb.translate.parse.TransformContent;

public class ParseFromTxt implements TransformContent {

	private static final String NAMED_MESSAGE_PACKAGE = "com.fubon.esb.bean";

	@Override
	public String transform(TransactionMessage message) {
		// return new ParseFromTXT().parse(directionDef, txtString, onlyParseHeader,
		// bodyTxtString, parsedTxnDataStr, encoding);
		return null;
	}

	// @Bean
	// public Jaxb2Marshaller jaxb2Marshaller() {
	// Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
	// jaxb2Marshaller.setPackagesToScan( NAMED_MESSAGE_PACKAGE );
	//
	// return jaxb2Marshaller;
	// }
}
