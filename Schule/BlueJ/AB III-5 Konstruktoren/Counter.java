public class Counter {
    private int count;

    public Counter(int startwert) {
        this.count = startwert;
    }

    public void ausgeben() {
        System.out.println("Der aktuelle Wert beträgt: " + this.count);
    }

}
