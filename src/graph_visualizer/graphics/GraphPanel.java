package graph_visualizer.graphics;

import javax.swing.*;
import java.awt.*;

class GraphPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();
    private int nodeCount = 0;

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

    void setCount(int count)
    {
        nodeCount = count;
        this.repaint();
    }

    public void paint(Graphics g)
    {
        //DrawGrid(g);
        //DrawBase(g);
        DrawNodes(g);
    }

    private void DrawGrid(Graphics g)
    {
        int boundary = this.getHeight();

        g.drawLine(0,0, boundary, boundary);
        g.drawLine(0, boundary, boundary, 0);

//        g.drawLine(boundary - 1, boundary - 1, boundary - 1, 0);
//        g.drawLine(boundary - 1, boundary - 1, 0, boundary - 1);

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

    private void DrawNodes(Graphics g)
    {
        if (nodeCount == 0) return;

        int centerX = constants.GraphPanelWidth() / 2;
        int centerY = constants.GraphPanelHeight() / 2;

        int nodeRadius = constants.NodeCircumference() / 2;
        int graphRadius = constants.GraphCircumference() / 2;

        if (nodeCount == 1)
        {
            g.drawOval(
                    centerX - nodeRadius,
                    centerY - nodeRadius,
                    constants.NodeCircumference(),
                    constants.NodeCircumference()
            );
            return;
        }

        double mainAngle = 360.0 / nodeCount;

        for (int i = 0; i < nodeCount; i++)
        {
            double angle = Math.toRadians(mainAngle * i);
            int x = (int)((centerX + graphRadius * Math.cos(angle)) - nodeRadius);
            int y = (int)((centerY - graphRadius * Math.sin(angle)) - nodeRadius);
            g.drawOval(x, y, constants.NodeCircumference(), constants. NodeCircumference());
        }
    }
}
