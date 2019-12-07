package graph_visualizer.graphics;

import javax.swing.*;
import java.awt.*;

class GraphPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();

    GraphPanel()
    {
        this.Initialize();
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
        this.setForeground(constants.GraphPanelDrawColor());
    }

    public void paint(Graphics g)
    {
        //DrawGrid(g);
        //DrawBase(g);
        DrawNodes(g, 8);
    }

    private void DrawGrid(Graphics g)
    {
        int boundary = this.getHeight();

        g.drawLine(0,0, boundary, boundary);
        g.drawLine(0, boundary, boundary, 0);

        g.drawLine(boundary - 1, boundary - 1, boundary - 1, 0);
        g.drawLine(boundary - 1, boundary - 1, 0, boundary - 1);

        for (int i = 0; i <= boundary; i += constants.Margin())
        {
            g.drawLine(0, i, boundary, i);
            g.drawLine(i, 0, i, boundary);
        }
    }

    private void DrawBase(Graphics g)
    {
        g.drawOval(constants.Margin(), constants.Margin(), constants.GraphCircumference(), constants.GraphCircumference());
    }

    private void DrawNodes(Graphics g, int count)
    {
        int centerX = constants.GraphPanelWidth() / 2;
        int centerY = constants.GraphPanelHeight() / 2;

        int nodeRadius = constants.NodeCircumference() / 2;
        int graphRadius = constants.GraphCircumference() / 2;

        double mainAngle = 360.0 / count;

        for (int i = 0; i < count; i++)
        {
            double angle = Math.toRadians(mainAngle * i);
            int x = (int)((centerX + graphRadius * Math.cos(angle)) - nodeRadius);
            int y = (int)((centerY - graphRadius * Math.sin(angle)) - nodeRadius);
            System.out.println("X: " + x + " Y: " + y);
            g.drawOval(x, y, constants.NodeCircumference(), constants. NodeCircumference());
        }
    }
}
