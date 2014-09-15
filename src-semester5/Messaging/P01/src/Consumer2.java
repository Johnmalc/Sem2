import javax.jms.*;
import javax.naming.*;

public class Consumer2 implements MessageListener {
  private static final String DESTINATION = "queue/myQueue1";
  private static final String USER = "guest";
  private static final String PASSWORD = "guest";
  
  private QueueConnection connection;
  private QueueSession session;
  private QueueReceiver receiver;

  public Consumer2() throws NamingException, JMSException {
    // JNDI-Kontext erzeugen
    Context ctx = new InitialContext();
    
    // ConnectionFactory über Namensdienst auslesen
    QueueConnectionFactory factory =
      (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
      
    // Zieladresse über Namensdienst auslesen
    Queue queue = (Queue) ctx.lookup(DESTINATION);
    
    // Verbindung aufbauen
    connection = factory.createQueueConnection(USER, PASSWORD);
    
    // Session erzeugen
    session = connection.createQueueSession(
      false, Session.AUTO_ACKNOWLEDGE);
      
    // Empfänger erzeugen
    receiver = session.createReceiver(queue);
    
    // MessageListener setzen
    receiver.setMessageListener(this);
    
    // Empfang von Nachrichten starten
    connection.start();
  }
  
  // Nachrichten werden im Push-Verfahren empfangen
  public void onMessage(Message message) {
    try {
      if (message instanceof TextMessage) {
        TextMessage textMessage = (TextMessage) message;
        System.out.println(textMessage.getText());
      }
    }
    catch (JMSException e) {
      System.err.println(e);
    }
  }
  
  // Ressourcen freigeben
  public void close() throws JMSException {
    receiver.close();
    session.close();
    connection.close();
  }
  
  public static void main(String[] args) throws Exception {
    long timeout = Long.parseLong(args[0]);
    Consumer2 consumer = new Consumer2();
    Thread.sleep(timeout);
    consumer.close();
  }
}
