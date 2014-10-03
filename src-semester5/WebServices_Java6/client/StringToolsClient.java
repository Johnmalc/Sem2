package client;

import client.StringTools;
import client.StringToolsService;

public class StringToolsClient {

    public static void main(String... args) {
        StringToolsService service = new StringToolsService();
        StringTools stringTools = service.getStringToolsPort();
        String testString = "Probe";
        String reversed = stringTools.reverse(args[0]);
        System.out.println("String " + args[0] + " reversed is " + reversed);
    }
}