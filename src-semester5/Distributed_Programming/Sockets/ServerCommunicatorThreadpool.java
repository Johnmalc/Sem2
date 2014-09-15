
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ServerCommunicatorThreadpool {

  private final static int PORT = 7825; 

  private static ServerSocket serverSocket;
  private static Server server;

  private static Socket incoming;

  private static ExecutorService pool;

  static class RunTask implements Runnable {
    private Socket myconnection;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public RunTask (Socket connection) {
      myconnection = connection;
      try {
        out = new ObjectOutputStream(myconnection.getOutputStream());
        in = new ObjectInputStream(myconnection.getInputStream());
      }  
      catch (Exception e) { e.printStackTrace(); }
    } 

    public void run() {
      try {
          String suchname = (String) in.readObject();
          Reply reply = server.investigate(suchname);
          out.writeObject(reply);  

          out.flush();
          myconnection.close();
      }
      catch (Exception e) { e.printStackTrace(); }  
    }
  }
  
  public static void main (String args[])
  {
    try {
      serverSocket = new ServerSocket(PORT);
      pool = Executors.newFixedThreadPool(10);

      System.out.println("Threadpool generated, Server waiting for clients...");
      
      server = new Server();

      while (true) {
        incoming = serverSocket.accept();
        Runnable task = new RunTask(incoming);
        pool.execute(task);
      }

    } catch ( IOException e) { pool.shutdown(); } 
  } 

} // ServerCommunicatorThreadpool