package graph_visualizer.graph;

public class GraphTest
{
    public static void main(String[] args)
    {
        var graph = new VisualGraph<Integer>();
        graph.AddNode(1);
        graph.AddNode(2);
        graph.AddNode(3);
        graph.AddNode(4);
        graph.AddEdge("1", "2");
        graph.AddEdge(1, 4);
        graph.AddEdge("4", "3");
        graph.AddEdge(2, 3);
        graph.RemoveEdge(1, 2);
        graph.RemoveNode("3");
        graph.AddNode(3);
        graph.AddEdge(3, 4);
        graph.AddEdge(5, 6);
        graph.AddEdge("6", "2");
        graph.RemoveNode(2);

        graph.PrintGraph();
        System.out.println("Test complete!");

        for (var node: graph)
        {
            System.out.println(node);
        }
    }
}
