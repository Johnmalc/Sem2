import javax.jms.*;
import javax.naming.*;

public class Producer {
  private static final String DESTINATION = "queue/myQueue1";
  private static final String USER = "guest";
  private static final String PASSWORD = "guest";
  
  private QueueConnection connection;
  private QueueSession session;
  private QueueSender sender;
  private String text;
  private long expiration;
  
  public Producer(String text, long expiration) throws NamingException,
    JMSException {

    this.text = text;
    this.expiration = expiration;
    
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
    session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

    // Sender erzeugen
    sender = session.createSender(queue);
  }
  
  // Nachricht erzeugen und senden
  public void sendMessage() throws JMSException {
    TextMessage message = session.createTextMessage();
    message.setText(text);
    sender.setTimeToLive(expiration);
    sender.send(message);
  }
  
  // Ressourcen freigeben
  public void close() throws JMSException {
    sender.close();
    session.close();
    connection.close();
  }
  
  public static void main(String[] args) throws Exception {
    String text = args[0];
    long expiration = (args.length == 2) ? Long.parseLong(args[1]) : 0;
    Producer producer = new Producer(text, expiration);
    producer.sendMessage();
    producer.close();
  }
}
