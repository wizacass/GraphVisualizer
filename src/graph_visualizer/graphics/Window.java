package graph_visualizer.graphics;

import javax.swing.*;

public class Window extends JFrame
{
    private WindowConstants constants = new WindowConstants();

    public Window(String title)
    {
        super(title);

        var button = new JButton("Click");
        button.setBounds(130,100,100, 40);
        add(button);

        setSize(constants.Width(), constants.Height());
        this.setResizable(false);
        setLayout(null);
        setVisible(true);
    }
}
