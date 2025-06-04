import java.awt.*;

public class Ball extends Rect{
    private double vx = -128, vy = -128;
    private double speed = 1;

    //KONSTRUKTOR
    public Ball(int x, int y, int r) {
        super(x, y, r, r);
    }
    
    void move(double d_time) {
        super.x += this.vx * speed * d_time;
        super.y += this.vy * speed * d_time;
    }
    
    void draw(Graphics2D g) {
        g.setColor(new Color(255, 255, 255));
        g.fillOval((int)super.x, (int)super.y, super.w, super.h);
    }
    
    boolean WallCollision() {
        if(super.x < 0 || super.x > SpielFeld.screenWidth - super.w) {
            if(super.x < 0) {
                super.setX(0);
            } else {
                super.setX(SpielFeld.screenWidth - super.w);
            }
            
            flipDirection(1);
            return true;
        } else if(super. y < 0 || super.y > SpielFeld.screenHeight - super.h) {
            if(super.y < 0) {
                super.setY(0);
            } else {
                super.setY(SpielFeld.screenHeight - super.h);
            }
            
            flipDirection(2);
            return true;
        }
        
        return false;
    }   
    
    void flipDirection(int a) {
        if(a == 1) {
            this.vx *= -1;
        } else if(a == 2) {
            this.vy *= -1; 
        } else if(a == 0) {
            this.vx *= -1;
            this.vy *= -1; 
        }
        
    }
    
    void addSpeed(double f) {
        this.speed *= f;
    }

    //Allg. Funktionen
    double getSpeed() {
        return Math.sqrt((vx*vx) + (vy*vy));
    }
};