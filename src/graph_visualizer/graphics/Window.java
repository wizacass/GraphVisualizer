package graph_visualizer.graphics;

import javax.swing.*;

public class Window extends JFrame
{
    public Window(String title)
    {
        super(title);

        int menuBarHeight = 22; //22px for Mac

        WindowConstants constants = new WindowConstants();
        setSize(
                constants.WindowWidth(),
                constants.WindowHeight() + menuBarHeight
        );

        this.add(new MenuPanel());
        this.add(new GraphPanel());

        this.setResizable(false);
        setLayout(null);
        setVisible(true);
    }
}
