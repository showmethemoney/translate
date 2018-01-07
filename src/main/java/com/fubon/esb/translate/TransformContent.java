package com.fubon.esb.translate;

import com.fubon.esb.bean.TransactionMessage;

public interface TransformContent {
	
	public String transform(TransactionMessage message);
}
