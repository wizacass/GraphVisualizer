package graph_visualizer.graphics;

import javax.swing.*;

class PanelButton extends JButton
{
    PanelButton(String title, int x, int y)
    {
        super(title);

        var constants = new WindowConstants();
        this.setBounds(x, y, constants.ButtonWidth(), constants.ButtonHeight());
    }
}
