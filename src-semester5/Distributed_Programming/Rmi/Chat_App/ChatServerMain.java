import java.rmi.Naming;

public class ChatServerMain {
    public static void main(String[] args) {
        try {
            ChatServerImpl server = new ChatServerImpl();
            Naming.rebind("rmi://localhost/ChatServer", server);
        } catch (Exception e) {
        }
    }
}