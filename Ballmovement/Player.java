import java.awt.*;

public class Player extends Rect {  
    private double speed = 200;
    
    public Player(int x, int y, int w, int h) {
        super(x, y, w, h);
    }
    
    boolean WallCollision() {
        if(super.x < 0 || super.x > SpielFeld.screenWidth - super.w) {
            if(super.x < 0) {
                super.setX(0);
            } else {
                super.setX(SpielFeld.screenWidth - super.w);
            }
            
            return true;
        } else if(super. y < 0 || super.y > SpielFeld.screenHeight - super.h) {
            if(super.y < 0) {
                super.setY(0);
            } else {
                super.setY(SpielFeld.screenHeight - super.h);
            }
        }
        
        return false;
    }
    
    
    //RENDERING
    void draw(Graphics2D g) {
         g.setColor(new Color(255, 255, 255));
         g.fillRect((int)super.x, (int)super.y, super.w, super.h);
    }
    
    void moveUp(double d_time) {
        super.y -= speed * d_time;
    }
    
    void moveDown(double d_time) {
        super.y += speed * d_time;
    }
    
    void addSpeed(double f) {
        this.speed *= f;
    }
    
};