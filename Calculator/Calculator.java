
public class Calculator {
    private Operationen op;
    public Calculator() {
        op = new Operationen();
    }
    
    public double evaluate(double a, double b, String operator) {
        switch(operator) {
            case "+":
                return op.addition(a, b);
            case "-":
                return op.subtraktion(a, b);
            case "*":
                return op.multiplikation(a, b);
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division durch Null nicht erlaubt");
                }
                return op.division(a, b);
            default:
                throw new IllegalArgumentException("Ungültiger Operator: " + operator);
        }
    }
}
