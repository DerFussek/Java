public class Aufgabe4 {
    public Aufgabe4() {
        String s = "reliefPFEILER";
        int length = s.length();
        char Char[] = new char[length];

        for(int i = length-1; i >= 0; i--) {
            Char[i] = s.charAt(i);
        }

        for(char x : Char) {
            System.out.print(x);
        }
    }
}