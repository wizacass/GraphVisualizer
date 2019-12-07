package graph_visualizer.graphics;

import javax.swing.*;

class GraphPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();

    GraphPanel()
    {
        Initialize();
    }

    private void Initialize()
    {
        this.setBounds(
                constants.Margin(),
                constants.Margin(),
                constants.GraphPanelWidth(),
                constants.GraphPanelHeight()
        );
        this.setBackground(constants.GraphPanelBackgroundColor());
    }
}
