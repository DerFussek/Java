import java.awt.*;

public class Ball {
    private double x = SpielFeld.screenWidth/2, y = SpielFeld.screenHeight/2;
    private int vx = -16, vy = -16;
    private int speed = 10;
    private int size = 10;
    
    
    //KONSTRUKTOR
    public Ball() {
        
    }
    
    void move(double d_time) {
        this.x += this.vx * this.speed * d_time;
        this.y += this.vy * this.speed * d_time;
    }
    
    void draw(Graphics2D g) {
        g.setColor(new Color(255, 255, 255));
        g.fillOval((int)this.x, (int)this.y, this.size, this.size);
    }
    
    void checkCollision() {
        if(this.x >= SpielFeld.screenWidth - this.size) {
            this.vx *= -1;
            this.x = SpielFeld.screenWidth - this.size;
        } else if(this.x <= 0) {
            this.vx *= -1;
            this.x = 0;
        }
        
        if(this.y >= SpielFeld.screenHeight - this.size) {
            this.y = SpielFeld.screenHeight - this.size;
            this.vy *= -1;
        } else if(this.y <= 0) {
            this.y = 0;
            this.vy *=-1;
        }
        
    }
    
    //Allg. Funktionen
    double getSpeed() {
        return Math.sqrt((vx*vx) + (vy*vy));
    }
};