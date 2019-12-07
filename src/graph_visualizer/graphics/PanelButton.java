package graph_visualizer.graphics;

import javax.swing.*;
import java.awt.*;

class PanelButton extends JButton
{
    public PanelButton(String title, int x, int y)
    {
        super(title);

        var constants = new WindowConstants();
        this.setBounds(x, y, constants.ButtonWidth(), constants.ButtonHeight());
    }
}
