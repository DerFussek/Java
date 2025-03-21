public class Rechteck {
    private int length, width;

    public Rechteck(int l, int b) {
        this.length = l;
        this.width = b;
    }
    
    int getLength() {
        return length;
    }
    
    int getWidth() {
        return width;
    }

    int getArea() {
        return this.length * this.width;
    }

    int getScope() {
        return 2 * this.length + 2 * this.width;
    }

}

public class Rechtecktest {
    public static void main( String[] args) {
        long length;
        long width;
        Rechteck rect01;
        rect01 = new Rechteck(3, 5);

        System.out.println("Länge: " + rect01.getLength());
        System.out.println("Breite: " + rect01.getWidth());
        System.out.println("Fläche: " + rect01.getArea());
        System.out.println("Umfang: " + rect01.getScope());
    }
}
