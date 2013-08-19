package cn.edu.seu.cose;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import android.util.Xml;

public class XML_Person {
	PersonInfo person =  new PersonInfo();
	

	public PersonInfo parsePersonXML(InputStream is){
		PersonInfo person = new PersonInfo();
		XmlPullParser xpp = Xml.newPullParser();
		try {
			xpp.setInput(is, "utf-8");
			for (int i = xpp.getEventType(); i != XmlPullParser.END_DOCUMENT; i = xpp.next())
			{
			    switch (i) {  
	            case XmlPullParser.START_TAG:  
	                if (xpp.getName().equals("userName"))
	                	person.setUserName(xpp.nextText());
	                else if (xpp.getName().equals("customerName")) 
	                	person.setCustomerName(xpp.nextText());
	                else if (xpp.getName().equals("cardNum")) 
	                	person.setCardNum(xpp.nextText());
	                else if(xpp.getName().equals("bluetoothMac"))
	                	person.setBluetoothMac(xpp.nextText());
	                else if(xpp.getName().equals("dynamicPasswordNum"))
	                	person.setDynamicPasswordNum(xpp.nextText());
	                else if(xpp.getName().equals("identificationCardNum"))
	                	person.setIdentificationCardNum(xpp.nextText());
	                else if(xpp.getName().equals("phoneNum"))
	                	person.setPhoneNum(xpp.nextText());
	                break;
	            }
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
				Log.e("错误","未成功接收xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return person;
    }
	
	public String productSentenceXML(String info)
    {
    	 StringWriter stringWriter = new StringWriter();  
	        try {  
	            // 获取XmlSerializer对象  
	            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
	            XmlSerializer xmlSerializer = factory.newSerializer();  
	            // 设置输出流对象  
	            xmlSerializer.setOutput(stringWriter);  
	         
	            xmlSerializer.startDocument("utf-8", true);
	            xmlSerializer.startTag(null, "information");
	            xmlSerializer.attribute(null, "event", "sentence");
	            xmlSerializer.text(info);
	            xmlSerializer.endTag(null, "information");
	            xmlSerializer.endTag(null, "information");
    		} catch (Exception e) {  
    			e.printStackTrace();  
    		}  
    		return stringWriter.toString();  
    }

	public static String parseSentenceXML(InputStream is)
    {
    	String sentence="";
        XmlPullParser xpp = Xml.newPullParser(); 
        try {
			xpp.setInput(is, "utf-8");
			for (int i = xpp.getEventType(); i != XmlPullParser.END_DOCUMENT; i = xpp.next())
			{        	
			    switch (i) {  
	            case XmlPullParser.START_TAG: 
	            	if(xpp.getName().equals("information"))
	            	{

	            		if(!xpp.getAttributeValue(null,"event").equals("sentence"))
	            			return sentence;
	            		else
	            		{	
	            			sentence=xpp.nextText();
	            		}

	            	}
	                
	            }
			}

		} catch (XmlPullParserException e) {
				Log.e("错误","未成功接收xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
        return sentence;
    }
	
	public String producePersonXML(String event){  
        StringWriter stringWriter = new StringWriter(); 
        try {  
            // 获取XmlSerializer对象  
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
            XmlSerializer xmlSerializer = factory.newSerializer();  
            // 设置输出流对象  
            xmlSerializer.setOutput(stringWriter);  
         
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "information");
            xmlSerializer.attribute(null, "event", event);
                xmlSerializer.startTag(null, "personInfo");  
                xmlSerializer.startTag(null, "userName");  
                xmlSerializer.text(person.getUserName());  
                xmlSerializer.endTag(null, "userName"); 
                
                xmlSerializer.startTag(null, "customerName");  
                xmlSerializer.text(person.getCustomerName());  
                xmlSerializer.endTag(null, "customerName");  
                  
                xmlSerializer.startTag(null, "cardNum");  
                xmlSerializer.text(person.getCardNum());  
                xmlSerializer.endTag(null, "cardNum");  
                
                xmlSerializer.startTag(null, "password");  
                xmlSerializer.text(person.getPassword());  
                xmlSerializer.endTag(null, "password");  
                
                xmlSerializer.startTag(null, "bluetoothMac");  
                xmlSerializer.text(person.getBluetoothMac());  
                xmlSerializer.endTag(null, "bluetoothMac"); 
                
                xmlSerializer.startTag(null, "dynamicPasswordNum");  
                xmlSerializer.text(person.getDynamicPasswordNum());  
                xmlSerializer.endTag(null, "dynamicPasswordNum"); 
                
                xmlSerializer.startTag(null, "identificationCardNum");  
                xmlSerializer.text(person.getIdentificationCardNum());  
                xmlSerializer.endTag(null, "identificationCardNum"); 
                
                xmlSerializer.startTag(null, "phoneNum");  
                xmlSerializer.text(person.getPhoneNum());  
                xmlSerializer.endTag(null, "phoneNum"); 
                
                xmlSerializer.startTag(null, "balance");  
                xmlSerializer.text(person.getBalance());  
                xmlSerializer.endTag(null, "balance");  
                  
                xmlSerializer.endTag(null, "personInfo");
            xmlSerializer.endTag(null, "information");
            xmlSerializer.endDocument(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return stringWriter.toString();  
    }  
	
	public String produceRegisterXML(String event){  
        StringWriter stringWriter = new StringWriter(); 
        try {  
            // 获取XmlSerializer对象  
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
            XmlSerializer xmlSerializer = factory.newSerializer();  
            // 设置输出流对象  
            xmlSerializer.setOutput(stringWriter);  
         
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "information");
            xmlSerializer.attribute(null, "event", event);
            xmlSerializer.startTag(null, "personInfo");  
            
            xmlSerializer.startTag(null, "userName"); 
            xmlSerializer.text(person.getUserName());  
            xmlSerializer.endTag(null, "userName"); 
            
            xmlSerializer.startTag(null, "customerName");  
            xmlSerializer.text(person.getCustomerName());  
            xmlSerializer.endTag(null, "customerName"); 
            
            xmlSerializer.startTag(null, "password");  
            xmlSerializer.text(person.getPassword());  
            xmlSerializer.endTag(null, "password");  
            
            xmlSerializer.startTag(null, "bluetoothMac");  
            xmlSerializer.text(person.getBluetoothMac());  
            xmlSerializer.endTag(null, "bluetoothMac"); 
            
            xmlSerializer.endTag(null, "personInfo");
            xmlSerializer.endTag(null, "information");
            xmlSerializer.endDocument(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return stringWriter.toString();  
    }
	
	public String produceUserNameXML(String event){  
        StringWriter stringWriter = new StringWriter(); 
        try {  
            // 获取XmlSerializer对象  
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
            XmlSerializer xmlSerializer = factory.newSerializer();  
            // 设置输出流对象  
            xmlSerializer.setOutput(stringWriter);  

            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "information");
            xmlSerializer.attribute(null, "event", event);
            xmlSerializer.startTag(null, "personInfo");  
            xmlSerializer.startTag(null, "userName");  
            xmlSerializer.text(person.getUserName());  
            xmlSerializer.endTag(null, "userName");  
            xmlSerializer.endTag(null, "personInfo");
            xmlSerializer.endTag(null, "information");

            xmlSerializer.endDocument(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return stringWriter.toString();  
    }
	
	public String produceLinkBankCardXML(String event){  
        StringWriter stringWriter = new StringWriter(); 
        try {  
            // 获取XmlSerializer对象  
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
            XmlSerializer xmlSerializer = factory.newSerializer();  
            // 设置输出流对象  
            xmlSerializer.setOutput(stringWriter);  
         
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "information");
            xmlSerializer.attribute(null, "event", event);
            xmlSerializer.startTag(null, "personInfo");  
            
            xmlSerializer.startTag(null, "userName"); 
            xmlSerializer.text(person.getUserName());  
            xmlSerializer.endTag(null, "userName");
            
            xmlSerializer.startTag(null, "cardNum"); 
            xmlSerializer.text(person.getCardNum());  
            xmlSerializer.endTag(null, "cardNum"); 
            
            xmlSerializer.startTag(null, "phoneNum");  
            xmlSerializer.text(person.getPhoneNum());  
            xmlSerializer.endTag(null, "phoneNum"); 
            
            xmlSerializer.startTag(null, "identificationCardNum");  
            xmlSerializer.text(person.getIdentificationCardNum());  
            xmlSerializer.endTag(null, "identificationCardNum");  
            
            xmlSerializer.endTag(null, "personInfo");
            xmlSerializer.endTag(null, "information");
            xmlSerializer.endDocument(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return stringWriter.toString();  
    }
	
    public void addPersonData(String userName, String customerName, String cardNum,String password,String bluetoothMac, 
    	String dynamicPasswordNum, String identificationCardNum, String phoneNum, String balance){  
        PersonInfo newPerson =new PersonInfo(userName, customerName, cardNum,password, bluetoothMac, dynamicPasswordNum, identificationCardNum, phoneNum,balance);
        person=newPerson;
    } 
    
    public void addPersonUserName(String userName){
    	person.setUserName(userName);

    }
    
    public void addPersonRegister(String userName, String password, String realName, String bluetoothMac){
    	person.setUserName(userName);
    	person.setPassword(password);
    	person.setCustomerName(realName);
    	person.setBluetoothMac(bluetoothMac);
    }
    
    public void addPersonLinkBankCard(String userName, String cardNum, String phoneNum, String idCardNum){
    	person.setUserName(userName);
    	person.setCardNum(cardNum);
    	person.setPhoneNum(phoneNum);
    	person.setIdentificationCardNum(idCardNum);
    }
    
    
}
			
		                                                                                                               