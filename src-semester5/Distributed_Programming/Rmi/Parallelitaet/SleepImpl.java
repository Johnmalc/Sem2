import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SleepImpl extends UnicastRemoteObject implements Sleep {
    public SleepImpl() throws RemoteException {
    }

    public void sleep(int secs) throws RemoteException {
        System.out.println("Start of sleep(" + secs + ")");
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
        }
        System.out.println("End of sleep(" + secs + ")");
    }
}