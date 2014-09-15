
import java.rmi.*;
import java.io.*;

public class ChatClientMain
{
    public static void main(String[] args)
    {
        if(args.length != 2)
        {
            System.out.println("Syntax: <Server> <Name>");
            return;
        }
        try
        {
            ChatServer server = (ChatServer) Naming.lookup("rmi://" + args[0]
                                                           + "/ChatServer");
            ChatClientImpl client = new ChatClientImpl(args[1]);
            if(server.addClient(client))
            {
                System.out.println("Give Input ('Ende' oder 'ende' to stop");
                sendInputToServer(server, args[1]);
                server.removeClient(client);
            }
            else
            {
                System.out.println("Participant with this name exists");
            }
        }
        catch(Exception e) {}
        System.exit(0);
    }

    private static void sendInputToServer(ChatServer server, String name)
    {
        try
        {
            BufferedReader input = new BufferedReader(
                                       new InputStreamReader(System.in));
            String line;
            while((line = input.readLine()) != null)
            {
                if(line.equals("ende") || line.equals("Ende"))
                {
                    break;
                }
                server.sendMessage(name, line);
            }
        }
        catch(Exception e) {}
    }
}