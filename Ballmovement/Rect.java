public abstract class Rect {
    protected double x, y;
    protected int w, h;

    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    //PUNKTPROBE
    boolean containsPoint(int a, int b) {
        double r = (double) (a - this.x) /this.w;
        double s = (double) (b-this.y)/this.h;

        return 0 <= r && r <= 1 && 0 <= s && s <= 1;
    }

    //Lagebeziehungen zu anderen Rechtecken
    boolean containsRect(Rect other) {
        return this.x + this.w > other.getX() && other.getX() + other.getW() > this.x &&
                this.y + this.h > other.getY() && other.getY() + other.getH() > this.y;
    }

    boolean intersects(Rect other) {
        return this.x < other.x + other.w &&
                this.x + this.w > other.x &&
                this.y < other.y + other.h &&
                this.y + this.h > other.y;
    }

    boolean isInside(Rect outer) {
        return this.x >= outer.x &&
                this.y >= outer.y &&
                this.x + this.w <= outer.x + outer.w &&
                this.y + this.h <= outer.y + outer.h;
    }

    //Vergrößern u. verkleinern des Rechtecks
    void scale(double factor) {
        this.w = (int)(this.w * factor);
        this.h = (int)(this.h * factor);
    }

    //Koordinaten des Mittelpunkts
    double getCenterX() {
        return this.x + this.w / 2;
    }

    double getCenterY() {
        return this.y + this.h / 2;
    }

    //Flächeninhalt
    double getArea() {
        return this.w * this.h;
    }

    //Umfang
    double getScope() {
        return 2*this.w + 2*this.h;
    }

    
    
    //Alg. Funktionen
    public String toString() {
        return "Rect[x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + "]";
    }

    //GETTER u. SETTER
    double getX() {return this.x;}
    double getY() {return this.y;}

    double getW() {return this.w;}
    double getH() {return this.h;}
    
    void setX(double x) {
        this.x = x;
    }
    
    void setY(double y) {
        this.y = y;
    }
}
