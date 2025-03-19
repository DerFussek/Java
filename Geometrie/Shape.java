import java.awt.*;

abstract class Shape {

    //Position
    int x,y;

    //Color
    Color color;
    Color shadowColor;

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.shadowColor = null;
    }


    //Shadow
    public void enableShadow(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    public void disableShadow() {
        this.shadowColor = null;
    }

    //Drawing methods
    abstract void draw(Graphics2D g);

    void drawShadow(Graphics2D g) {
        if(shadowColor == null) {
            return;
        }

        g.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 80)); // Alpha = 80 für Transparenz
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    }
}
