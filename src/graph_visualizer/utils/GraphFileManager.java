package graph_visualizer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GraphFileManager
{
    private final String mainDir = "graphs";

    public GraphFileManager() {}

    public Object[] FindGraphFiles()
    {
        var path = Paths.get(mainDir);
        var files = new File(path.toString()).listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null)
        {
            return null;
        }

        var fileNames = new ArrayList<String>();
        for (var file : files)
        {
            if (file.isFile())
            {
                fileNames.add(file.getName());
            }
        }

        return fileNames.toArray();
    }

    public List<String> ReadFile(String filename) throws IOException
    {
        var path = Paths.get(mainDir + "/" + filename);
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
