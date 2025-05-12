public class Kapital {

    //ATTRIBUTE
    private double kontoStand;
    private double rate;

    public final double ZINSSATZ = 0.03;
    private static long KontoNr = 0;


    //KONSTRUKTOREN
    public Kapital(double kontoStand, double rate) {
        this.kontoStand = kontoStand;
        this.rate = rate;
        KontoNr++;
    }

    public Kapital() {
        this(0, 0);
    }

    //METHODEN
    public void schreibeKapital() {
        System.out.println("Kapital: " + this.kontoStand);
        System.out.println("Rate: " + this.rate);
    }
    public void rateHinzu() {
        this.kontoStand += this.rate;
    }

    public void verzinsung() {
        this.kontoStand*= 1 + this.ZINSSATZ;
    }

    //GETTER
    public double getKontoStand() {
        return this.kontoStand;
    }

    public double getRate() {
        return this.rate;
    }

    //SETTER
    public void setKontoStand(double betrag) {
        this.kontoStand = betrag;
    }

    public void setRate(double rate) {
        if(rate < 0) return;
        this.rate = rate;
    }

}
