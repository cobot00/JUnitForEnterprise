package junit.io;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * xmlファイルを読み込み、Map形式に変換して返します。<br>
 * 
 * @author cobot00
 * 
 */
public class XMLReader {

    private String xmluri;

    public XMLReader(final String aXMLURI) {
        xmluri = aXMLURI;
    }

    private String getXMLURI() {
        return xmluri;
    }

    /**
     * xmlファイルを読み込み、Map<String, String>に変換して返します。
     * 
     * @return key、valueともStringのMap
     */
    public Map<String, String> parse() {
        final Map<String, String> result = new HashMap<String, String>();
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            factory.setIgnoringElementContentWhitespace(true);
            factory.setIgnoringComments(true);
            factory.setValidating(true);

            // 指定されたxmlファイルが存在するかの判定
            final File xml = new File(getXMLURI());
            if (!xml.exists()) {
                throw new FileNotFoundException();
            }

            final Document doc = builder.parse(xml);

            for (int i = 0; i < doc.getChildNodes().getLength(); i++) {
                final Element root = (Element)doc.getChildNodes().item(i);

                for (int j = 0; j < root.getChildNodes().getLength(); j++) {
                    final Node node = root.getChildNodes().item(j);
                    if (node.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    result.put(node.getNodeName(), node.getFirstChild().getNodeValue());
                }
            }

        } catch (ParserConfigurationException e0) {
            System.out.println(e0.getMessage());
        } catch (SAXException e1) {
            System.out.println(e1.getMessage());
        } catch (FileNotFoundException e2) {
            System.out.println(e2.getMessage());
        } catch (IOException e3) {
            System.out.println(e3.getMessage());
        }

        return result;
    }
}
