package graph_visualizer.graph;

public interface Graph<T>
{
    public int Size();

    public boolean IsEmpty();

    public void Clear();

    public void AddEdge(Node<T> n1, Node<T> n2);

    public void AddNode(Node<T> node);

    public void RemoveEdge(Node<T> n1, Node<T> n2);

    public void RemoveNode(Node<T> node);

    public void RemoveNodeById(int id);

    public void RemoveNodeByLabel(String label);

    public Node<T> FindNode(Node<T> node);

    public Node<T> FindNodeById(int id);

    public Node<T> FindNodeByLabel(String label);

    public int[] CalculateConnectedComponents();

    public Node<T>[] FindConnectionPoints();
}
