package com.fubon.esb.translate.requestor.render.xml;

import org.springframework.stereotype.Component;

import com.fubon.esb.translate.render.xml.RenderToXml;
import com.fubon.esb.translate.requestor.MessageQueueRequestor;

@Component
public class RenderToXmlMessageRequestor extends MessageQueueRequestor {

	public RenderToXmlMessageRequestor() {
		setTransformer(new RenderToXml());
	}
}
