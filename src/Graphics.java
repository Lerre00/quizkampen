import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame{

    JPanel basePanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel northNorthPanel = new JPanel();
    JPanel southNorthPanel = new JPanel();
    JLabel questionFrame = new JLabel("Vad heter du?");
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JProgressBar progressBar = new JProgressBar();

    Graphics(){

        questionFrame.setFont(new Font("Arial", Font.BOLD, 20));
        basePanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new GridLayout(2,2));
        northPanel.setPreferredSize(new Dimension(100, 100));
        southPanel.setPreferredSize(new Dimension(150, 150));
        southPanel.add(button1);
        southPanel.add(button2);
        southPanel.add(button3);
        southPanel.add(button4);
        northPanel.add(northNorthPanel, BorderLayout.NORTH);
        northPanel.add(southNorthPanel, BorderLayout.SOUTH);
        southNorthPanel.add(progressBar, BorderLayout.CENTER);
        northNorthPanel.add(questionFrame);
        basePanel.add(northPanel, BorderLayout.NORTH);
        basePanel.add(southPanel);
        add(basePanel);

        setSize(350,350);
        setLocation(100,100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        Graphics g = new Graphics();
    }

}