package graph_benchmark;

import graph_visualizer.graph.GraphFactory;

import java.util.Random;

class Benchmark
{
    private final Timekeeper timekeeper;
    private final int[] counts = {100, 200, 400, 800, 1600, 3200};

    private Random rnd;
    private GraphFactory factory;

    private Benchmark()
    {
        timekeeper = new Timekeeper(counts);
        factory = new GraphFactory();

        rnd = new Random();
        rnd.setSeed(2020);
    }

    private void run()
    {
        try
        {
            benchmark();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }

    private void benchmark() throws InterruptedException
    {
        try
        {
            for (var count : counts)
            {
                timekeeper.startAfterPause();
                timekeeper.start();

                var graph = factory.CreateRandomIntGraph(count, rnd);
                timekeeper.finish("  Create");

                for (int i = 0; i < rnd.nextInt(count / 2); i++)
                {
                    graph.RemoveNode(rnd.nextInt(graph.NodeCount()));
                }
                timekeeper.finish("  Remove");
                timekeeper.seriesFinish();
            }
            timekeeper.logResult("");
        }
        catch (Exception e)
        {
            timekeeper.logResult(e.getMessage());
        }
    }

    public static void main(String... args)
    {
        System.out.println("Benchmark starts!");
        new Benchmark().run();
        System.out.println("Benchmark ends!");
    }
}
