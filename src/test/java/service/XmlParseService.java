package service;

import domain.Number;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlParseService {

    public List<Number> parseXmlNumbers(File file) throws ParserConfigurationException, IOException, SAXException {
        final List<Number> numbers = new ArrayList<>();
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        final DocumentBuilder db = dbf.newDocumentBuilder();
        final Document document = db.parse(file);
        document.getDocumentElement().normalize();

        final NodeList nodeList = document.getElementsByTagName("number");
        for (int i = 0; i < nodeList.getLength(); i++) {
            final Node item = nodeList.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE && item.hasAttributes()) {
                final Element element = ((Element) item);
                final Number number = new Number();
                number.setId(element.getAttribute("id"));
                number.setPrefix(element.getElementsByTagName("prefix").item(0).getTextContent());
                number.setNumber(element.getElementsByTagName("number").item(0).getTextContent());
                number.setGenre(element.getElementsByTagName("genre").item(0).getTextContent());

                numbers.add(number);
            }
        }

        return numbers;
    }
}
