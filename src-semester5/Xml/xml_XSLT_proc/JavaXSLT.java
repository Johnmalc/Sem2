import java.io.*;
import java.awt.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

// Import-Klassen für das Fenster
import java.awt.event.*;
import javax.swing.*;

public class JavaXSLT 
{
   static Document XMLdocument;
   static Document XSLTdocument;

   Anzeigefenster anzeigefenster = new Anzeigefenster(500,200,100,600,"Meldungen und Fehlermeldungen");

   public static void main(String param[])
   {

       if (param.length!=3)
       {
           System.out.println("Syntax: JavaXSLT <XML-Datei> <XSL-Datei> <Ziel-HTML-Datei>");
           System.exit(1);
       }
       JavaXSLT javaXSLT= new JavaXSLT(param);
   }

   public JavaXSLT(String param[])
   {
       transforming(param);
   }

   public void transforming(String param[])
   {
       File XMLdatei=new File(param[0]);
       boolean XMLladen=XMLdatei.canRead();
       File XSLTdatei=new File(param[1]);
       boolean XSLTladen=XSLTdatei.canRead();

       if (XMLladen==true&&XSLTladen==true)
       {
          try {
             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
             DocumentBuilder builder = factory.newDocumentBuilder();
             XMLdocument = builder.parse(XMLdatei);
             XSLTdocument=builder.parse(XSLTdatei);

             anzeigefenster.ausgabe("\nDas Formen startet");

             TransformerFactory tfactory = TransformerFactory.newInstance();
             StreamSource xsltsource = new StreamSource(XSLTdatei);
             StreamSource xmlsource = new StreamSource(XMLdatei);

             Transformer transformer = tfactory.newTransformer(xsltsource);


             FileWriter Ausgabestrom= new FileWriter(param[2]);
             BufferedWriter output = new BufferedWriter(Ausgabestrom);

             StreamResult result = new StreamResult(output);
             transformer.transform(xmlsource, result);
             String out=result.toString();
             output.write(out,0,out.indexOf("javax"));
             output.close();

             anzeigefenster.ausgabe("\nFormen beendet");
          }
          catch (SAXParseException error) {  
             anzeigefenster.ausgabe("\n+++Parse Error+++"+ "\nZeile: " + error.getLineNumber() + "\nDatei: " + error.getSystemId());
             anzeigefenster.ausgabe("\n" + error.getMessage() );
          }
          catch (ParserConfigurationException pce) {
             pce.printStackTrace();
          }
          catch (IOException e) {
             // I/O error
             anzeigefenster.ausgabe("IO-Fehler:\n"+e);
          }
          catch (Throwable t) {
             t.printStackTrace();
          }
        }
       else
        {
          anzeigefenster.ausgabe("Datei existiert nicht");
        }
   }

}


// Klasse für das Anzeigefenster
class Anzeigefenster extends JFrame implements WindowListener
{

   JTextArea textbereich;

   public Anzeigefenster(int breite, int hoehe, int posx, int posy, String name)
   {
       setSize (breite,hoehe);
       setLocation (posx,posy);
       setTitle (name);
       textbereich=new JTextArea();
       Container content=getContentPane();
       content.add (textbereich);
       addWindowListener(this);
       setVisible(true);
   }

   public void ausgabe(String s)
   {
      textbereich.append(s);
   }

   // Ereignisse des WindowListener
   public void windowClosing(WindowEvent evt) 
   {
      dispose();
      System.exit(0);
   }

   public void windowOpened(WindowEvent evt) {   }
   public void windowIconified(WindowEvent evt)  {  }
   public void windowDeiconified(WindowEvent evt)   { }
   public void windowClosed(WindowEvent evt)  { }
   public void windowActivated(WindowEvent evt)  {  }
   public void windowDeactivated(WindowEvent evt) { }

}
