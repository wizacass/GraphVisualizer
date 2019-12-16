package graph_visualizer.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisualGraph<T> implements Graph<T>, Iterable<T>
{
    private List<Node<T>> nodes;
    private int nodeCount;
    private int edgesCount;

    public VisualGraph()
    {
        nodes = new ArrayList<>();
        nodeCount = 0;
        edgesCount = 0;
    }

    @Override
    public int NodeCount()
    {
        return nodeCount;
    }

    @Override
    public int EdgesCount()
    {
        return edgesCount;
    }

    @Override
    public boolean IsEmpty()
    {
        return nodeCount == 0;
    }

    @Override
    public void Clear()
    {
        nodes = new ArrayList<>();
        nodeCount = 0;
        edgesCount = 0;
    }

    @Override
    public void AddEdge(T e1, T e2)
    {
        var n1 = this.FindNode(e1);
        var n2 = this.FindNode(e2);

        if (n1 == null)
        {
            n1 = new Node<>(e1);
            this.AddNode(n1);
        }

        if (n2 == null)
        {
            n2 = new Node<>(e2);
            this.AddNode(n2);
        }

        this.AddEdge(n1, n2);
    }

    @Override
    public void AddEdge(String label1, String label2)
    {
        var n1 = this.FindNode(label1);
        var n2 = this.FindNode(label2);
        this.AddEdge(n1, n2);
    }

    private void AddEdge(Node<T> n1, Node<T> n2)
    {
        if(n1 != null && n2 != null)
        {
            n1.AddNeighbor(n2);
            n2.AddNeighbor(n1);
            edgesCount++;
        }
    }

    @Override
    public void AddNode(T element)
    {
        var node = this.FindNode(element);
        if(node == null)
        {
            nodes.add(new Node<>(element));
            nodeCount++;
        }
    }

    @Override
    public void AddNode(T element, String label)
    {
        var node = this.FindNode(element);
        if(node == null)
        {
            nodes.add(new Node<>(element, label));
            nodeCount++;
        }
    }

    private void AddNode(Node<T> node)
    {
        nodes.add(node);
        nodeCount++;
    }

    @Override
    public void RemoveEdge(T e1, T e2)
    {
        var n1 = this.FindNode(e1);
        var n2 = this.FindNode(e2);
        this.RemoveEdge(n1, n2);
    }

    @Override
    public void RemoveEdge(String label1, String label2)
    {
        var n1 = this.FindNode(label1);
        var n2 = this.FindNode(label2);
        this.RemoveEdge(n1, n2);
    }

    private void RemoveEdge(Node<T> n1, Node<T> n2)
    {
        if(n1 != null && n2 != null)
        {
            n1.RemoveNeighbor(n2);
            n2.RemoveNeighbor(n1);
        }
        edgesCount--;
    }

    @Override
    public void RemoveNode(T element)
    {
        var nodeToRemove = this.FindNode(element);
        this.RemoveNode(nodeToRemove);
    }

    @Override
    public void RemoveNode(String label)
    {
        var nodeToRemove = this.FindNode(label);
        this.RemoveNode(nodeToRemove);
    }

    private void RemoveNode(Node<T> nodeToRemove)
    {
        if(nodeToRemove != null)
        {
            for(var node: nodes)
            {
                node.RemoveNeighbor(nodeToRemove);
            }
            nodes.remove(nodeToRemove);
            nodeCount--;
        }
    }

    private Node<T> FindNode(T element)
    {
        for (var node: nodes)
        {
            if(node.Data().equals(element))
            {
                return node;
            }
        }
        return null;
    }

    private Node<T> FindNode(String label)
    {
        for (var node: nodes)
        {
            if(node.Label().equals(label))
            {
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean Contains(T element)
    {
        return this.FindNode(element) != null;
    }

    @Override
    public boolean Contains(String label)
    {
        return this.FindNode(label) != null;
    }

    @Override
    public Graph<T> Clone()
    {
        var newGraph = new VisualGraph<T>();
        for (var node: this.nodes)
        {
            newGraph.AddNode(node.Clone());
        }
        return newGraph;
    }

    @Override
    public T FindElementByLabel(String label)
    {
        var node = this.FindNode(label);
        return node == null ? null : node.Data();
    }

    @Override
    public T FindElementByIndex(int index)
    {
        return (index < 0 || index > nodeCount) ? null : nodes.get(index).Data();
    }

    @Override
    public String NodeLabel(T element)
    {
        var node = FindNode(element);
        return node == null ? null : node.Label();
    }

    @Override
    public String NodeLabel(int index)
    {
        return (index < 0 || index > nodeCount) ? null : nodes.get(index).Label();
    }

    @Override
    public int IndexOf(String label)
    {
        var node = this.FindNode(label);
        return IndexOf(node);
    }

    @Override
    public int IndexOf(T element)
    {
        var node = this.FindNode(element);
        return IndexOf(node);
    }

    private int IndexOf(Node<T> node)
    {
        return node == null ? -1 : nodes.indexOf(node);
    }

    @Override
    public int ConnectedComponents()
    {
        int components = 0;
        var visited = new boolean[nodeCount];
        for (int i = 0; i < nodeCount; ++i)
        {
            if (visited[i]) continue;

            DepthFirstSearch(i, visited);
            components++;
        }

        return components;
    }

    private void DepthFirstSearch(int current, boolean[] visited)
    {
        visited[current] = true;
        var currentNode = nodes.get(current);
        var currentNeighbors = currentNode.Neighbors();
        for (var neighbor : currentNeighbors)
        {
            int currentIndex = IndexOf(neighbor);
            if (!visited[currentIndex])
            {
                DepthFirstSearch(currentIndex, visited);
            }
        }
    }

    @Override
    public List<Integer> NeighborIndexes(String label)
    {
        var node = this.FindNode(label);
        return this.NeighborIndexes(node);
    }

    @Override
    public List<Integer> NeighborIndexes(T element)
    {
        var node = this.FindNode(element);
        return this.NeighborIndexes(node);
    }

    private List<Integer> NeighborIndexes(Node<T> node)
    {
        if (node == null)
        {
            return null;
        }

        var list = new ArrayList<Integer>();
        var neighbors = node.Neighbors();
        for (var neighbor: neighbors)
        {
            list.add(IndexOf(neighbor));
        }
        return list;
    }

    @Override
    public List<Integer> FindConnectionPoints()
    {
        var points = new ArrayList<Integer>();
        int comp = ConnectedComponents();
        for (int i = 0; i < nodeCount; i++)
        {
            var newGraph = this.Clone();
            var element = newGraph.FindElementByIndex(i);
            newGraph.RemoveNode(element);
            int removedComp = newGraph.ConnectedComponents();

            if (removedComp > comp)
            {
                points.add(i);
            }
        }
        return points;
    }

    @Override
    public void PrintGraph()
    {
        for(var node: nodes)
        {
            System.out.println(node.toString());
        }
        System.out.println();
    }

    public String toString()
    {
        var sb = new StringBuilder();
        for(var node: nodes)
        {
            sb.append("|");
            sb.append(node.toString());
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new GraphIterator<T>(nodes);
    }
}
