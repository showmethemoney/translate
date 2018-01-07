package com.fubon.esb.translate;

import java.io.StringWriter;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fubon.esb.bean.TransactionMessage;
import com.tibco.tibjms.TibjmsQueueConnectionFactory;

public class TestRenderToTxt
{
	protected static final Logger logger = LoggerFactory.getLogger( TestRenderToTxt.class );

	@Test
	public void testRenderToTxt() {
		try {
			String directionDefStr = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>\r\n"
			        + "<DirectionDef Direction = \"U\" xmlns = \"http://fubon.com.tw/XSD/ESB/Txn/TxnDefinition\" xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation = \"http://fubon.com.tw/XSD/ESB/Txn/TxnDefinition ../../../../SharedResources/Schemas/Txn/TxnDefinition.xsd\">\r\n"
			        + "    <HeaderDef>\r\n" + "        <FieldDef Type = \"F\">\r\n" + "            <Name>H1</Name>\r\n" + "            <CName>H1</CName>\r\n"
			        + "            <Type>H</Type>\r\n" + "            <Length>1</Length>\r\n" + "            <Scale>0</Scale>\r\n"
			        + "            <Justify>L</Justify>\r\n" + "            <PadChar> </PadChar>\r\n" + "            <Default>0xFE</Default>\r\n"
			        + "            <IncludeChinese>false</IncludeChinese>\r\n" + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n"
			        + "        <FieldDef Type = \"F\">\r\n" + "            <Name>H2</Name>\r\n" + "            <CName>H2</CName>\r\n"
			        + "            <Type>9</Type>\r\n" + "            <Length>2</Length>\r\n" + "            <Scale>0</Scale>\r\n"
			        + "            <Justify>L</Justify>\r\n" + "            <PadChar>0</PadChar>\r\n" + "            <Default>0</Default>\r\n"
			        + "            <IncludeChinese>true</IncludeChinese>\r\n" + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n"
			        + "        <FieldDef Type = \"F\">\r\n" + "            <Name>H3</Name>\r\n" + "            <CName>-Zs_Extend_BH3</CName>\r\n"
			        + "            <Type>H</Type>\r\n" + "            <Length>1</Length>\r\n" + "            <Scale>0</Scale>\r\n"
			        + "            <Justify>L</Justify>\r\n" + "            <PadChar> </PadChar>\r\n" + "            <Default> </Default>\r\n"
			        + "            <IncludeChinese>true</IncludeChinese>\r\n" + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n"
			        + "        <FieldDef Type = \"F\">\r\n" + "            <Name>H4</Name>\r\n" + "            <CName>H4</CName>\r\n"
			        + "            <Type>X</Type>\r\n" + "            <Length>5</Length>\r\n" + "            <Scale>0</Scale>\r\n"
			        + "            <Justify>L</Justify>\r\n" + "            <PadChar> </PadChar>\r\n" + "            <Default>0001</Default>\r\n"
			        + "            <IncludeChinese>true</IncludeChinese>\r\n" + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n"
			        + "        <FieldDef Type = \"F\">\r\n" + "            <Name>H5</Name>\r\n" + "            <CName>H5</CName>\r\n"
			        + "            <Type>H</Type>\r\n" + "            <Length>1</Length>\r\n" + "            <Scale>0</Scale>\r\n"
			        + "            <Justify>L</Justify>\r\n" + "            <PadChar> </PadChar>\r\n" + "            <Default>0x43</Default>\r\n"
			        + "            <IncludeChinese>true</IncludeChinese>\r\n" + "            <Optional>false</Optional>\r\n" + "        </FieldDef>\r\n"
			        + "        <FieldDef Type = \"F\">\r\n" + "            <Name>H6</Name>\r\n" + "            <CName>H6</CName>\r\n"
			        + "            <Type>9</Type>\r\n" + "            <Length>1</Length>\r\n" + "            <Scale>0</Scale>\r\n"
			        + "            <Justify>L</Justify>\r\n" + "            <PadChar>0</PadChar>\r\n"
			        + "            <Default>GetSetting('BodyLength')</Default>\r\n" + "            <IncludeChinese>true</IncludeChinese>\r\n"
			        + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n" + "    </HeaderDef>\r\n" + "    <BodyDef>\r\n"
			        + "        <FieldDef Type = \"F\">\r\n" + "            <Name>L11</Name>\r\n" + "            <CName>L11</CName>\r\n"
			        + "            <Type>9</Type>\r\n" + "            <Length>10</Length>\r\n" + "            <Scale>0</Scale>\r\n"
			        + "            <Justify>R</Justify>\r\n" + "            <PadChar>0</PadChar>\r\n" + "            <Default>0</Default>\r\n"
			        + "            <IncludeChinese>true</IncludeChinese>\r\n" + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n"
			        + "        <FieldDef Type = \"R\" Value = \"1\">\r\n" + "            <FieldDef Type = \"F\">\r\n" + "                <Name>L21</Name>\r\n"
			        + "                <CName>L21</CName>\r\n" + "                <Type>X</Type>\r\n" + "                <Length>10</Length>\r\n"
			        + "                <Scale>0</Scale>\r\n" + "                <Justify>L</Justify>\r\n" + "                <PadChar> </PadChar>\r\n"
			        + "                <Default> </Default>\r\n" + "                <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                <Optional>true</Optional>\r\n" + "            </FieldDef>\r\n" + "            <FieldDef Type = \"F\">\r\n"
			        + "                <Name>L22</Name>\r\n" + "                <CName>L22</CName>\r\n" + "                <Type>9</Type>\r\n"
			        + "                <Length>1</Length>\r\n" + "                <Scale>0</Scale>\r\n" + "                <Justify>R</Justify>\r\n"
			        + "                <PadChar> </PadChar>\r\n" + "                <Default> </Default>\r\n"
			        + "                <IncludeChinese>true</IncludeChinese>\r\n" + "                <Optional>true</Optional>\r\n"
			        + "            </FieldDef>\r\n" + "            <FieldDef Type = \"R\" Value = \"L22\">\r\n" + "                <FieldDef Type = \"F\">\r\n"
			        + "                    <Name>L31</Name>\r\n" + "                    <CName>L31</CName>\r\n" + "                    <Type>X</Type>\r\n"
			        + "                    <Length>10</Length>\r\n" + "                    <Scale>0</Scale>\r\n"
			        + "                    <Justify>L</Justify>\r\n" + "                    <PadChar> </PadChar>\r\n"
			        + "                    <Default> </Default>\r\n" + "                    <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                    <Optional>true</Optional>\r\n" + "                </FieldDef>\r\n"
			        + "                <FieldDef Type = \"R\" Value = \"2\">\r\n" + "                    <FieldDef Type = \"F\">\r\n"
			        + "                        <Name>L41</Name>\r\n" + "                        <CName>L41</CName>\r\n"
			        + "                        <Type>X</Type>\r\n" + "                        <Length>10</Length>\r\n"
			        + "                        <Scale>0</Scale>\r\n" + "                        <Justify>L</Justify>\r\n"
			        + "                        <PadChar> </PadChar>\r\n" + "                        <Default> </Default>\r\n"
			        + "                        <IncludeChinese>true</IncludeChinese>\r\n" + "                        <Optional>true</Optional>\r\n"
			        + "                    </FieldDef>\r\n" + "                    <FieldDef Type = \"F\">\r\n" + "                        <Name>L42</Name>\r\n"
			        + "                        <CName>L42</CName>\r\n" + "                        <Type>X</Type>\r\n"
			        + "                        <Length>10</Length>\r\n" + "                        <Scale>0</Scale>\r\n"
			        + "                        <Justify>L</Justify>\r\n" + "                        <PadChar> </PadChar>\r\n"
			        + "                        <Default> </Default>\r\n" + "                        <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                        <Optional>true</Optional>\r\n" + "                    </FieldDef>\r\n" + "                </FieldDef>\r\n"
			        + "                <FieldDef Type = \"F\">\r\n" + "                    <Name>L32</Name>\r\n" + "                    <CName>L32</CName>\r\n"
			        + "                    <Type>X</Type>\r\n" + "                    <Length>10</Length>\r\n" + "                    <Scale>0</Scale>\r\n"
			        + "                    <Justify>L</Justify>\r\n" + "                    <PadChar> </PadChar>\r\n"
			        + "                    <Default> </Default>\r\n" + "                    <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                    <Optional>true</Optional>\r\n" + "                </FieldDef>\r\n" + "            </FieldDef>\r\n"
			        + "            <FieldDef Type = \"F\">\r\n" + "                <Name>L23</Name>\r\n" + "                <CName>L23</CName>\r\n"
			        + "                <Type>X</Type>\r\n" + "                <Length>10</Length>\r\n" + "                <Scale>0</Scale>\r\n"
			        + "                <Justify>L</Justify>\r\n" + "                <PadChar> </PadChar>\r\n" + "                <Default> </Default>\r\n"
			        + "                <IncludeChinese>true</IncludeChinese>\r\n" + "                <Optional>true</Optional>\r\n"
			        + "            </FieldDef>\r\n" + "        </FieldDef>\r\n" + "        <FieldDef Type = \"F\">\r\n" + "            <Name>L12</Name>\r\n"
			        + "            <CName>L12</CName>\r\n" + "            <Type>9</Type>\r\n" + "            <Length>10</Length>\r\n"
			        + "            <Scale>2</Scale>\r\n" + "            <Justify>L</Justify>\r\n" + "            <PadChar>0</PadChar>\r\n"
			        + "            <Default>0</Default>\r\n" + "            <IncludeChinese>true</IncludeChinese>\r\n"
			        + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n" + "        <FieldDef Field = \"H4\" Type = \"S\">\r\n"
			        + "            <FieldDef Type = \"C\" Value = \"0001\">\r\n" + "                <FieldDef Type = \"F\">\r\n"
			        + "                    <Name>L221</Name>\r\n" + "                    <CName>L221</CName>\r\n" + "                    <Type>X</Type>\r\n"
			        + "                    <Length>10</Length>\r\n" + "                    <Scale>0</Scale>\r\n"
			        + "                    <Justify>L</Justify>\r\n" + "                    <PadChar> </PadChar>\r\n"
			        + "                    <Default> </Default>\r\n" + "                    <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                    <Optional>true</Optional>\r\n" + "                </FieldDef>\r\n"
			        + "                <FieldDef Type = \"R\" Value = \"2\">\r\n" + "                    <FieldDef Type = \"F\">\r\n"
			        + "                        <Name>L321</Name>\r\n" + "                        <CName>L321</CName>\r\n"
			        + "                        <Type>X</Type>\r\n" + "                        <Length>10</Length>\r\n"
			        + "                        <Scale>0</Scale>\r\n" + "                        <Justify>L</Justify>\r\n"
			        + "                        <PadChar> </PadChar>\r\n" + "                        <Default> </Default>\r\n"
			        + "                        <IncludeChinese>true</IncludeChinese>\r\n" + "                        <Optional>true</Optional>\r\n"
			        + "                    </FieldDef>\r\n" + "                </FieldDef>\r\n" + "            </FieldDef>\r\n"
			        + "            <FieldDef Type = \"C\" Value = \"0002\">\r\n" + "                <FieldDef Field = \"L11\" Type = \"S\">\r\n"
			        + "                    <FieldDef Type = \"C\" Value = \"2\">\r\n" + "                        <FieldDef Type = \"R\" Value = \"L11\">\r\n"
			        + "                            <FieldDef Type = \"F\">\r\n" + "                                <Name>L221</Name>\r\n"
			        + "                                <CName>L221</CName>\r\n" + "                                <Type>X</Type>\r\n"
			        + "                                <Length>10</Length>\r\n" + "                                <Scale>0</Scale>\r\n"
			        + "                                <Justify>L</Justify>\r\n" + "                                <PadChar> </PadChar>\r\n"
			        + "                                <Default> </Default>\r\n" + "                                <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                                <Optional>true</Optional>\r\n" + "                            </FieldDef>\r\n"
			        + "                            <FieldDef Type = \"F\">\r\n" + "                                <Name>L222</Name>\r\n"
			        + "                                <CName>L222</CName>\r\n" + "                                <Type>9</Type>\r\n"
			        + "                                <Length>10</Length>\r\n" + "                                <Scale>0</Scale>\r\n"
			        + "                                <Justify>R</Justify>\r\n" + "                                <PadChar>0</PadChar>\r\n"
			        + "                                <Default>0</Default>\r\n" + "                                <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                                <Optional>true</Optional>\r\n" + "                            </FieldDef>\r\n"
			        + "                            <FieldDef Type = \"R\" Value = \"L222\">\r\n" + "                                <FieldDef Type = \"F\">\r\n"
			        + "                                    <Name>L321</Name>\r\n" + "                                    <CName>L321</CName>\r\n"
			        + "                                    <Type>X</Type>\r\n" + "                                    <Length>10</Length>\r\n"
			        + "                                    <Scale>0</Scale>\r\n" + "                                    <Justify>L</Justify>\r\n"
			        + "                                    <PadChar> </PadChar>\r\n" + "                                    <Default> </Default>\r\n"
			        + "                                    <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                                    <Optional>true</Optional>\r\n" + "                                </FieldDef>\r\n"
			        + "                                <FieldDef Type = \"F\">\r\n" + "                                    <Name>L322</Name>\r\n"
			        + "                                    <CName>L322</CName>\r\n" + "                                    <Type>X</Type>\r\n"
			        + "                                    <Length>10</Length>\r\n" + "                                    <Scale>0</Scale>\r\n"
			        + "                                    <Justify>L</Justify>\r\n" + "                                    <PadChar> </PadChar>\r\n"
			        + "                                    <Default> </Default>\r\n"
			        + "                                    <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                                    <Optional>true</Optional>\r\n" + "                                </FieldDef>\r\n"
			        + "                            </FieldDef>\r\n" + "                        </FieldDef>\r\n" + "                    </FieldDef>\r\n"
			        + "                    <FieldDef Type = \"C\" Value = \"21\">\r\n" + "                        <FieldDef Type = \"F\">\r\n"
			        + "                            <Name>L325</Name>\r\n" + "                            <CName>L325</CName>\r\n"
			        + "                            <Type>X</Type>\r\n" + "                            <Length>10</Length>\r\n"
			        + "                            <Scale>0</Scale>\r\n" + "                            <Justify>L</Justify>\r\n"
			        + "                            <PadChar> </PadChar>\r\n" + "                            <Default> </Default>\r\n"
			        + "                            <IncludeChinese>true</IncludeChinese>\r\n" + "                            <Optional>true</Optional>\r\n"
			        + "                        </FieldDef>\r\n" + "                    </FieldDef>\r\n" + "                </FieldDef>\r\n"
			        + "            </FieldDef>\r\n" + "        </FieldDef>\r\n" + "        <FieldDef Type = \"F\">\r\n" + "            <Name>L13</Name>\r\n"
			        + "            <CName>L13</CName>\r\n" + "            <Type>9</Type>\r\n" + "            <Length>10</Length>\r\n"
			        + "            <Scale>2</Scale>\r\n" + "            <Justify>L</Justify>\r\n" + "            <PadChar>0</PadChar>\r\n"
			        + "            <Default>0</Default>\r\n" + "            <IncludeChinese>true</IncludeChinese>\r\n"
			        + "            <Optional>true</Optional>\r\n" + "        </FieldDef>\r\n" + "        <FieldDef Type = \"R\" Value = \"1\">\r\n"
			        + "            <FieldDef Type = \"F\">\r\n" + "                <Name>L24</Name>\r\n" + "                <CName>L24</CName>\r\n"
			        + "                <Type>X</Type>\r\n" + "                <Length>10</Length>\r\n" + "                <Scale>0</Scale>\r\n"
			        + "                <Justify>L</Justify>\r\n" + "                <PadChar> </PadChar>\r\n" + "                <Default> </Default>\r\n"
			        + "                <IncludeChinese>true</IncludeChinese>\r\n" + "                <Optional>true</Optional>\r\n"
			        + "            </FieldDef>\r\n" + "            <FieldDef Type = \"R\" Value = \"2\">\r\n" + "                <FieldDef Type = \"F\">\r\n"
			        + "                    <Name>L324</Name>\r\n" + "                    <CName>L324</CName>\r\n" + "                    <Type>X</Type>\r\n"
			        + "                    <Length>10</Length>\r\n" + "                    <Scale>0</Scale>\r\n"
			        + "                    <Justify>L</Justify>\r\n" + "                    <PadChar> </PadChar>\r\n"
			        + "                    <Default> </Default>\r\n" + "                    <IncludeChinese>true</IncludeChinese>\r\n"
			        + "                    <Optional>true</Optional>\r\n" + "                </FieldDef>\r\n" + "            </FieldDef>\r\n"
			        + "        </FieldDef>\r\n" + "    </BodyDef>\r\n" + "</DirectionDef>";

			String txnDataStr = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>\r\n"
			        + "<TxnData xmlns = \"http://fubon.com.tw/XSD/ESB/Txn/TxnData\" xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation = \"http://fubon.com.tw/XSD/ESB/Txn/TxnData ../../../../SharedResources/Schemas/Txn/TxnData.xsd\">\r\n"
			        + "    <Header>\r\n" + "        <Field Type = \"F\">\r\n" + "            <Name>H1</Name>\r\n" + "            <Value></Value>\r\n"
			        + "        </Field>\r\n" + "        <Field Type = \"F\">\r\n" + "            <Name>H2</Name>\r\n" + "            <Value>20</Value>\r\n"
			        + "        </Field>\r\n" + "        <Field Type = \"F\">\r\n" + "            <Name>H3</Name>\r\n" + "            <Value>M</Value>\r\n"
			        + "        </Field>\r\n" + "        <Field Type = \"F\">\r\n" + "            <Name>H4</Name>\r\n" + "            <Value>0002</Value>\r\n"
			        + "        </Field>\r\n" + "    </Header>\r\n" + "    <Body>\r\n" + "        <Field Type = \"F\">\r\n" + "            <Name>L11</Name>\r\n"
			        + "            <Value>2</Value>\r\n" + "        </Field>\r\n" + "        <Field Type = \"R\">\r\n" + "            <Field Type = \"F\">\r\n"
			        + "                <Name>L21</Name>\r\n" + "                <Value>V21</Value>\r\n" + "            </Field>\r\n"
			        + "            <Field Type = \"F\">\r\n" + "                <Name>L22</Name>\r\n" + "                <Value>2</Value>\r\n"
			        + "            </Field>\r\n" + "            <Field Type = \"R\">\r\n" + "                <Field Type = \"F\">\r\n"
			        + "                    <Name>L31</Name>\r\n" + "                    <Value>V31</Value>\r\n" + "                </Field>\r\n"
			        + "                <Field Type = \"R\">\r\n" + "                    <Field Type = \"F\">\r\n"
			        + "                        <Name>L41</Name>\r\n" + "                        <Value>V41-1</Value>\r\n" + "                    </Field>\r\n"
			        + "                    <Field Type = \"F\">\r\n" + "                        <Name>L42</Name>\r\n"
			        + "                        <Value>V42-1</Value>\r\n" + "                    </Field>\r\n" + "                </Field>\r\n"
			        + "                <Field Type = \"R\">\r\n" + "                    <Field Type = \"F\">\r\n"
			        + "                        <Name>L41</Name>\r\n" + "                        <Value>V41-2</Value>\r\n" + "                    </Field>\r\n"
			        + "                    <Field Type = \"F\">\r\n" + "                        <Name>L42</Name>\r\n"
			        + "                        <Value>V42-2</Value>\r\n" + "                    </Field>\r\n" + "                </Field>\r\n"
			        + "                <Field Type = \"F\">\r\n" + "                    <Name>L32</Name>\r\n" + "                    <Value>V32</Value>\r\n"
			        + "                </Field>\r\n" + "            </Field>\r\n" + "            <Field Type = \"R\">\r\n"
			        + "                <Field Type = \"F\">\r\n" + "                    <Name>L31</Name>\r\n" + "                    <Value>V31-2</Value>\r\n"
			        + "                </Field>\r\n" + "                <Field Type = \"R\">\r\n" + "                    <Field Type = \"F\">\r\n"
			        + "                        <Name>L41</Name>\r\n" + "                        <Value>V41-3</Value>\r\n" + "                    </Field>\r\n"
			        + "                    <Field Type = \"F\">\r\n" + "                        <Name>L42</Name>\r\n"
			        + "                        <Value>V42-3</Value>\r\n" + "                    </Field>\r\n" + "                </Field>\r\n"
			        + "                <Field Type = \"R\">\r\n" + "                    <Field Type = \"F\">\r\n"
			        + "                        <Name>L41</Name>\r\n" + "                        <Value>V41-4</Value>\r\n" + "                    </Field>\r\n"
			        + "                    <Field Type = \"F\">\r\n" + "                        <Name>L42</Name>\r\n"
			        + "                        <Value>V42-4</Value>\r\n" + "                    </Field>\r\n" + "                </Field>\r\n"
			        + "                <Field Type = \"F\">\r\n" + "                    <Name>L32</Name>\r\n" + "                    <Value>V32-2</Value>\r\n"
			        + "                </Field>\r\n" + "            </Field>\r\n" + "            <Field Type = \"F\">\r\n"
			        + "                <Name>L23</Name>\r\n" + "                <Value>V23</Value>\r\n" + "            </Field>\r\n" + "        </Field>\r\n"
			        + "        <Field Type = \"F\">\r\n" + "            <Name>L12</Name>\r\n" + "            <Value>12.2</Value>\r\n" + "        </Field>\r\n"
			        + "        <Field Type = \"R\">\r\n" + "            <Field Type = \"F\">\r\n" + "                <Name>L221</Name>\r\n"
			        + "                <Value>V221</Value>\r\n" + "            </Field>\r\n" + "            <Field Type = \"F\">\r\n"
			        + "                <Name>L222</Name>\r\n" + "                <Value>2</Value>\r\n" + "            </Field>\r\n"
			        + "            <Field Type = \"R\">\r\n" + "                <Field Type = \"F\">\r\n" + "                    <Name>L321</Name>\r\n"
			        + "                    <Value>V321-1</Value>\r\n" + "                </Field>\r\n" + "                <Field Type = \"F\">\r\n"
			        + "                    <Name>L322</Name>\r\n" + "                    <Value>V322-1</Value>\r\n" + "                </Field>\r\n"
			        + "            </Field>\r\n" + "            <Field Type = \"R\">\r\n" + "                <Field Type = \"F\">\r\n"
			        + "                    <Name>L321</Name>\r\n" + "                    <Value>V321-2</Value>\r\n" + "                </Field>\r\n"
			        + "                <Field Type = \"F\">\r\n" + "                    <Name>L322</Name>\r\n" + "                    <Value>V322-2</Value>\r\n"
			        + "                </Field>\r\n" + "            </Field>\r\n" + "        </Field>\r\n" + "        <Field Type = \"R\">\r\n"
			        + "            <Field Type = \"F\">\r\n" + "                <Name>L221</Name>\r\n" + "                <Value>V221-2</Value>\r\n"
			        + "            </Field>\r\n" + "            <Field Type = \"F\">\r\n" + "                <Name>L222</Name>\r\n"
			        + "                <Value>2</Value>\r\n" + "            </Field>\r\n" + "            <Field Type = \"R\">\r\n"
			        + "                <Field Type = \"F\">\r\n" + "                    <Name>L321</Name>\r\n" + "                    <Value>V321-3</Value>\r\n"
			        + "                </Field>\r\n" + "                <Field Type = \"F\">\r\n" + "                    <Name>L322</Name>\r\n"
			        + "                    <Value>V322-3</Value>\r\n" + "                </Field>\r\n" + "            </Field>\r\n"
			        + "            <Field Type = \"R\">\r\n" + "                <Field Type = \"F\">\r\n" + "                    <Name>L321</Name>\r\n"
			        + "                    <Value>V321-4</Value>\r\n" + "                </Field>\r\n" + "                <Field Type = \"F\">\r\n"
			        + "                    <Name>L322</Name>\r\n" + "                    <Value>V322-4</Value>\r\n" + "                </Field>\r\n"
			        + "            </Field>\r\n" + "        </Field>\r\n" + "        <Field Type = \"F\">\r\n" + "            <Name>L13</Name>\r\n"
			        + "            <Value>13.2</Value>\r\n" + "        </Field>\r\n" + "        <Field Type = \"R\">\r\n"
			        + "            <Field Type = \"F\">\r\n" + "                <Name>L24</Name>\r\n" + "                <Value>V24</Value>\r\n"
			        + "            </Field>\r\n" + "            <Field Type = \"R\">\r\n" + "                <Field Type = \"F\">\r\n"
			        + "                    <Name>L324</Name>\r\n" + "                    <Value>V324-1</Value>\r\n" + "                </Field>\r\n"
			        + "            </Field>\r\n" + "            <Field Type = \"R\">\r\n" + "                <Field Type = \"F\">\r\n"
			        + "                    <Name>L324</Name>\r\n" + "                    <Value>V324-2</Value>\r\n" + "                </Field>\r\n"
			        + "            </Field>\r\n" + "        </Field>\r\n" + "    </Body>\r\n" + "</TxnData>";

			String encoding = "Big5";

			TransactionMessage message = new TransactionMessage();

			message.setParameter1( directionDefStr );
			message.setParameter2( txnDataStr );
			message.setParameter3( encoding );
			
			JAXBContext jaxbContext = JAXBContext.newInstance( TransactionMessage.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter result = new StringWriter();
			jaxbMarshaller.marshal( message, result );
			
//			logger.info( result.toString() );
			logger.info( "start" );
			requestToTranslate( "fubon.esb.render.to.txt.1.transform", result.toString() );
			logger.info( "end" );
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}
	
	protected void requestToTranslate(String queueName, String data) {
		QueueConnectionFactory factory = null;
		QueueConnection connection = null;
		
		try {
			factory = new TibjmsQueueConnectionFactory( "tcp://172.19.241.5:7222,tcp://172.19.241.5:7224" );
			connection = factory.createQueueConnection( "","" );
			QueueSession session = connection.createQueueSession( false, javax.jms.Session.AUTO_ACKNOWLEDGE );

			Queue senderQueue = session.createQueue( queueName );
			QueueSender sender = session.createSender( senderQueue );

			for (int i = 0; i < 10000; i++) {
				TextMessage message = session.createTextMessage();
				message.setText( data );

				sender.send( message );
			}
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}
	}
}
