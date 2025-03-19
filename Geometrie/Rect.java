import java.awt.*;

public class Rect extends Shape{
    //Größe
    private int width, height;

    public Rect(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    void draw(Graphics2D g) {
        if(shadowColor !=null) {
            drawShadow(g);
            g.fillRoundRect(x, y , width + 3, height + 3, 15, 15); // Weiche Kanten mit Rundung
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
