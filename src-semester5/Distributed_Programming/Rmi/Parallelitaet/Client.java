public class Client {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Syntax: "
                    + "<Server> <Object Name> "
                    + "<Seconds>");
            return;
        }

        try {
            Sleep server = (Sleep) Naming.lookup("rmi://" + args[0] + "/"
                    + args[1]);
            int secs = Integer.parseInt(args[2]);
            server.sleep(secs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}