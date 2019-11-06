import javax.swing.*;

class MyPanel extends JPanel{

    private JTextField results;
    private JButton[][] digits;
    private boolean clicked;

    MyPanel() {

        setLayout(null);
        createFields();
        addFields();
        actions();
    }

    private void createFields() {

        results = new JTextField();
        results.setBounds(0, 0, 200, 50);

        digits = new JButton[4][5];
        digits[0][0] = new JButton("7");
        digits[0][1] = new JButton("4");
        digits[0][2] = new JButton("1");
        digits[0][3] = new JButton("0");
        digits[0][4] = new JButton("(");
        digits[1][0] = new JButton("8");
        digits[1][1] = new JButton("5");
        digits[1][2] = new JButton("2");
        digits[1][3] = new JButton(".");
        digits[1][4] = new JButton(")");
        digits[2][0] = new JButton("9");
        digits[2][1] = new JButton("6");
        digits[2][2] = new JButton("3");
        digits[2][3] = new JButton("/");
        digits[2][4] = new JButton("CE");
        digits[3][0] = new JButton("+");
        digits[3][1] = new JButton("-");
        digits[3][2] = new JButton("*");
        digits[3][3] = new JButton("=");
        digits[3][4] = new JButton("C");
    }

    private void addFields() {
        add(results);
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits[i].length; j++) {
                add(digits[i][j]);
                digits[i][j].setBounds(i * 50, (j + 1)* 50, 50, 50);
            }
        }
    }

    private void actions(){
        for (JButton[] digit : digits) {
            for (JButton aDigit : digit) {

                JButton button = Digits.getButton(aDigit);
                button.addActionListener(e -> {
                    if (clicked) {
                        if (button.getText().matches(".*[0-9].*"))
                            results.setText("");
                    }
                    switch (button.getText()) {
                        case "=":
                            results.setText(Calculator.calculate(results.getText()));
                            clicked = true;
                            break;
                        case "CE":
                            String text = results.getText();
                            results.setText(text.substring(0, text.length() - 1));
                            break;
                        case "C":
                            results.setText("");
                            break;
                        default:
                            results.setText(results.getText() + button.getText());
                            clicked = false;
                            break;
                    }
                });
            }
        }
    }

    static class Digits {

        Digits() {}

        static JButton getButton(JButton button) {
            return button;
        }
    }
}