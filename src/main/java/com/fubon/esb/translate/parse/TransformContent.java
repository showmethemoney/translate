package com.fubon.esb.translate.parse;

import com.fubon.esb.bean.TransactionMessage;

public interface TransformContent {
	
	public String transform(TransactionMessage message);
}
