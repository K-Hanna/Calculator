import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {

    MyFrame(){

        setSize(205,330);

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
    }
}