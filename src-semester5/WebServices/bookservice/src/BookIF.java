
package bookservice;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BookIF extends Remote {
    public String getBookTitle(String s) throws RemoteException;
}

