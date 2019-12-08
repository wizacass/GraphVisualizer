package graph_visualizer.graphics;

import graph_visualizer.graph.Graph;
import graph_visualizer.utils.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class GraphPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();
    private Graph<Integer> graph;

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

    void setActiveGraph(Graph graph)
    {
        this.graph = graph;
        this.repaint();
    }

    public void paint(Graphics g)
    {
//        DrawGrid(g);
//        DrawBase(g);
        DrawGraph(g);
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

    private void DrawGraph(Graphics g)
    {
        if (graph == null || graph.IsEmpty()) return;

        int nodeRadius = constants.NodeCircumference() / 2;
        int graphRadius = constants.GraphCircumference() / 2;
        var centerCoordinates = new Coordinates(
                constants.GraphPanelWidth() / 2,
                constants.GraphPanelHeight() / 2
        );

        DrawNodes(g, nodeRadius, graphRadius, centerCoordinates);
        DrawEdges(g, graphRadius, centerCoordinates);
        DrawLabels(g, nodeRadius,graphRadius, centerCoordinates);
    }

    private void DrawNodes(Graphics g, int nodeRadius, int graphRadius, Coordinates centerCoordinates)
    {
        var nodeCount = graph.NodeCount();
        if (nodeCount == 1)
        {
            g.drawOval(
                    centerCoordinates.x - nodeRadius,
                    centerCoordinates.y - nodeRadius,
                    constants.NodeCircumference(),
                    constants.NodeCircumference()
            );
            return;
        }

        for (int i = 0; i < nodeCount; i++)
        {
            var c = GetNodeCoordinates(i, graphRadius, centerCoordinates);
            g.drawOval(
                    c.x - nodeRadius,
                    c.y - nodeRadius,
                    constants.NodeCircumference(),
                    constants.NodeCircumference()
            );
        }
    }

    private Coordinates GetNodeCoordinates(int k, int graphRadius, Coordinates centerCoordinates)
    {
        var coordinates = new Coordinates();

        double mainAngle = 360.0 / graph.NodeCount();
        double angle = Math.toRadians(mainAngle * (k));
        coordinates.x = (int)((centerCoordinates.x + graphRadius * Math.cos(angle)));
        coordinates.y = (int)((centerCoordinates.y - graphRadius * Math.sin(angle)));

        return coordinates;
    }

    private void DrawEdges(Graphics g, int graphRadius, Coordinates centerCoordinates)
    {
        var nodeCount = graph.NodeCount();
        for (int i = 0; i < nodeCount; i++)
        {
            var c = GetNodeCoordinates(i, graphRadius, centerCoordinates);
            var element = graph.FindElementByIndex(i);
            var neighbors = graph.NeighborIndexes(element);
            for (var index: neighbors)
            {
                var nc = GetNodeCoordinates(index, graphRadius, centerCoordinates);
                g.drawLine(c.x, c.y, nc.x, nc.y);
            }
        }
    }

    private void DrawLabels(Graphics g, int nodeRadius, int graphRadius, Coordinates centerCoordinates)
    {
        var nodeCount = graph.NodeCount();
        if (nodeCount == 1)
        {
            var label = graph.NodeLabel(0);
            g.drawString(
                    label,
                    centerCoordinates.x,
                    centerCoordinates.y
            );
            return;
        }

        for (int i = 0; i < nodeCount; i++)
        {
            var c = GetNodeCoordinates(i, graphRadius, centerCoordinates);
            var label = graph.NodeLabel(i);
            g.drawString(label, c.x, c.y);
        }
    }
}
