
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

import net.jini.lease.LeaseRenewalManager;
import net.jini.lookup.*;
import net.jini.core.lookup.*;
import net.jini.discovery.*;

public class SDS_ServerImpl extends UnicastRemoteObject 
                            implements SDS_Server, ServiceIDListener
{
   public SDS_ServerImpl() throws RemoteException {}

   public void serviceIDNotify(ServiceID id) {   
      System.out.println("Service-ID: "+id);    
   }

   public Reply investigate(String suchname) {

      int result = -1;
      SDS sds;
      FileInputStream fis = null;
      ObjectInputStream ois = null;
      Reply reply = null;

      System.out.println("Methode investigate aufgerufen, Parameter: " + suchname);
      String filename = "kundendatei.txt";
      try {
         fis = new FileInputStream(filename);
         ois = new ObjectInputStream(fis);
         while(true) {
            sds = (SDS)ois.readObject();
            System.out.println("Ausgelesener Name: " + sds.name);
            if ((sds.name).equals(suchname)) {
               ois.close(); fis.close();
               System.out.println("Name " + suchname + " gefunden");
               reply = new Reply(Reply.FOUND, sds.name, sds.vorname,
                                 sds.personalnummer, sds.wohnort);
               break;
            } 
         };
      } catch(Exception e) {
         System.out.println("Suchname " + suchname + " nicht gefunden");
         reply = new Reply();
      }            

      System.out.println();
      return reply;
   }

   public static void main(String args[])
   {
      String[] groups = {"SDS_app"};

      try {
         System.setSecurityManager(new RMISecurityManager());
         SDS_ServerImpl server = new SDS_ServerImpl();
         LookupDiscoveryManager ldm = new LookupDiscoveryManager(groups, null, null);
         JoinManager manager = new JoinManager(server, null, server, ldm,                             
                                               new LeaseRenewalManager());
         System.out.println("SDS_Server erwartet Anfragen");
      } catch (Exception e) { e.printStackTrace(); }
   }

}  // SDS_ServerImpl
