package graph_visualizer.graph;

class Node<T>
{
    private String label;
    private int id;
    private T data;
    private Node<T>[] neighbors;

    public Node(T data, String label, int id)
    {
        this.data = data;
        this.label = label;
        this.id = id;
    }

    public String toString()
    {
        return label + "+\n" + data.toString();
    }
}
