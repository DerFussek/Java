import java.util.Scanner;

public class Aufgabe2 {
    public Aufgabe2() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Anzahl der Klassen angeben: ");
        int numberClasses = scanner.nextInt();

        System.out.println("Klassengröße angeben: ");
        int numberStudents = scanner.nextInt();

        int Zahlenfeld[][] = new int[numberClasses][ numberStudents];


        for(int classes = 0; classes < numberClasses; classes ++) {
            for(int students = 0; students < numberStudents; students++) {
                System.out.println("Alter eingeben [" + (numberStudents-students) + "]   :");
                Zahlenfeld[classes][students] = scanner.nextInt();
            }
        }

        double Ergebnisse[] = new double[numberClasses];
        for(int i = 0; i < numberClasses; i++) {
            Ergebnisse[i] = mittelwert(Zahlenfeld, i, numberStudents);
        }

        for(double e : Ergebnisse) {
            System.out.println("Klasse = " + e);
        }

        scanner.close();
    }


    double mittelwert(int feld[][], int classes, int students) {
        double summe = 0;
        for (double x : feld[classes]) {
            summe+=x;
        }
        return summe/students;
    }
}
