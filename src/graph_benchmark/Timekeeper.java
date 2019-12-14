package graph_benchmark;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Timekeeper
{
    private final long startTimeTot;
    private final int maxBenchmarks = 30;
    private final int countsNumber;
    private final String countFormat = "%8d(%2d) ";
    private final BlockingQueue<String> resultsLogger;
    private final Semaphore semaphore;

    private int[] counts;
    private long startTime;
    private double sumTime;
    private int currentBenchmark;
    private int currentCount;
    private int benchmarkCount;
    private double[][] times;
    private String benchmarkString;
    private String header = "  Count(*k) ";

    public Timekeeper(int[] counts)
    {
        this(counts, null, null);
    }

    private Timekeeper(int[] counts, BlockingQueue<String> resultsLogger, Semaphore semaphore)
    {
        this.counts = counts;
        this.resultsLogger = resultsLogger;
        this.semaphore = semaphore;
        countsNumber = this.counts.length;
        times = new double[countsNumber][maxBenchmarks];
        startTimeTot = System.nanoTime();
    }

    public void start() throws InterruptedException
    {
        currentBenchmark = 0;
        if (currentCount >= countsNumber)
        {
            logResult("Too much data changes!");
        }
        benchmarkString = String.format(
                countFormat,
                counts[currentCount],
                counts[currentCount] / counts[0]
        );
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime = System.nanoTime();
    }

    public void startAfterPause()
    {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime = System.nanoTime();
    }

    public void finish(String name) throws InterruptedException
    {
        double executionTime = (System.nanoTime() - startTime) / 1e9;
        sumTime += executionTime;
        if (startTime == 0)
        {
            logResult("Finished without starting!\n");
        }
        if (currentCount == 0)
        {
            header += String.format("%9s ", name);
        }
        if (currentBenchmark >= maxBenchmarks)
        {
            logResult("Max Benchmarks reached!!!\n");
        }
        times[currentCount][currentBenchmark++] = executionTime;
        benchmarkString += String.format("%9.4f ", executionTime);
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime = System.nanoTime();
    }

    public void seriesFinish() throws InterruptedException
    {
        if (currentCount == 0)
        {
            logResult(header + "\n");
        }
        logResult(benchmarkString + "\n");
        currentCount++;
        benchmarkCount = currentBenchmark;
        if (currentCount == countsNumber)
        {
            summary();
        }
    }

    private void summary() throws InterruptedException
    {
        var sb = new StringBuilder();
        double totTime = (System.nanoTime() - startTimeTot) / 1e9;
        sb.append(String.format("      Total Benchmarking time %8.3f seconds.", totTime)).append("\n");
        sb.append(String.format("            Benchmarking time %8.3f seconds.", sumTime)).append("\n");
        sb.append(String.format("     %5.1f%% consumed by additional actions.", (totTime - sumTime) / totTime * 100)).append("\n");
        sb.append("\n");
        sb.append("Normalized time table\n");
        sb.append(header).append("\n");
        double d1 = times[0][0];
        for (int i = 0; i < countsNumber; i++)
        {
            benchmarkString = String.format(countFormat, counts[i], counts[i] / counts[0]);
            for (int j = 0; j < benchmarkCount; j++)
            {
                benchmarkString += String.format("%9.2f ", times[i][j] / d1);
            }
            sb.append(benchmarkString).append("\n");
        }
        sb.append("\n");
        logResult(sb.toString());
    }

    public void logResult(String result) throws InterruptedException
    {
        if (resultsLogger != null && semaphore != null)
        {
            resultsLogger.put(result);
            semaphore.acquire();
        }
        else
        {
            System.out.println(result);
        }
    }
}