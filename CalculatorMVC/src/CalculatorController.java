import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class CalculatorController implements ActionListener{
    private final CalculatorModel model;
    private final CalculatorView view;

    private static final Set<String> OPERATORS = Set.of("+", "-", "×", "÷");

    CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        for (JButton b: view.getAllButtons()) {
            b.addActionListener(this);
        }
        view.setDisplay(model.getDisplay());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String v = ((JButton) e.getSource()).getText();

        if (OPERATORS.contains(v)) {
            model.setOperator(v);
        } else if (v.equals("=")) {
            model.equals();
        } else if (v.equals("AC")) {
            model.clearAll();
        } else if (v.equals("⌫")) {
            model.backspace();
        } else if (v.equals("+/-")) {
            model.toggleSign();
        } else if (v.equals("%")) {
            model.percent();
        } else if (v.equals(".")) {
            model.inputDot();
        } else if (v.matches("[0-9]")){
            model.inputDigit(v);
        }

        view.setDisplay(model.getDisplay());
    }

}