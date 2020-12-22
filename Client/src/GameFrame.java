import constants.GameImage;

import javax.swing.*;

public class GameFrame extends JFrame {
    ImageIcon img = new ImageIcon(getClass().getResource("res/hust.png"));

    public GameFrame() {
        setIconImage(GameImage.ICON_HUST.getImage());
    }
}
