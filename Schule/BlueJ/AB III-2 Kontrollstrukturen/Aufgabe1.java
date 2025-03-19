import java.util.Scanner;

public class Aufgabe1 {
    public Aufgabe1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("x^2 + [p]x + q : ");
        double p = scanner.nextDouble();

        System.out.println("x^2 + px + [q] : ");
        double q = scanner.nextDouble();

        double D = (p*p)/4 - q;

        if(D < 0) System.err.println("Discriminate D ist negativ!!!");
        else {
            double E1 = -(p/2) + Math.sqrt(Math.pow(p/2, 2) - q);
            double E2 = -(p/2) - Math.sqrt(Math.pow(p/2, 2) - q);

            System.out.println("x_1 = " + E1);
            System.out.println("x_2 = " + E2);
        }
    }
}