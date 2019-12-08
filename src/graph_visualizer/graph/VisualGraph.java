package graph_visualizer.graph;

public class VisualGraph<T> implements Graph
{
    @Override
    public int Size()
    {
        return 0;
    }

    @Override
    public boolean IsEmpty()
    {
        return true;
    }

    @Override
    public void Clear()
    {

    }

    @Override
    public void AddEdge(Node n1, Node n2)
    {

    }

    @Override
    public void AddNode(Node node)
    {

    }

    @Override
    public void RemoveEdge(Node n1, Node n2)
    {

    }

    @Override
    public void RemoveNode(Node node)
    {

    }

    @Override
    public void RemoveNodeById(int id)
    {

    }

    @Override
    public void RemoveNodeByLabel(String label)
    {

    }

    @Override
    public Node FindNode(Node node)
    {
        return null;
    }

    @Override
    public Node FindNodeById(int id)
    {
        return null;
    }

    @Override
    public Node FindNodeByLabel(String label)
    {
        return null;
    }

    @Override
    public int[] CalculateConnectedComponents()
    {
        return new int[0];
    }

    @Override
    public Node[] FindConnectionPoints()
    {
        return new Node[0];
    }
}
