package com.eventattend.portal.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MailXML {
	public static Map value = new HashMap();
	public  static void readXMLFile(){
    try {
    		Properties properties = System.getProperties();
    		String path = properties.getProperty("mailPath");
    		String wp_dir = System.getProperty("workspace.name");
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    //        String URLPath = request.getRealPath(getServlet().getServletContext().getRealPath("/"));
		//	paySlipDTO.setURLPATH(URLPath);
           Document doc = docBuilder.parse (new File(path));
           // Document doc = docBuilder.parse (new File(System.getProperty("file")));
            //Document doc = docBuilder.parse (new  File( getClass().getClassLoader().getResourceAsStream("Sample.xml"));
            NodeList listOfPersons = doc.getElementsByTagName("mail");
            Node firstPersonNode = listOfPersons.item(0);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
                    Element firstPersonElement = (Element)firstPersonNode;
                    NodeList smartadminuid = firstPersonElement.getElementsByTagName("smartadminuid");
                    Element smartadminid = (Element)smartadminuid.item(0);
                    NodeList smartadminID = smartadminid.getChildNodes();
                    String uid = ((Node)smartadminID.item(0)).getNodeValue().trim();
                    value.put("smartadminuid", uid);
                    NodeList smartadminpwd = firstPersonElement.getElementsByTagName("smartadminpwd");
                    Element smartadminPw = (Element)smartadminpwd.item(0);
                    NodeList smartadminPWD = smartadminPw.getChildNodes();
                    String pwd= ((Node)smartadminPWD.item(0)).getNodeValue().trim();
                    value.put("smartadminpwd", pwd);
                    NodeList smarthruid = firstPersonElement.getElementsByTagName("smarthruid");
                    Element smarthrid = (Element)smarthruid.item(0);
                    NodeList smarthrID = smarthrid.getChildNodes();
                    String hrId =((Node)smarthrID.item(0)).getNodeValue().trim();
                    value.put("smarthruid", hrId);
                    NodeList smarthrpwd = firstPersonElement.getElementsByTagName("smarthrpwd");
                    Element smarthrpw = (Element)smarthrpwd.item(0);
                    NodeList smarthrPWD = smarthrpw.getChildNodes();
                    String hrpwd = ((Node)smarthrPWD.item(0)).getNodeValue().trim();
                    value.put("smarthrpwd", hrpwd);
                }
        }catch (SAXParseException err) {
        System.out.println(" " + err.getMessage ());
        }catch (SAXException e) {
        	System.out.println(" " + e.getMessage ());
        }catch (FileNotFoundException t) {
        	t.printStackTrace();
        }
        catch (Exception t) {
        	t.printStackTrace();
        }
    }

}
