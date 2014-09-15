import java.rmi.*;

import net.jini.core.entry.*;
import net.jini.lookup.entry.*;
import net.jini.lookup.*;
import net.jini.core.lookup.*;
import net.jini.core.discovery.*;
import net.jini.discovery.*;

public class Client implements DiscoveryListener 
{
  private SDS_Server sds_server;
  private ServiceRegistrar[] registrars = null;

  public void discovered(DiscoveryEvent e) {
    registrars = e.getRegistrars();
  }

  public void discarded(DiscoveryEvent e) {
    registrars = e.getRegistrars();
  }

  private void outputReply(Reply reply) {
    if (reply.status == Reply.FOUND) {
        System.out.println("\nSuchname gefunden");
        System.out.println("   Name: " + reply.name);
        System.out.println("   Vorname: " + reply.vorname);
        System.out.println("   Personalnummer: " + reply.personalnummer);
        System.out.println("   Wohnort: " + reply.wohnort);
      } else
        System.out.println("\nSuchname nicht gefunden");
  } 

  public Client(String[] argumente) {
    Reply reply = null; 
    String [] groups = {"SDS_app"};

    try {
     System.setSecurityManager(new RMISecurityManager());   
     
     LookupDiscovery discovery = new LookupDiscovery(groups);
     discovery.addDiscoveryListener(this);

     while ( registrars == null ) {
       try {
         Thread.currentThread().sleep(100);
       } catch (Exception e) { e.printStackTrace(); }
     }

     Class[] classes = new Class[] { SDS_Server.class };
     ServiceTemplate template = new ServiceTemplate(null, classes, null);

     sds_server = (SDS_Server) registrars[0].lookup(template);

     reply = sds_server.investigate(argumente[0]);

    } catch (Exception ex) { ex.printStackTrace(); }

    outputReply(reply);
  }

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Syntax: java Client <suchname>");
      System.exit(1);
    }

    Client client = new Client(args);
  } 

} // Client 