import java.awt.*;

public class Ball {
    private double x, y;
    private double vx, vy;

    private int radius;

    public Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;

        this.vx = 2;
        this.vy = 2;
    }

    public void move(double dt) {
        this.x += this.vx * dt;
        this.y += this.vy * dt;
    }

    public void checkCollisionWall(Player p1, Player p2, int scoreperCollision) {
        if(this.y <= 0 || this.y + radius >= Gamelogic.screenHeight) {
            this.vy = -this.vy;

        }
        if(this.x <= 0) {
            this.vx = -this.vx;
            p2.addScore(scoreperCollision);
        }

        if(this.x + radius >= Gamelogic.screenWidth) {
            this.vx = -this.vx;
            p1.addScore(scoreperCollision);
        }
    }

    public void checkCollisionPlayers(Player ... players) {


        for (Player p : players) {
            boolean istLinks = p.getX() < Gamelogic.screenWidth / 2;

            if (istLinks) {
                // Linker Spieler: prüfe rechten Rand vom Ball mit linkem Rand vom Schläger
                boolean xKollision = this.x <= p.getX() + p.getWidth();
                boolean yKollision = this.y + radius >= p.getY() &&
                        this.y - radius <= p.getY() + p.getHeight();

                if (xKollision && yKollision) {
                    this.vx = Math.abs(this.vx); // Ball geht nach rechts
                }
            } else {
                // Rechter Spieler: prüfe linken Rand vom Ball mit rechtem Rand vom Schläger
                boolean xKollision = this.x + radius >= p.getX();
                boolean yKollision = this.y + radius >= p.getY() &&
                        this.y - radius <= p.getY() + p.getHeight();

                if (xKollision && yKollision) {
                    this.vx = -Math.abs(this.vx); // Ball geht nach links
                }
            }
        }

    }

    public void draw(Graphics2D g, Color color) {
        g.setColor(color);
        g.fillOval((int) this.x,(int) this.y, this.radius, this.radius);
    }
}
