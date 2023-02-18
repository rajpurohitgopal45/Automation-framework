package Utility;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Page_Object_Reader {
    public static final String XML_PAGE_NODE = "page";
    public static final String XML_ELEMENT_NODE = "element";

    public static Node getXMLElement(String strElementName) {

        String strXpath;
        Node xmlElement = null;

        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            if (strElementName.contains(".")) {
                String strPage = strElementName.split("\\.")[0];
                String strElement = strElementName.split("\\.")[1];
                strXpath = "//" + XML_PAGE_NODE + "[@name='" + strPage + "']/element[@name='" + strElement + "']";
            } else {
                strXpath = "//" + XML_ELEMENT_NODE + "[@name='" + strElementName + "']";
            }

            XPathExpression expr = xpath.compile(strXpath);
            Object result = expr.evaluate(getDOM(), XPathConstants.NODE);
            xmlElement = (Node) result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlElement;
    }


    public static Map<String, String> getElementProperties(String strElementName) {

        Map<String, String> mapElement = new HashMap<>();

        try {
            Node xmlElement = getXMLElement(strElementName);
            if (xmlElement != null) {
                mapElement.put("name", xmlElement.getAttributes().getNamedItem("name").getNodeValue());
                mapElement.put("by", xmlElement.getAttributes().getNamedItem("by").getNodeValue());
                mapElement.put("identifier", xmlElement.getAttributes().getNamedItem("identifier").getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapElement;
    }

    public static Document getDOM() {

        Document doc = null;
        try {
            String OR_FILE = ".\\src\\resources\\OR_RB.xml";

            File fXmlFile = new File(OR_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }
    public static By getElement(String strElementName) {

        By by = null;
        Map<String, String> objElementProperties = Page_Object_Reader.getElementProperties(strElementName);

        if (objElementProperties != null) {

            String strBy = objElementProperties.get("by");
            String strIdentifier = objElementProperties.get("identifier");

            if (strBy.equalsIgnoreCase("css")) {
                by = By.cssSelector(strIdentifier);
            } else if (strBy.equalsIgnoreCase("xpath")) {
                by = By.xpath(strIdentifier);
            } else if (strBy.equalsIgnoreCase("name")) {
                by = By.name(strIdentifier);
            } else if (strBy.equalsIgnoreCase("linktext")) {
                by = By.linkText(strIdentifier);
            } else if (strBy.equalsIgnoreCase("id")) {
                by = By.id(strIdentifier);
            } else if (strBy.equalsIgnoreCase("classname")) {
                by = By.className(strIdentifier);
            }else if (strBy.equalsIgnoreCase("tagName")) {
                by = By.tagName(strIdentifier);
            }

        }
        return by;
    }

}