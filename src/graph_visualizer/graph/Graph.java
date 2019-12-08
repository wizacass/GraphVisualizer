package graph_visualizer.graph;

import java.util.ArrayList;
import java.util.List;

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

    public T FindElementByIndex(int index);

    public String NodeLabel(T element);

    public String NodeLabel(int index);

    public int IndexOf(String label);

    public int IndexOf(T element);

    public int ConnectedComponents();

    public List<Integer> NeighborIndexes(String label);

    public List<Integer> NeighborIndexes(T element);

    public List<Integer> FindConnectionPoints();

    public void PrintGraph();
}
