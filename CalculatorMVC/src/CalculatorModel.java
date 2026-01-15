import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
public class CalculatorModel {

    private BigDecimal A = BigDecimal.ZERO;
    private BigDecimal B = null;
    private String operator = null;
    private String display = "0.00";

    private static final MathContext MC = new MathContext(16, RoundingMode.HALF_UP);

    String getDisplay() {return formatDisplay(display); }

    void clearAll() {
        A = BigDecimal.ZERO;
        B = null;
        operator = null;
        display = "0.00";
    }

    void backspace() {
        if (display.length() > 1) {
            display = display.substring(0, display.length() - 1);
        } else {
            display = "0.00";
        }
    }

    void inputDigit(String d) {
        if (!d.matches("[0-9]")) return;
        if (display.equals("0.00") || display.equals("0")) {
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
        try {
            BigDecimal v = new BigDecimal(display);
            v = v.negate();
            display = v.toPlainString();
        } catch (Exception e) {
            display = "ERROR";
        }
    }

    void percent() {
        try {
            BigDecimal v = new BigDecimal(display);
            v = v.divide(new BigDecimal("100"), MC);
            display = v.toPlainString();
        } catch (Exception e) {
            display = "ERROR";
        }
    }

    void setOperator(String op) {
        if (operator == null) {
            try {
                A = new BigDecimal(display);
                operator = op;
                display = "0.00";
                B = null;
            } catch (Exception e) {
                display = "ERROR";
            }
        } else {
            if (B != null || !display.equals("0.00")) {
                equals();
            }
            operator = op;
        }
    }

    void equals() {
        if (operator == null) return;
        try {
            if (B == null) {
                B = new BigDecimal(display);
            }
            BigDecimal result = compute(A, B, operator);
            display = result.setScale(2, RoundingMode.HALF_UP).toPlainString();
            String intPart = display.contains(".") ? display.split("\\.")[0] : display;
            if (intPart.replace("-", "").length() > 5) {
                display = "ERROR";
            }

            A = BigDecimal.ZERO;
            B = null;
            operator = null;
        } catch (Exception e) {
            display = "ERROR";
        }
    }

    private BigDecimal compute(BigDecimal a, BigDecimal b, String op) {
        return switch (op) {
            case "+" -> a.add(b, MC);
            case "-" -> a.subtract(b, MC);
            case "×" -> a.multiply(b, MC);
            case "÷" -> {
                if (b.equals(BigDecimal.ZERO)) throw new RuntimeException("dividing by zero");
                yield a.divide(b, MC);
            }
            default -> b;
        };
    }

    private String formatDisplay(String raw) {
        if (raw.equals("ERROR")) return raw;
        try {
            double val = Double.parseDouble(raw);
            String formatted = String.format("%5.2f", val);
            String intPart = formatted.split("\\.")[0].trim();
            if (intPart.length() > 5) return "ERROR";
            return formatted;
        } catch (Exception e) {
            return "ERROR";
        }
    }
}