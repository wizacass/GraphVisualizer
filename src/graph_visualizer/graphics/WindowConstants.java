package graph_visualizer.graphics;

class WindowConstants
{
    private final int width = 360;
    private final int height = 480;
    private final int scale = 1;

    WindowConstants() {}

    int Width()
    {
        return width * scale;
    }

    int Height()
    {
        return height * scale;
    }
}
