import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISemaphoreImpl extends UnicastRemoteObject
        implements RMISemaphore {
    private int value;

    public RMISemaphoreImpl(int init) throws RemoteException {
        if (init < 0)
            init = 0;
        value = init;
    }

    public synchronized void p() throws RemoteException {
        System.out.println("p-method called");
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        value--;
    }

    public synchronized void v() throws RemoteException {
        System.out.println("v-method called");
        value++;
        notify();
    }
}