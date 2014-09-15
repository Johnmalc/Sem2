
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX-Beispiel
 */
public class SAX_Example
{
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
		DefaultHandler handler = new MyHandler();
		
		// Parse-Vorgang durchführen
		parser.parse(input, handler);
	}
	
	private static class MyHandler extends DefaultHandler
	{
		public void characters(char[] ch, int start, int length) throws SAXException
		{
			// String aus den geparsten Characters erzeugen und
			// Leerzeichen vorne und hinten wegkürzen
			String characters = new String(ch, start, length).trim();
			
			// Nicht-leere Strings auf Konsole ausgeben
			if(characters.length() > 0)
				System.out.println(characters);
		}
	}
}
