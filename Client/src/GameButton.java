import constants.GameColor;

import javax.swing.*;

public class GameButton extends JButton {
    public GameButton(String title){
        super(title);
        setBackground(GameColor.BUTTON_BACKGROUND);
        setForeground(GameColor.BUTTON_FOREGROUND);
    }
}

