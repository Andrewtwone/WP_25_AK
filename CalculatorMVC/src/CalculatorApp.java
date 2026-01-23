import javax.swing.*;

public class CalculatorApp {
    public static void main(String[] args) {
        // Use a cross-platform Look & Feel so Swing rendering
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            CalculatorView view = new CalculatorView();
            CalculatorModel model = new CalculatorModel();
            CalculatorController controller = new CalculatorController(model, view);
            view.setVisible(true);
        });
    }
}