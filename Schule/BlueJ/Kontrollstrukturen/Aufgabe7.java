import java.util.Scanner;

public class Aufgabe7 {
    public Aufgabe7() {
        While();
        For();
        DoWhile();
    }

    void While() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wert n eingeben: ");
        double n = scanner.nextDouble();

        double i = 3;
        while(i < 2*n) {
            System.out.println(1/(2*i+1));
            i++;
        }

        scanner.close();
    }

    void For() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wert n eingeben: ");
        double n = scanner.nextDouble();

        for(double i = 3; i < 2*n; i++) {
            System.out.println(1/(2*i+1));
        }

        scanner.close();
    }

    void DoWhile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wert n eingeben: ");
        double n = scanner.nextDouble();

        double i = 3;

        if(i < 2*n) {
            do {
                System.out.println(1/(2*i+1));
                i++;
            } while(i < 2*n);
        }

        scanner.close();
    }
}