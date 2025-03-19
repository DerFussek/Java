import java.awt.*;

public class Circle extends Shape{
    private int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    public int getRadius() {return radius;}
    public void setRadius(int radius) {this.radius = radius;}

    @Override
    void draw(Graphics2D g) {
        if(shadowColor != null) {
            drawShadow(g);
            g.fillOval(x - radius + 5, y - radius + 5, radius * 2, radius * 2);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
