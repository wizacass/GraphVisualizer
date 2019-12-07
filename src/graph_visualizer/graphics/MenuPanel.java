package graph_visualizer.graphics;

import javax.swing.*;

class MenuPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();

    MenuPanel()
    {
        Initialize();
        AddButtons();
    }

    private void Initialize()
    {
        var height =  (int)(constants.WindowHeight() / 3);
        this.setBounds(
                0,
                constants.WindowHeight() - height,
                constants.WindowWidth(),
                height
        );
        this.setBackground(constants.MenuPanelColor());
    }

    private void AddButtons()
    {

    }
}
