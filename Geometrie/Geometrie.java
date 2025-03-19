import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Geometrie {
    private List<Shape> shapes = new ArrayList<>();

    public Rect createRect(int x, int y, int width, int height, Color color) {
        Rect rect = new Rect(x, y, width, height, color);
        shapes.add(rect);
        return rect;
    }

    public Circle createCircle(int x, int y, int radius, Color color) {
        Circle circle = new Circle(x, y, radius, color);
        shapes.add(circle);
        return circle;
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
