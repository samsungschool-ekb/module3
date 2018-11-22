import javax.swing.*;

public class SimpleFileLoader extends JFrame {

    public SimpleFileLoader(){
        setTitle("Simple file loader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new SimpleFileLoaderPanel());
        pack();
        setLocation((1920 / 2) - (getWidth() / 2),
                (1080 / 2) - (getHeight() / 2));
        setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleFileLoader();
    }
}
