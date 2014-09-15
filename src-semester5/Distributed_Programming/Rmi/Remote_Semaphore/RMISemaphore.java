
import java.rmi.*;

public interface RMISemaphore extends Remote
{
    public void p() throws RemoteException;
    public void v() throws RemoteException;
}