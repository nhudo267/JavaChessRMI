import constants.TextConstants;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

public class SessionFrame extends GameFrame {
    private static final long serialVersionUID = 1L; //necessary thing is necessary
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private JPanel pan;
    public JLabel labLogo;
    public JLabel labInTro;
    public JLabel game1, game2, game3, gameOc1, gameOc2, gameOc3;
    public static JButton game1JoinButton, game2JoinButton, game3JoinButton,
            openClosedButton1, openClosedButton2, openClosedButton3;
    ImageIcon openIcon = new ImageIcon(getClass().getResource("res/open.png"));
    ImageIcon closedIcon = new ImageIcon(getClass().getResource("res/closed.png"));
    ImageIcon hustIcon=new ImageIcon(getClass().getResource("res/hust.png"));

    public SessionFrame() {
        //set size of window
        this.setSize(700, 550);

        //use awt.Toolkit to get screensize and set xPos and yPos to the center of the screen
        Dimension dim = tk.getScreenSize();
        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);
        //set location to center defined by xPos and yPos
        this.setLocation(xPos, yPos);

        //turn off resizability, set close operation, turn off decoration, and set title of window
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TextConstants.ONLINE_ROOM);

        labLogo = new JLabel();

        Image image=hustIcon.getImage();
        Image newimg = image.getScaledInstance(60, 90,  java.awt.Image.SCALE_SMOOTH);
        hustIcon = new ImageIcon(newimg);
        labLogo.setIcon(hustIcon);

        labInTro = new JLabel("<html><font color='red'>LTU13B - Group 10</font></html>");
        labInTro.setFont(new Font("Dialog", Font.BOLD, 34));

        game1 = new GameLabel(TextConstants.ROOM_1);
        game2 = new GameLabel(TextConstants.ROOM_2);
        game3 = new GameLabel(TextConstants.ROOM_3);
        gameOc1 = new GameLabel(TextConstants.CURRENT_PLAYERS + ": 0");
        gameOc2 = new GameLabel(TextConstants.CURRENT_PLAYERS + ": 0");
        gameOc3 = new GameLabel(TextConstants.CURRENT_PLAYERS + ": 0");

        game1.setFont(new Font("Dialog", Font.BOLD, 17));
        game2.setFont(new Font("Dialog", Font.BOLD, 17));
        game3.setFont(new Font("Dialog", Font.BOLD, 17));
        gameOc1.setFont(new Font("Dialog", Font.BOLD, 17));
        gameOc2.setFont(new Font("Dialog", Font.BOLD, 17));
        gameOc3.setFont(new Font("Dialog", Font.BOLD, 17));

        openClosedButton1 = new JButton();
        openClosedButton2 = new JButton();
        openClosedButton3 = new JButton();

        openClosedButton1.setIcon(openIcon);
        openClosedButton1.setBorderPainted(false);
        openClosedButton1.setContentAreaFilled(false);

        openClosedButton2.setIcon(openIcon);
        openClosedButton2.setBorderPainted(false);
        openClosedButton2.setContentAreaFilled(false);

        openClosedButton3.setIcon(openIcon);
        openClosedButton3.setBorderPainted(false);
        openClosedButton3.setContentAreaFilled(false);

        game1JoinButton = new GameButton(TextConstants.JOIN_ROOM);
        game2JoinButton = new GameButton(TextConstants.JOIN_ROOM);
        game3JoinButton = new GameButton(TextConstants.JOIN_ROOM);


        game1JoinButton.addActionListener(new ButtonListener());
        game2JoinButton.addActionListener(new ButtonListener());
        game3JoinButton.addActionListener(new ButtonListener());

        //make pan have a GridBagLayout
        pan = new BackgroundPanel();
        pan.setLayout(new GridBagLayout());
        //create variable c for constraints
        GridBagConstraints c = new GridBagConstraints();


        //Add each component to panel with different x and y positions
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 40;
        pan.add(labLogo, c);

        c.gridx = 2;
        pan.add(labInTro, c);

        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        pan.add(game1, c);

        c.gridx = 1;
        c.gridy = 2;
        pan.add(game2, c);

        c.gridx = 1;
        c.gridy = 3;
        pan.add(game3, c);

        c.gridx = 2;
        c.gridy = 1;
        pan.add(gameOc1, c);

        c.gridx = 2;
        c.gridy = 2;
        pan.add(gameOc2, c);

        c.gridx = 2;
        c.gridy = 3;
        pan.add(gameOc3, c);

        c.gridx = 3;
        c.gridy = 1;
        pan.add(openClosedButton1, c);

        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 2;
        pan.add(openClosedButton2, c);

        c.gridx = 3;
        c.gridy = 3;
        pan.add(openClosedButton3, c);

        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 4;
        c.gridy = 1;
        pan.add(game1JoinButton, c);

        c.gridx = 4;
        c.gridy = 2;
        pan.add(game2JoinButton, c);

        c.gridx = 4;
        c.gridy = 3;
        pan.add(game3JoinButton, c);

        this.add(pan);
    }

    public void refresh() throws RemoteException {
        if (ChessClient.getOcFromGame(0) == 0) {
            gameOc1.setText(TextConstants.CURRENT_PLAYERS + ": 0");
            game1JoinButton.setEnabled(true);
            openClosedButton1.setIcon(openIcon);
        }
        if (ChessClient.getOcFromGame(0) == 1) {
            gameOc1.setText(TextConstants.CURRENT_PLAYERS + ": 1");
            game1JoinButton.setEnabled(true);
            openClosedButton1.setIcon(openIcon);
        }
        if (ChessClient.getOcFromGame(0) == 2) {
            gameOc1.setText(TextConstants.CURRENT_PLAYERS + ": 2");
            game1JoinButton.setEnabled(false);
            openClosedButton1.setIcon(closedIcon);
        }
        if (ChessClient.getOcFromGame(1) == 0) {
            gameOc2.setText(TextConstants.CURRENT_PLAYERS + ": 0");
            game2JoinButton.setEnabled(true);
            openClosedButton2.setIcon(openIcon);
        }
        if (ChessClient.getOcFromGame(1) == 1) {
            gameOc2.setText(TextConstants.CURRENT_PLAYERS + ": 1");
            game2JoinButton.setEnabled(true);
            openClosedButton2.setIcon(openIcon);
        }
        if (ChessClient.getOcFromGame(1) == 2) {
            gameOc2.setText(TextConstants.CURRENT_PLAYERS + ": 2");
            game2JoinButton.setEnabled(false);
            openClosedButton2.setIcon(closedIcon);
        }
        if (ChessClient.getOcFromGame(2) == 0) {
            gameOc3.setText(TextConstants.CURRENT_PLAYERS + ": 0");
            game3JoinButton.setEnabled(true);
            openClosedButton3.setIcon(openIcon);
        }
        if (ChessClient.getOcFromGame(2) == 1) {
            gameOc3.setText(TextConstants.CURRENT_PLAYERS + ": 1");
            game3JoinButton.setEnabled(true);
            openClosedButton3.setIcon(openIcon);
        }
        if (ChessClient.getOcFromGame(2) == 2) {
            gameOc3.setText(TextConstants.CURRENT_PLAYERS + ": 2");
            game3JoinButton.setEnabled(false);
            openClosedButton3.setIcon(closedIcon);
        }
    }
}
