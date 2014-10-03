import java.rmi.Naming;

public class Server_S {
    public static void main(String[] args) {
        try {
            SleepImpl_S server;
            server = new SleepImpl_S();
            Naming.rebind("SleepServer_S", server);
        } catch (Exception e) {
        }
    }
}