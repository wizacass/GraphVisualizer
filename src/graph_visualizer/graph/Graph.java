package graph_visualizer.graph;

public interface Graph<T>
{
    public int NodeCount();

    public int EdgesCount();

    public boolean IsEmpty();

    public void Clear();

    public void AddEdge(T e1, T e2);

    public void AddEdge(String label1, String label2);

    public void AddNode(T element);

    public void AddNode(T element, String label);

    public void RemoveEdge(T e1, T e2);

    public void RemoveEdge(String label1, String label2);

    public void RemoveNode(T element);

    public void RemoveNode(String label);

    public boolean Contains(T element);

    public boolean Contains(String label);

    public Graph<T> Clone();

    public T FindElementByLabel(String label);

    public int CalculateConnectedComponents();

    public Node<T>[] FindConnectionPoints();
}
