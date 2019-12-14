package graph_visualizer.graph;

import java.util.Random;

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

    public Graph<Integer> CreateRandomIntGraph(int count, int seed)
    {
        var rnd = new Random(seed);
        var graph = new VisualGraph<Integer>();

        for (int i = 1; i < count + 1; i++)
        {
            int rndCount = rnd.nextInt((int) (count * 0.5)) + 1;
            for (int j = 1; j < rndCount; j++)
            {
                int next = rnd.nextInt(count - 1) + 1;
                if (i == next) continue;

                graph.AddEdge(i, next);
            }
        }
        return graph;
    }
}
