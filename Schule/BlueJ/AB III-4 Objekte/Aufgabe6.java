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
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Gib die Länge des 1.Rechecks ein: ");
        int length01 = scanner.nextInt();
        
        System.out.println("Gib die Breite des 1.Rechecks ein: ");
        int width01 = scanner.nextInt();
        
        Rechteck rect01 = new Rechteck(length01, width01);

        System.out.println("Gib die Länge des 2.Rechecks ein: ");
        int length02 = scanner.nextInt();
        
        System.out.println("Gib die Breite des 2.Rechecks ein: ");
        int width02 = scanner.nextInt();

        Rechteck rect02 = new Rechteck(length02, width02);

        System.out.println("Rechteck 01");
        System.out.println("Länge: " + rect01.getLength);
        System.out.println("Breite: " + rect01.getWidth);
        System.out.println("Fläche: " + rect01.getArea);
        System.out.println("Umfang: " + rect01.getScope);

        System.out.println("");
        System.out.println("=====================");
        System.out.println("");

        System.out.println("Rechteck 02");
        System.out.println("Länge: " + rect02.getLength);
        System.out.println("Breite: " + rect02.getWidth);
        System.out.println("Fläche: " + rect02.getArea);
        System.out.println("Umfang: " + rect02.getScope);

        scanner.close();
    }
}
