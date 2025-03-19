public class Aufgabe3 {
    
    static int zufzahl = 0;
    
    final int a = 8121;
    final int b = 28411;
    final int m = 134456;
    
    public Aufgabe3() {
        for(int i = 0; i < 20; i++) {
            zufzahl = (a*zufzahl+b) % m;
            System.out.println(zufzahl);
        }
    }
}