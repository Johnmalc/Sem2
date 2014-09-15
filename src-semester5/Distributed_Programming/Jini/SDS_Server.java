
import java.rmi.*;

public interface SDS_Server extends Remote 
{

  public Reply investigate(String suchname) throws RemoteException;

} // SDS_Server
