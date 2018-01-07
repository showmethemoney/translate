package com.fubon.esb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MethodParam")
public class TransactionMessage implements Serializable
{
	@XmlElement(name = "Parameter1")
	private String parameter1 = null;
	@XmlElement(name = "Parameter2")
	private String parameter2 = null;
	@XmlElement(name = "Parameter3")
	private String parameter3 = null;
	@XmlElement(name = "Parameter4")
	private String parameter4 = null;
	@XmlElement(name = "Parameter5")
	private String parameter5 = null;
	@XmlElement(name = "Parameter6")
	private String parameter6 = null;
	@XmlElement(name = "Parameter7")
	private String parameter7 = null;
	@XmlElement(name = "Parameter8")
	private String parameter8 = null;

	public TransactionMessage() {
	}

	public String getParameter1() {
		return parameter1;
	}

	public String getParameter2() {
		return parameter2;
	}

	public String getParameter3() {
		return parameter3;
	}

	public String getParameter4() {
		return parameter4;
	}

	public String getParameter5() {
		return parameter5;
	}

	public String getParameter6() {
		return parameter6;
	}

	public String getParameter7() {
		return parameter7;
	}

	public String getParameter8() {
		return parameter8;
	}

	public void setParameter1(String parameter1) {
		this.parameter1 = parameter1;
	}

	public void setParameter2(String parameter2) {
		this.parameter2 = parameter2;
	}

	public void setParameter3(String parameter3) {
		this.parameter3 = parameter3;
	}

	public void setParameter4(String parameter4) {
		this.parameter4 = parameter4;
	}

	public void setParameter5(String parameter5) {
		this.parameter5 = parameter5;
	}

	public void setParameter6(String parameter6) {
		this.parameter6 = parameter6;
	}

	public void setParameter7(String parameter7) {
		this.parameter7 = parameter7;
	}

	public void setParameter8(String parameter8) {
		this.parameter8 = parameter8;
	}

}
