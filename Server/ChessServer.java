import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.rmi.registry.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessServer extends JFrame 
{
  private static final long serialVersionUID = 1L;
	
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private JPanel pan;
	private JLabel lab1, lab2, lab3, lab4, lab5;
	private static ChessServer server;
	
	public static void main(String[] args) 
	{
		server = new ChessServer();
		if(hostGames())
		{
			server.lab5.setText(server.lab5.getText() + "\n...Hosting successful!");
		}
		else
		{
			server.lab5.setText(server.lab5.getText() + "\n...Hosting Failed!");
		}
	}
    
		boolean success = false;
		try {
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
			registry1 = LocateRegistry.createRegistry(4200); 
			System.out.println("registry1 created at port 4200");
			registry2 = LocateRegistry.createRegistry(4201);
			System.out.println("registry2 created at port 4201");
			registry3 = LocateRegistry.createRegistry(4202);
			System.out.println("registry2 created at port 4202");

			//Bind each Session with a registry and give it a name
			registry1.rebind("Session1", sesh1);
			server.lab2.setText("Sesh1 up and running");
			registry2.rebind("Session2", sesh2);
			server.lab3.setText("Sesh2 up and running");
			registry3.rebind("Session3", sesh3);
			server.lab4.setText("Sesh3 up and running");
			success = true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return success;
	}
}