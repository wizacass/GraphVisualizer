package graph_visualizer.utils;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParser
{
    private GraphFileManager fileManager;

    public FileParser()
    {
        fileManager = new GraphFileManager();
    }

    public Map<Integer, String> CreateLabelDictionary()
    {
        try
        {
            var data = fileManager.ReadFile("districts", "dictionaries");
            return ParseText(data);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private Map<Integer, String> ParseText(List<String> text) throws Exception
    {
        var dict = new HashMap<Integer, String>();
        for (var line: text)
        {
            var data = line.trim().split("\\s+");
            int key = Integer.parseInt(data[0]);
            String value = data[1];
            dict.put(key, value);
        }
        return dict;
    }
}
