import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;


class CalculatorView {
    final int boardWidth = 360;
    final int boardHeight = 540;


    final Color customLightGray = new Color(212, 212, 210);
    final Color customDarkGray = new Color(80, 80, 80);
    final Color customBlack = new Color(28, 28, 28);
    final Color customOrange = new Color(255, 149, 0);


    final String[] buttonValues = {
            "⌫", "AC", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
    };
    final java.util.List<String> rightSymbols = Arrays.asList("÷", "×", "-", "+", "=");
    final java.util.List<String> topSymbols = Arrays.asList("⌫", "AC", "%");


    final JFrame frame = new JFrame("Calculator");
    final JLabel displayLabel = new JLabel();
    final JPanel displayPanel = new JPanel(new BorderLayout());
    final JPanel buttonsPanel = new JPanel(new GridLayout(5, 4));


    CalculatorView() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);


        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);


        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        for (String value : buttonValues) {
            JButton button = new JButton(value);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));
            if (topSymbols.contains(value)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else if (rightSymbols.contains(value)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            } else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }
            buttonsPanel.add(button);
        }
    }

    void setVisible(boolean visible) { frame.setVisible(visible);}

    void setDisplay(String text) {displayLabel.setText(text);}

    java.util.List<JButton> getAllButtons() {
        java.util.List<JButton> list = new java.util.ArrayList<>();
        for (Component c : buttonsPanel.getComponents()) {
            if (c instanceof JButton) list.add((JButton) c);
        }
        return list;
    }
}