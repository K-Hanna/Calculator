import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.IOException;

class MyPanel extends JPanel{

    private JTextField results;
    private JButton[][] digits;
    private boolean clicked;
    private KeyListener keyListener;

    MyPanel() {

        setLayout(null);
        createFields();
        addFields();
        defineKeys();
        actions();
    }

    private void createFields() {

        results = new JTextField();
        results.setBounds(0, 0, 200, 50);
        results.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                try {
                    String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
//                    char c = e.getKeyChar();

                    if (!((e.getKeyCode() == KeyEvent.VK_C) && (KeyEvent.VK_CONTROL) != 0)) {
                        if(!data.matches("[0-9]+"))
                            results.setText("Wrong input");
                        digits[0][0].requestFocus();
                        e.consume();
/*                    } else if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();*/
                    }
                } catch (UnsupportedFlavorException | IOException ex) {
                    results.setText("");
                    digits[0][0].requestFocus();
                }
            }
        });

        digits = new JButton[4][5];
        digits[0][0] = new JButton("(");
        digits[0][0].setToolTipText("[");
        digits[0][1] = new JButton("7");
        digits[0][2] = new JButton("4");
        digits[0][3] = new JButton("1");
        digits[0][4] = new JButton("0");
        digits[1][0] = new JButton(")");
        digits[1][0].setToolTipText("]");
        digits[1][1] = new JButton("8");
        digits[1][2] = new JButton("5");
        digits[1][3] = new JButton("2");
        digits[1][4] = new JButton(".");
        digits[2][0] = new JButton("CE");
        digits[2][1] = new JButton("9");
        digits[2][2] = new JButton("6");
        digits[2][3] = new JButton("3");
        digits[2][4] = new JButton("/");
        digits[3][0] = new JButton("C");
        digits[3][1] = new JButton("+");
        digits[3][2] = new JButton("-");
        digits[3][3] = new JButton("*");
        digits[3][4] = new JButton("=");
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

                final JButton button = Digits.getButton(aDigit);
                button.addKeyListener(keyListener);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String text = results.getText();
                        if (text.equals("Error") || text.equals("Wrong input"))
                            results.setText("");

                        if (clicked) {
                            if (button.getText().matches(".*[0-9].*"))
                                results.setText("");
                        }
                        switch (button.getText()) {
                            case "=":
                                results.setText(Calculator.calculate(results.getText()));
                                digits[1][4].setEnabled(true);
                                digits[0][0].setEnabled(true);
                                digits[1][0].setEnabled(true);
                                clicked = true;
                                break;
                            case "CE":
                                if (text.length() > 0)
                                    results.setText(text.substring(0, text.length() - 1));
                                break;
                            case "C":
                                results.setText("");
                                digits[1][4].setEnabled(true);
                                digits[0][0].setEnabled(true);
                                digits[1][0].setEnabled(true);
                                break;
                            case "+":
                            case "-":
                            case "/":
                            case "*":
                                if (text.substring(text.length() - 1).equals("+") || text.substring(text.length() - 1).equals("-") ||
                                        text.substring(text.length() - 1).equals("/") || text.substring(text.length() - 1).equals("*"))
                                    results.setText(text.substring(0, text.length() - 1));
                                results.setText(results.getText() + button.getText());
                                clicked = false;
                                digits[1][4].setEnabled(true);
                                break;
                            case ".":
                            case "(":
                            case ")":
                                button.setEnabled(false);
                                results.setText(results.getText() + button.getText());
                                break;
                            default:
                                results.setText(results.getText() + button.getText());
                                clicked = false;
                                break;
                        }
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

    private void defineKeys() {
        keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    digits[3][4].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_DELETE)
                    digits[3][0].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    digits[2][0].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_0 | e.getKeyCode() == KeyEvent.VK_NUMPAD0)
                    digits[0][4].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_1 | e.getKeyCode() == KeyEvent.VK_NUMPAD1)
                    digits[0][3].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_2 | e.getKeyCode() == KeyEvent.VK_NUMPAD2)
                    digits[1][3].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_3 | e.getKeyCode() == KeyEvent.VK_NUMPAD3)
                    digits[2][3].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_4 | e.getKeyCode() == KeyEvent.VK_NUMPAD4)
                    digits[0][2].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_5 | e.getKeyCode() == KeyEvent.VK_NUMPAD5)
                    digits[1][2].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_6 | e.getKeyCode() == KeyEvent.VK_NUMPAD6)
                    digits[2][2].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_7 | e.getKeyCode() == KeyEvent.VK_NUMPAD7)
                    digits[0][1].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_8 | e.getKeyCode() == KeyEvent.VK_NUMPAD8)
                    digits[1][1].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_9 | e.getKeyCode() == KeyEvent.VK_NUMPAD9)
                    digits[2][1].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET)
                    digits[0][0].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET)
                    digits[1][0].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_COMMA | e.getKeyCode() == KeyEvent.VK_DECIMAL)
                    digits[1][4].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_ADD)
                    digits[3][1].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT)
                    digits[3][2].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY)
                    digits[3][3].doClick();
                else if (e.getKeyCode() == KeyEvent.VK_DIVIDE)
                    digits[2][4].doClick();
            }
        };
    }

    JButton getButton(){
        return digits[0][0];
    }
}
