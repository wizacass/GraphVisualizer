package graph_visualizer.graphics;

import graph_visualizer.utils.OS;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    private final WindowConstants constants = new WindowConstants();

    public Window(String title, OS os)
    {
        super(title);

        var graphPanel = new GraphPanel();

        this.add(new MenuPanel(graphPanel));
        this.add(graphPanel);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WindowSize(os));
        this.setResizable(false);
        this.setLayout(null);
        this.centerWindow();
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

    private void centerWindow()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
}
