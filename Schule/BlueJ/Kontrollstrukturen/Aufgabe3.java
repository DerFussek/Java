import java.util.Scanner;

public class Aufgabe3 {
    public Aufgabe3() {
        Scanner scanner = new Scanner(System.in);
        int eingabe = 1;
        do {
            System.out.println("Zahl z eingeben:");
            eingabe = scanner.nextInt();

            if(eingabe==0) break;

            for(int z = 0; z < eingabe; z++) {
                System.out.println(z+1 + ".Durchläufe");
            }

        } while(eingabe != 0);

        scanner.close();
    }
}