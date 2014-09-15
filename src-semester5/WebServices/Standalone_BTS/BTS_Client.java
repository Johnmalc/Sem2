// BTS_Client.java
// BTS_Client invokes the (directly deployed) BTS Book Title Web service,
// which retrieves titles of Deitel books.

// Apache core packages
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

// Java core packages
import java.net.URL;

// Java extension packages
import javax.xml.rpc.ParameterMode;
import javax.xml.namespace.QName;

public class BTS_Client {

   // define ISBNs for books titles to retrieve
   public static String[] isbn = new String[] { "0130895601", 
      "0130895717", "0130293636", "0130284173", "0130923613" };
   
   // class entry-point
   public static void main( String[] args ) 
   {
      // interact with Book Title Web service
      try {
         
         // define endpoint for Book Title Web service
         String endPoint = "http://ammann2:8080/axis/BTS.jws";
            
         // create service instance
         Service service = new Service();
         
         // create call object from service
         Call call = ( Call )service.createCall();
         
         // set call target endpoint
         call.setTargetEndpointAddress( new URL( endPoint ) );
    
         // set name of operation
         QName operationName = new QName( 
            "axis:BookTitle:BookTitleService", 
            "getBookTitle" );

         call.setOperationName( operationName );
         
         // set return type for call
         call.setReturnType( XMLType.XSD_STRING );

         // set the SOAPAction header value
         call.setSOAPActionURI( 
            "axis:BookTitle:" +
            "BookTitleService#getBookTitle" );

         // set parameter name, type and mode
         call.addParameter( "ISBN",
            XMLType.XSD_STRING, ParameterMode.IN );
         
         // invoke Web service and display results
         for ( int i = 0; i < isbn.length; i++ ) {
            
            // parameter list
            Object[] parameterList = new Object[ 1 ];
            parameterList[ 0 ] = isbn[ i ];

            String response = 
               ( String ) call.invoke( parameterList );
            
            System.out.println( "ISBN #" + isbn[ i ] + 
               ", Title: " + response );

         } // end for block
         
      } // end try block
      
      catch( Exception exception ) {
         exception.printStackTrace();
      }
      
   } // end method main

} // end class BTS_Client

