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

}