
package bookproxy;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.JAXRPCException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceFactory;
import bookproxy.BookIF;


public class BookClient {
    public static void main(String[] args) {
        try {
            String UrlString = args[0] + "?WSDL";
            String nameSpaceUri = "urn:Foo";
            String serviceName = "MyBookService";
            String portName = "BookIFPort";

            System.out.println("UrlString = " + UrlString);

            URL bookWsdlUrl = new URL(UrlString);

            ServiceFactory serviceFactory = ServiceFactory.newInstance();

            Service bookService =
                serviceFactory.createService(bookWsdlUrl,
                    new QName(nameSpaceUri, serviceName));

            bookproxy.BookIF myProxy =
                (bookproxy.BookIF) bookService.getPort(new QName(
                        nameSpaceUri,
                        portName), bookproxy.BookIF.class);

            System.out.println(myProxy.getBookTitle("0130293636"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

