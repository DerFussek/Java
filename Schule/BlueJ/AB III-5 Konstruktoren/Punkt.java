public class Punkt {
    private double x, y;

    public Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Verschiebung
    void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    //DISTANCE to another Point
    double getDistanceToOtherPoint(Punkt p) {
        return Math.sqrt(Math.pow((p.getX() - this.x), 2) + Math.pow((p.getY() - this.y), 2));
    }

    //GET VECTOR AB
    Punkt getAB(Punkt p) {
        double x1 = p.getX() - this.x;
        double x2 = p.getY() - this.y;

        return new Punkt(x1, x2);
    }

    //  GET/SET - METHODEN
    void setX(double x) {
        this.x = x;
    }

    void setY(double y) {
        this.y = y;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }


}