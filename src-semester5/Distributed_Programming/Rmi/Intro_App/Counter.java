public interface Counter extends Remote {
    public int reset() throws RemoteException;

    public int increment() throws RemoteException;
}