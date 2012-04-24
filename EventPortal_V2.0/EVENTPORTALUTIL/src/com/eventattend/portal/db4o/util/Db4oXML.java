package com.eventattend.portal.db4o.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Db4oXML {
	public static Map<String,String> value = new HashMap<String,String>();
	
	public  static void readXMLFile(){
	    try {
	    	Properties properties = System.getProperties();
	    	String path = properties.getProperty("Db4oConfigXML");
	    	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	    	Document document = documentBuilder.parse(new File(path));
	    	
	    	NodeList list = document.getElementsByTagName("server");
	    	Node firstNode = list.item(0);
	    	if(firstNode.getNodeType() == Node.ELEMENT_NODE){
                Element firstElement = (Element)firstNode;
                
                NodeList serverHost = firstElement.getElementsByTagName("serverHost");
                Element server = (Element)serverHost.item(0);
                NodeList serverHostIP = server.getChildNodes();
                String ip = ((Node)serverHostIP.item(0)).getNodeValue().trim();
                System.out.println("Db4oXML serverHost ==>" +ip);
                value.put("serverHost", ip);

                NodeList serverDBFile = firstElement.getElementsByTagName("serverDBFile");
                Element serverFile = (Element)serverDBFile.item(0);
                NodeList file = serverFile.getChildNodes();
                String dbFile = ((Node)file.item(0)).getNodeValue().trim();
                System.out.println("Db4oXML serverDBFile ==>" +dbFile);
                value.put("serverDBFile", dbFile);

                NodeList serverPort = firstElement.getElementsByTagName("serverPort");
                Element serverUnUsedPort = (Element)serverPort.item(0);
                NodeList serverHostPort = serverUnUsedPort.getChildNodes();
                String port = ((Node)serverHostPort.item(0)).getNodeValue().trim();
                System.out.println("Db4oXML serverPort ==>" +port);
                value.put("serverPort", port);

                NodeList serverUID = firstElement.getElementsByTagName("serverUID");
                Element serverUser = (Element)serverUID.item(0);
                NodeList serverUserId = serverUser.getChildNodes();
                String user = ((Node)serverUserId.item(0)).getNodeValue().trim();
                System.out.println("Db4oXML serverUID ==>" +user);
                value.put("serverUID", user);

                NodeList serverPwd = firstElement.getElementsByTagName("serverPwd");
                Element serverPass = (Element)serverPwd.item(0);
                NodeList serverPassword = serverPass.getChildNodes();
                String password = ((Node)serverPassword.item(0)).getNodeValue().trim();
                System.out.println("Db4oXML serverPwd ==>" +password);
                value.put("serverPwd", password);

	    	}
	    } catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
}
