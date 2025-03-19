import java.util.Scanner;

public class Aufgabe1 {
    public Aufgabe1() {
        Scanner scanner = new Scanner(System.in);

        int zahlenfeld[];
        zahlenfeld = new int[20];
        for (int k = 0 ; k <= 19 ; k++ ) {
            System.out.println("Alter eingeben [" + (20-k) + "]   :");
            zahlenfeld[k] = scanner.nextInt();
        }
        double ergebnis = mittelwert(zahlenfeld);
        System.out.println("Ergebnis: " + ergebnis);

        scanner.close();
    }

    static double mittelwert(int [] feld) {
        int j;
        double summe;
        summe = 0;
        for (j = 0; j <= 19; j++) summe = summe + feld[j];
        summe = summe / 20;
        return summe;
    }
}
