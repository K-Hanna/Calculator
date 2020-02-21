import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MyFrame extends JFrame {

    MyFrame(){

        setSize(215,340);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 2 - getWidth() / 2;
        int posY = height / 2 - getHeight() / 2;

        setTitle("Calculator");
        setResizable(false);
        setLocation(posX, posY);

        MyPanel myPanel = new MyPanel();
        add(myPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                myPanel.getButton().requestFocus();
            }
        });
    }
}
