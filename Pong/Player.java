import java.awt.*;

public class Player {

    private double x, y;
    private int width, height;
    private int speed = 4;

    private int score = 0;

    public Player(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

    }

    public void moveUp(double dt) {
        if(this.y > 0) this.y -= this.speed * dt;
    }

    public void moveDOWN(double dt) {
        if(this.y < Gamelogic.screenHeight - this.height) this.y += this.speed * dt;
    }

    //DRAW-METHOD
    public void draw(Graphics2D g, Color color) {
        g.setColor(color);
        g.fillRect((int) this.x, (int) this.y, this.width, this.height);
    }

    //GET-METHODEN
    public double getX() {return this.x;}
    public double getY() {return this.y;}

    public int getHeight() {return this.height;}
    public int getWidth() {return this.width;}


    public int getScore() {return this.score;}

    //SET-METHODEn
    public void setX(int x) {
        if(x < 0) return;
        if(x > Gamelogic.screenWidth) return;

        this.x = x;
    }
    public void setY(int y) {
        if(y < 0) return;
        if(y > Gamelogic.screenHeight) return;

        this.y = y;
    }

    public void setHeight(int h) {
        if(h < 0) return;
        this.height = h;
    }
    public void setWidth(int w) {
        if(w < 0) return;
        this.width = w;
    }

    public void setScore(int s) {
        if(s < 0) return;
        this.score = s;
    }

    public void addScore(int add) {
        this.score += add;
    }
}
