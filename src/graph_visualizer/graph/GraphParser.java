package graph_visualizer.graph;

import java.util.List;
import java.util.Map;

public class GraphParser
{
    public GraphParser() {}

    public Graph<Integer> CreateGraphFromIntegers(List<String> text)
    {
        var graph = new VisualGraph<Integer>();
        for (var line: text)
        {
            var data = line.trim().split("\\s+");
            try
            {
                int vertex = Integer.parseInt(data[0]);
                graph.AddNode(vertex);
                for (int i = 1; i < data.length; i++)
                {
                    int neighbor = Integer.parseInt(data[i]);
                    graph.AddEdge(vertex, neighbor);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Unable to create Graph from provided text!");
                return null;
            }
        }
        return graph;
    }

    public Graph<Integer> CreateGraphFromIntegers(List<String> text, Map<Integer, String> labels)
    {
        var graph = new VisualGraph<Integer>();
        for (var line: text)
        {
            var data = line.trim().split("\\s+");
            try
            {
                int vertex = Integer.parseInt(data[0]);
                graph.AddNode(vertex, labels.get(vertex));
                for (int i = 1; i < data.length; i++)
                {
                    int neighbor = Integer.parseInt(data[i]);
                    graph.AddNode(neighbor, labels.get(neighbor));
                    graph.AddEdge(vertex, neighbor);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Unable to create Graph from provided text!");
                return null;
            }
        }
        return graph;
    }
}
