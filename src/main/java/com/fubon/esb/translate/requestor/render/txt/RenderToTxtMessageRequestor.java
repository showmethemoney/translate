package com.fubon.esb.translate.requestor.render.txt;

import org.springframework.stereotype.Component;

import com.fubon.esb.translate.render.txt.RenderToTxt;
import com.fubon.esb.translate.requestor.MessageQueueRequestor;

@Component
public class RenderToTxtMessageRequestor extends MessageQueueRequestor {

	public RenderToTxtMessageRequestor() {
		setTransformer(new RenderToTxt());
	}

}
