import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SDS_Server extends Remote {

    public Reply investigate(String suchname) throws RemoteException;

} // SDS_Server
