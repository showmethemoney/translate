package com.fubon.esb.translate.requestor.parse.txt;

import org.springframework.stereotype.Component;

import com.fubon.esb.translate.parse.txt.ParseFromTxt;
import com.fubon.esb.translate.requestor.MessageQueueRequestor;

@Component
public class ParseToTxtMessageRequestor extends MessageQueueRequestor {

	public ParseToTxtMessageRequestor() {
		setTransformer(new ParseFromTxt());
	}
}
