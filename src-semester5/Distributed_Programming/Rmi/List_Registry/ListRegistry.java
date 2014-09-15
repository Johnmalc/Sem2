import java.rmi.*;

public class ListRegistry {
  public static void main(String args[]) throws Exception {
    String registryName = args[0];

    String list[] = Naming.list(registryName);
    for (String name : list) {
      System.out.println(name);
    }
  }
}
