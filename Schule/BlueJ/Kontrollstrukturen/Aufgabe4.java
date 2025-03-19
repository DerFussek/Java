import java.util.Scanner;

public class Aufgabe4 {
    public Aufgabe4() {
        Scanner scanner = new Scanner(System.in);
        int eingabe = 1;
        double anzahl = 0;
        double summe = 0;

        do {
            System.out.println("Zahl z eingeben:");
            eingabe = scanner.nextInt();

            if(eingabe==0) break;
            ++anzahl;
            summe+=eingabe;

        } while(eingabe != 0);

        if(anzahl !=0) System.out.println("Der Mittelwert ist: " + summe/anzahl);
        
        scanner.close();
    }
}