package graph_visualizer.graph;

public class GraphFactory
{
    public Graph<Integer> CreateEmptyIntGraph(int count)
    {
        var graph = new VisualGraph<Integer>();
        for (int i = 1; i < count + 1; i++)
        {
            graph.AddNode(i);
        }
        return graph;
    }

    public Graph<Integer> CreateFullIntGraph(int count)
    {
        var graph = new VisualGraph<Integer>();
        for (int i = 1; i < count + 1; i++)
        {
            for (int j = 1; j < count + 1; j++)
            {
                if (i == j) continue;

                graph.AddEdge(i, j);
            }
        }
        return graph;
    }
}
