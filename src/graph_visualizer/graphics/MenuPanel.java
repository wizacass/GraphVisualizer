package graph_visualizer.graphics;

import javax.swing.*;
import java.awt.*;

class MenuPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();

    MenuPanel()
    {
        Initialize();
        AddButtons();

        this.setLayout(null);
    }

    private void Initialize()
    {
        this.setBounds(
                0,
                constants.WindowHeight() - constants.PanelHeight(),
                constants.WindowWidth(),
                constants.PanelHeight()
        );
        this.setBackground(constants.MenuPanelColor());
    }

    private void AddButtons()
    {
        int y = constants.PanelHeight() - constants.Margin() - constants.ButtonHeight();

        var loadButton = new PanelButton("Load", GetButtonX(0), y);
        var generateButton = new PanelButton("Generate", GetButtonX(1), y);
        var clearButton = new PanelButton("Clear", GetButtonX(2), y);

        this.add(loadButton);
        this.add(generateButton);
        this.add(clearButton);
    }

    private int GetButtonX(int k)
    {
       return constants.Margin() * (k + 1) + constants.ButtonWidth() * k;
    }
}
