
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * SAX-Beispiel
 */
public class SAX_Example2
{

	private static long number_elements;
	private static long number_attributes;
	private static long number_characters;

	/**
	 * Parst eine Datei mit SAX.
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		// SAXParserFactory anlegen und konfigurieren
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		spf.setValidating(true);
		
		// SAXParser generieren
		SAXParser parser = spf.newSAXParser();
		
		// Eingabedokument und Handler vorbereiten
		File input = new File(args[0]);
		System.out.println("XML File: " + input);
		DefaultHandler handler = new MyHandler();
		
		// Parse-Vorgang durchführen
		parser.parse(input, handler);
	}
	
	private static class MyHandler extends DefaultHandler
	{

	    /* DocumentHandler Methoden */

	    public void startDocument() {

	        number_elements   = 0;
        	number_attributes = 0;
        	number_characters = 0;
	    }

	    public void startElement(String uri, String name, String qName, Attributes attrs) {

	        number_elements++;
        	if (attrs != null) {
            		number_attributes += attrs.getLength();
	        }
    	    }

	    public void characters(char ch[], int start, int length) {

	        number_characters += length;
	    }

	    public void endDocument() {

		System.out.println("\nResults:");
		System.out.println("  Zahl der Elemente: " + number_elements);
		System.out.println("  Zahl der Attribute: " + number_attributes);
		System.out.println("  Zahl der Characters: " + number_characters);

	    }


	    /* ErrorHandler Methoden */

	    public void warning(SAXException ex) {
        	System.err.println("[Warning]");
	    }

	    public void error(SAXException ex) {
    		System.err.println("[Error]");
	    }

	    public void fatalError(SAXException ex) throws SAXException {
        	System.err.println("[Fatal Error]");
        	throw ex;
	    }

	}
}
