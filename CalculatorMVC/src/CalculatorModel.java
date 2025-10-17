import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
public class CalculatorModel {

    private BigDecimal A = BigDecimal.ZERO;
    private BigDecimal B = null;
    private String operator = null;
    private String display = "0";

    private static final MathContext MC = new MathContext(16, RoundingMode.HALF_UP);

    String getDisplay() {return display;}

    void clearAll() {
        A = BigDecimal.ZERO;
        B = null;
        operator = null;
        display = "0";
    }

    void backspace() {
        if (display.length() > 1) {
            display = display.substring(0, display.length() - 1);
        } else {
            display = "0";
        }
    }

    void inputDigit(String d) {
        if (!d.matches("[0-9]")) return;
        if (display.equals("0")) {
            display = d;
        } else {
            display += d;
        }
    }

    void inputDot() {
        if (!display.contains(".")) {
            display += ".";
        }
    }

    void toggleSign() {
        if (display.equals("0")) return;
        BigDecimal v = new BigDecimal(display);
        v = v.negate();
        display = stripZeros(v);
    }

    void percent() {
        BigDecimal v = new BigDecimal(display);
        v = v.divide(new BigDecimal("100"), MC);
        display = stripZeros(v);
    }

    void setOperator(String op) {
        if (operator == null) {
            A = new BigDecimal(display);
            operator = op;
            display = "0";
            B = null;
        } else {
            if (B != null || !display.equals("0")) {
                equals();
            }
            operator = op;
        }
    }

    void equals() {
        if (operator == null) return;
        if (B == null) {
            B = new BigDecimal(display);
        }
        BigDecimal result = compute(A, B, operator);
        display = stripZeros(result);
        A = BigDecimal.ZERO;
        B = null;
        operator = null;
    }

    private BigDecimal compute(BigDecimal a, BigDecimal b, String op) {
        switch(op) {
            case "+": return a.add(b, MC);
            case "-": return a.subtract(b, MC);
            case "×": return a.multiply(b, MC);
            case "÷":
                if (b.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
                return a.divide(b, MC);
            default: return b;
        }
    }

    private String stripZeros(BigDecimal v) {
        v = v.stripTrailingZeros();
        String s = v.toPlainString();
        if (s.equals("-0")) return "0";
        return s;
    }
}
