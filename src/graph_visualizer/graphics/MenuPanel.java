package graph_visualizer.graphics;

import javax.swing.*;

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
                constants.WindowHeight() - constants.MenuPanelHeight(),
                constants.WindowWidth(),
                constants.MenuPanelHeight()
        );
        this.setBackground(constants.MenuPanelColor());
    }

    private void AddButtons()
    {
        int y = constants.MenuPanelHeight() - constants.Margin() - constants.ButtonHeight();

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
