package com.parser.XMLparser;

import com.parser.Model.Xml;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class XMLParserRss {
    public List<Xml> getlist() {
        List<Xml> listXml = new ArrayList<>();
        try {
//creating a constructor of file class and parsing an XML file
            File file = new File("/home/onkars/Desktop/parser/04_parser_test_local_file_pojo/parser/src/main/java/com/parser/File/rss.xml");
//an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("item");
// nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String link = eElement.getElementsByTagName("link").item(0).getTextContent();
                    String pubDate = eElement.getElementsByTagName("pubDate").item(0).getTextContent();
                    String category = eElement.getElementsByTagName("category").item(0).getTextContent();
                    String guid = eElement.getElementsByTagName("guid").item(0).getTextContent();
                    String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    Xml xml = new Xml(title,link,pubDate,category,guid,description);
                    listXml.add(xml);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listXml;
    }
}
