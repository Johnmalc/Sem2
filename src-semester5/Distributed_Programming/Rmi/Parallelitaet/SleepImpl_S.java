
import java.rmi.*;
import java.rmi.server.*;

public class SleepImpl_S extends UnicastRemoteObject implements Sleep
{
    public SleepImpl_S() throws RemoteException
    {
    }

    public synchronized void sleep(int secs) throws RemoteException
    {
        System.out.println("Start of sleep(" + secs + ")");
        try
        {
            Thread.sleep(secs * 1000);
        }
        catch(InterruptedException e) {}
        System.out.println("End of sleep(" + secs + ")");
    }
}