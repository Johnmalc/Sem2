import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * DOM-Beispiel
 */
public class DOM_Example {
    private static long number_elements;
    private static long number_attributes;

    /* Parst eine Datei mit DOM */
    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {
        // DocumentBuilderFactory anlegen und konfigurieren
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setValidating(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringComments(true);

        // DOMParser generieren
        DocumentBuilder builder = dbf.newDocumentBuilder();

        // Eingabedokument und Handler vorbereiten
        File input = new File(args[0]);

        // Parse-Vorgang durchf�hren
        Document document = builder.parse(input);

        // Dokument traversieren und ausgeben
        start_traverse(document);

        // Statistik ausgeben
        System.out.println("Zahl der Elemente: " + number_elements);
        System.out.println("Zahl der Attribute: " + number_attributes);
    }

    private static void start_traverse(Document document) {
        number_elements = 0;
        number_attributes = 0;
        traverse_and_print(document.getDocumentElement(), "");
    }

    private static void traverse_and_print(Element element, String indent) {
        String nextIndent = indent + "  ";

        // Element
        System.out.print(indent);
        number_elements++;
        System.out.print("Element '");
        System.out.print(element.getNodeName());
        System.out.println("'");

        NamedNodeMap attributes = element.getAttributes();
        number_attributes += attributes.getLength();
        for (int i = 0; i < attributes.getLength(); ++i) {
            Node attribute = attributes.item(i);

            System.out.print(nextIndent);
            System.out.print("Attribut '");
            System.out.print(attribute.getNodeName());
            System.out.print("': '");
            System.out.print(attribute.getNodeValue());
            System.out.println("'");
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node currentChild = children.item(i);

            switch (currentChild.getNodeType()) {
                case Node.TEXT_NODE:
                    String trimmedValue = currentChild.getNodeValue().trim();
                    if (trimmedValue.length() == 0)
                        break;
                    System.out.print(nextIndent);
                    System.out.print("Text '");
                    System.out.print(currentChild.getNodeValue());
                    System.out.println("'");
                    break;
                case Node.ELEMENT_NODE:
                    traverse_and_print((Element) currentChild, nextIndent);
                    break;
                default:
                    break;
            }
        }
    }
}
