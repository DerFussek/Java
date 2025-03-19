import java.util.Scanner;

public class Aufgabe2() {
    public Aufgabe2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Radius eingeben: ");
        double radius = scanner.nextDouble();

        double U = Math.PI * 2 * radius;
        double A = Math.PI * radius*radius;

        System.out.println("U = " + U);
        System.out.println("A = " + A);


        scanner.close();
    }
}