package graph_visualizer.graphics;

import graph_visualizer.OS;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    private final WindowConstants constants = new WindowConstants();

    public Window(String title, OS os)
    {
        super(title);

        setSize(WindowSize(os));

        this.add(new MenuPanel());
        this.add(new GraphPanel());

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private Dimension WindowSize(OS os)
    {
        var dimensions = new Dimension();

        int macMenuBarHeight = 22;
        int windowsMenuBarHeight = 39;

        int height = constants.WindowHeight();
        switch (os)
        {
            case Windows:
                height += windowsMenuBarHeight;
                break;
            case Mac:
                height += macMenuBarHeight;
                break;
        }

        dimensions.width = os == OS.Windows ? constants.WindowWidth() + 16 : constants.WindowWidth();
        dimensions.height = height;

        return dimensions;
    }
}
