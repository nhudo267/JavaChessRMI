import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image img;

    public BackgroundPanel() {
        super();
        img = new ImageIcon(getClass().getResource("res/wood.jpg")).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
}
