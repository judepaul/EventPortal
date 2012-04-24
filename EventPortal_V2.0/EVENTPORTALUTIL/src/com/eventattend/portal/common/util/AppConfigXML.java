package com.eventattend.portal.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AppConfigXML {

	public static Map<String,String> appConfigMap = new HashMap<String,String>();
	
	
	public  static void readAppConfigXMLFile(){
		
		Properties properties = null;
		DocumentBuilderFactory documentBuilderFactory = null;
		DocumentBuilder documentBuilder = null;
		Document document = null;
		NodeList nodeList = null;
		Node firstNode = null;		
		
		String xmlFilePath = null;		
		
		try {
		
		properties = System.getProperties();
		
		xmlFilePath = properties.getProperty("AppConfigXMLPath");
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
    	
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
    	document = documentBuilder.parse(new File(xmlFilePath));
    	
    	nodeList = document.getElementsByTagName("app-settings");
    	
    	firstNode = nodeList.item(0);
    	
    	if(firstNode.getNodeType() == Node.ELEMENT_NODE){
    		
    		String appMode = null; 
    		
    		 Element firstElement = (Element)firstNode;
 
    		 NodeList appModeNodeList = firstElement.getElementsByTagName("app-mode");
             Element appModeElement = (Element)appModeNodeList.item(0);
             NodeList appModeNode = appModeElement.getChildNodes();
             appMode = ((Node)appModeNode.item(0)).getNodeValue().trim();
             //System.out.println("Application Mode ==>" +appMode);
             appConfigMap.put("app-mode", appMode);

    	}
    	
    	
    	} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
			
		} catch (SAXException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}catch (Exception e) {
			
			e.printStackTrace();
		}
    	
    	
	
	}
	
}
