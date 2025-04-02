public class PunktTest {
    public static void main(String[] args) {
        Punkt p1 = new Punkt(2, 2);
        Punkt p2 = new Punkt(1, 1);

        System.out.println(p2.getDistanceToOtherPoint(p1));

        Punkt p3 = p1.getAB(p2);

        System.out.println(p3.getX());
        System.out.println(p3.getY());
    }
}
