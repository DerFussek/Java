public class Operationen {
    public static double addition(double a, double b) {
        return a+b;
    }
    
    public static double subtraktion(double a, double b) {
        return a-b;
    }
    
    public static double multiplikation(double a, double b) {
        return a*b;
    }
    
    public static double division(double a, double b) {
        if(b == 0) throw new ArithmeticException("Division durch Null!");
        return a/b;
    }
}
