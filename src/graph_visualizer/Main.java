package graph_visualizer;

import graph_visualizer.graphics.Window;
import graph_visualizer.utils.OS;

public class Main
{
    public static void main(String[] args)
    {
        new Window("Graph Visualizer", DetermineOs());
    }

    private static OS DetermineOs()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
        {
            return OS.Windows;
        }
        else if (os.contains("mac"))
        {
            return OS.Mac;
        }
        else
        {
            return OS.Other;
        }
    }
}
