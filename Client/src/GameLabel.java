import constants.GameColor;

import javax.swing.*;
import java.awt.*;

public class GameLabel extends JLabel {
    public GameLabel(String title){
        super(title);
        setForeground(GameColor.LABEL_FOREGROUND);
    }
}
