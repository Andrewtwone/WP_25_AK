import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;


class CalculatorView {
    final int boardWidth = 420;
    final int boardHeight = 640;
    final Color customLightGray = new Color(212, 212, 210);
    final Color customDarkGray = new Color(80, 80, 80);
    final Color customBlack = new Color(28, 28, 28);
    final Color customOrange = new Color(255, 149, 0);
    // Integer part in green, decimal part stays accent color
    final Color integerColor = new Color(0, 200, 0);
    final Color decimalColor = new Color(255, 200, 0);
    final Color errorColor = new Color(255, 80, 80);


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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        displayLabel.setBackground(customBlack);
//        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Consolas", Font.BOLD, 56));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setOpaque(true);
        displayLabel.setText(formatStyledText("0.00"));

        displayPanel.setPreferredSize(new Dimension(boardWidth, 120));
        displayPanel.add(displayLabel, BorderLayout.CENTER);
        frame.add(displayPanel, BorderLayout.NORTH);


        buttonsPanel.setBackground(customBlack);
        buttonsPanel.setPreferredSize(new Dimension(boardWidth, boardHeight - 120));
        frame.add(buttonsPanel, BorderLayout.CENTER);

        for (String value : buttonValues) {
            JButton button = new JButton(value);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setBorderPainted(false);
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

        frame.setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    void setVisible(boolean visible) { frame.setVisible(visible);}

    void setDisplay(String text) {displayLabel.setText(formatStyledText(text));}

    java.util.List<JButton> getAllButtons() {
        java.util.List<JButton> list = new java.util.ArrayList<>();
        for (Component c : buttonsPanel.getComponents()) {
            if (c instanceof JButton) list.add((JButton) c);
        }
        return list;
    }

    private String formatStyledText(String value) {
        if (value.equals("ERROR")) {
            return String.format(
                    "<html><font color='rgb(%d,%d,%d)'><b>%s</b></font></html>",
                    errorColor.getRed(), errorColor.getGreen(), errorColor.getBlue(), value);
        }

        int dotIdx = value.lastIndexOf('.');
        int commaIdx = value.lastIndexOf(',');
        int sepIdx = Math.max(dotIdx, commaIdx);

        if (sepIdx != -1) {
            String intPart = value.substring(0, sepIdx);
            String decimalPart = value.substring(sepIdx + 1);
            char sepChar = value.charAt(sepIdx);
            return String.format(
                    "<html><font color='rgb(%d,%d,%d)'>%s</font><font color='rgb(%d,%d,%d)'>%c%s</font></html>",
                    integerColor.getRed(), integerColor.getGreen(), integerColor.getBlue(), intPart,
                    decimalColor.getRed(), decimalColor.getGreen(), decimalColor.getBlue(), sepChar, decimalPart);
        }

        return String.format("<html><font color='rgb(%d,%d,%d)'>%s</font></html>",
                integerColor.getRed(), integerColor.getGreen(), integerColor.getBlue(), value);
    }
}