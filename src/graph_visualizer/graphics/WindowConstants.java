package graph_visualizer.graphics;

import java.awt.*;

class WindowConstants
{
    private final int gridScale = 32;
    private final int scale = 1;

    private final Color menuPanelColor = Color.gray;

    WindowConstants() {}

    int WindowWidth()
    {
        return 8 * gridScale * scale;
    }

    int WindowHeight()
    {
        return 12 * gridScale * scale;
    }

    int ButtonWidth()
    {
        return 2 * gridScale * scale;
    }

    int ButtonHeight()
    {
        return (int)(gridScale * scale / 2);
    }

    Color MenuPanelColor()
    {
        return menuPanelColor;
    }
}
