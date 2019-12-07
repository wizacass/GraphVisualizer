package graph_visualizer.graphics;

import javax.swing.*;

public class Window extends JFrame
{
    private WindowConstants constants = new WindowConstants();

    public Window(String title)
    {
        super(title);

        add(new MenuPanel());

        setSize(constants.WindowWidth(), constants.WindowHeight());
        this.setResizable(false);
        setLayout(null);
        setVisible(true);
    }
}
