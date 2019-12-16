package graph_visualizer.graph;

import java.util.List;

class GraphIterator<T> implements java.util.Iterator<T>
{
    private List<Node<T>> nodes;
    private int current;

    GraphIterator(List<Node<T>> nodes)
    {
        this.nodes = nodes;
        this.current = 0;
    }

    @Override
    public boolean hasNext()
    {
        return current < nodes.size();
    }

    @Override
    public T next()
    {
        return this.nodes.get(current++).Data();
    }
}