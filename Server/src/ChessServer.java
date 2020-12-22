import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.rmi.registry.*;


public class ChessServer {

    private static final long serialVersionUID = 1L;

    private Toolkit tk = Toolkit.getDefaultToolkit();
    private static ChessServer server;

    private static int registryPort1= Integer.parseInt(System.getenv(Constants.REGISTRY_PORT_1));
    private static int registryPort2= Integer.parseInt(System.getenv(Constants.REGISTRY_PORT_2));
    private static int registryPort3= Integer.parseInt(System.getenv(Constants.REGISTRY_PORT_3));

    public static void main(String[] args) {
        server = new ChessServer();
        if (hostGames()) {
            System.out.println("...Hosting successful!");
        } else {
            System.out.println("...Hosting Failed!");
        }
    }

    public ChessServer() {
        System.out.println("JavaChess Server");

        GridBagConstraints c = new GridBagConstraints();

        System.out.println("Server starting up");


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 15;

        c.gridx = 0;
        c.gridy = 1;

        c.gridx = 0;
        c.gridy = 2;


        c.gridx = 0;
        c.gridy = 3;

        c.gridx = 0;
        c.gridy = 4;

    }

    public static boolean hostGames() {
        boolean success = false;
        try {

            System.setProperty("java.rmi.server.hostname", System.getenv(Constants.SERVER_IP));
            //create GameSession objects to host
            GameSession sesh1 = new GameSession();
            GameSession sesh2 = new GameSession();
            GameSession sesh3 = new GameSession();

            //Create 3 registry objects for each session
            Registry registry1;
            Registry registry2;
            Registry registry3;
            //registry = LocateRegistry.getRegistry();

            //Start up the registries
            registry1 = LocateRegistry.createRegistry(registryPort1);
            System.out.println("registry1 created at port: "+registryPort1);
            registry2 = LocateRegistry.createRegistry(registryPort2);
            System.out.println("registry2 created at port: "+registryPort2);
            registry3 = LocateRegistry.createRegistry(registryPort3);
            System.out.println("registry3 created at port: "+registryPort3);

            //Bind each Session with a registry and give it a name
            registry1.rebind(Constants.SESSION_ONE, sesh1);
            System.out.println("Sesh1 up and running");
            registry2.rebind(Constants.SESSION_TWO, sesh2);
            System.out.println("Sesh2 up and running");
            registry3.rebind(Constants.SESSION_THREE, sesh3);
            System.out.println("Sesh3 up and running");

            /*os.writeBytes("ok" + "\n");
            is.close();
            os.close();

            String clientHost = clientSocket.getInetAddress().getHostAddress();
            System.out.println(clientHost);*/


            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}