package graph_visualizer.graph;

import java.util.ArrayList;
import java.util.List;

class Node<T>
{
    private String label;
    private T data;
    private List<Node<T>> neighbors;

    Node(T data, String label)
    {
        this.data = data;
        this.label = label;
        this.neighbors = new ArrayList<>();
    }

    Node(T data)
    {
        this(data, data.toString());
    }

    String Label()
    {
        return label;
    }

    T Data()
    {
        return data;
    }

    void AddNeighbor(Node<T> neighbor)
    {
        if (!neighbors.contains(neighbor))
        {
            neighbors.add(neighbor);
        }
    }

    void RemoveNeighbor(Node<T> neighbor)
    {
        neighbors.remove(neighbor);
    }

    List<T> Neighbors()
    {
        var list = new ArrayList<T>();
        for (var neighbor : neighbors)
        {
            list.add(neighbor.data);
        }
        return list;
    }

    Node<T> Clone()
    {
        var newNode = new Node<>(this.data, this.label);
        for (var neighbor : neighbors)
        {
            newNode.AddNeighbor(new Node<>(neighbor.data, neighbor.label));
        }
        return newNode;
    }

    public String toString()
    {
        var sb = new StringBuilder();
        sb.append(label);
        if (neighbors.size() > 0)
        {
            sb.append(": ");
            for (var neighbor : neighbors)
            {
                sb.append(neighbor.label);
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {

        if (o == this)
        {
            return true;
        }

        if (!(o instanceof Node))
        {
            return false;
        }

        var c = (Node<T>) o;
        return this.Data().equals(c.Data()) && this.Label().equals(c.Label());
    }
}
