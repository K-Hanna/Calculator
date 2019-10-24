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

        digits = new JButton[4][4];
        digits[0][0] = new JButton("7");
        digits[0][1] = new JButton("4");
        digits[0][2] = new JButton("1");
        digits[0][3] = new JButton("0");
        digits[1][0] = new JButton("8");
        digits[1][1] = new JButton("5");
        digits[1][2] = new JButton("2");
        digits[1][3] = new JButton(".");
        digits[2][0] = new JButton("9");
        digits[2][1] = new JButton("6");
        digits[2][2] = new JButton("3");
        digits[2][3] = new JButton("/");
        digits[3][0] = new JButton("+");
        digits[3][1] = new JButton("-");
        digits[3][2] = new JButton("*");
        digits[3][3] = new JButton("=");

    }

    private void addFields() {
        add(results);
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                add(digits[i][j]);
                digits[i][j].setBounds(i * 50, (j + 1)* 50, 50, 50);
            }
        }
    }

    private void actions(){
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if(i == 3 && j == 3) {
                    digits[i][j].addActionListener(e -> {
                        results.setText(Calculator.calculate(results.getText()));
                        clicked = true;
                    });
                }
                JButton button = Digits.getButton(digits[i][j]);
                button.addActionListener(e -> {
                    if(clicked) {
                        if (button.getText().matches(".*[0-9].*"))
                            results.setText("");
                    }
                    if(button.getText().equals("=")) {
                        results.setText(results.getText());
                    } else {
                        results.setText(results.getText() + button.getText());
                        clicked = false;
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