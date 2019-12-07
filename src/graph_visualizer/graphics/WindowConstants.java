package graph_visualizer.graphics;

import java.awt.*;

class WindowConstants
{
    private final int windowsHeightUnits = 12;
    private final int gridScale = 32;
    private final int scale = 2;
    private final int mainScale = gridScale * scale;

    private final Color menuPanelColor = Color.lightGray;
    private final Color graphPanelColor = Color.white;
    private final Color graphDrawColor = Color.black;
    private final Color errorTextColor = Color.red;

    WindowConstants() {}

    int WindowWidth()
    {
        return 8 * mainScale;
    }

    int WindowHeight()
    {
        return windowsHeightUnits * mainScale;
    }

    int ButtonWidth()
    {
        return 2 * mainScale;
    }

    int ButtonHeight()
    {
        return mainScale / 2;
    }

    int Margin()
    {
        return mainScale / 2;
    }

    int MenuPanelHeight()
    {
        return (windowsHeightUnits / 3) * mainScale;
    }

    int GraphPanelWidth()
    {
        return WindowWidth() - 2 * Margin();
    }

    int GraphPanelHeight()
    {
        return ((WindowHeight() / 3) * 2) - (2 * Margin());
    }

    int GraphCircumference()
    {
        return GraphPanelHeight() - Margin() * 2;
    }

    int NodeCircumference()
    {
        return mainScale / 2;
    }

    Color MenuPanelColor()
    {
        return menuPanelColor;
    }

    Color GraphPanelBackgroundColor()
    {
        return graphPanelColor;
    }

    Color GraphPanelDrawColor()
    {
        return graphDrawColor;
    }

    Color ErrorTextColor()
    {
        return errorTextColor;
    }
}
