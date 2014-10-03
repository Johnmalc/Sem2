import java.rmi.Naming;

public class RMISem_Server {
    public static void main(String args[]) {
        try {
            RMISemaphore rmi_sem = new RMISemaphoreImpl(1);
            Naming.rebind("RMISemaphore", rmi_sem);
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}