import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CounterImpl extends UnicastRemoteObject
        implements Counter {
    private int counter;

    public CounterImpl() throws RemoteException {
        super();
        counter = 0;
    }

    public synchronized int reset() throws RemoteException {
        System.out.println("Method reset() called ");
        counter = 0;
        return counter;
    }

    public synchronized int increment() throws RemoteException {
        System.out.println("Method increment() called ");
        counter++;
        return counter;
    }
}