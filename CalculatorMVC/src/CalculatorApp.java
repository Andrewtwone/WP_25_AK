public class CalculatorApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            CalculatorView view = new CalculatorView();
            CalculatorModel model = new CalculatorModel();
            CalculatorController controller = new CalculatorController(model, view);
            view.setVisible(true);
        });
    }
}