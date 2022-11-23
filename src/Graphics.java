import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JFrame implements ActionListener {

    JPanel basePanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel northPanel = new JPanel();

    JPanel northNorthPanel = new JPanel();
    JPanel southNorthPanel = new JPanel();
    JPanel centralNorthPanel = new JPanel();

    JPanel westNorthNorthPanel = new JPanel();
    JPanel eastNorthNorthPanel = new JPanel();
    JPanel centralNorthNorthPanel = new JPanel();

    JRadioButton radioButton1 = new JRadioButton();
    JRadioButton radioButton2 = new JRadioButton();
    JRadioButton radioButton3 = new JRadioButton();

    JRadioButton opponentRadioButton1 = new JRadioButton();
    JRadioButton opponentRadioButton2 = new JRadioButton();
    JRadioButton opponentRadioButton3 = new JRadioButton();

    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();

    JLabel themeLabel = new JLabel("Tema: Natur");
    JLabel questionFrame = new JLabel("Waiting for opponent...");
    JProgressBar progressBar = new JProgressBar();

    Graphics(){

        questionFrame.setFont(new Font("Arial", Font.BOLD, 20));

        basePanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        northNorthPanel.setLayout(new BorderLayout());
        eastNorthNorthPanel.setLayout(new GridLayout(3, 1));
        westNorthNorthPanel.setLayout(new GridLayout(3,1));
        southPanel.setLayout(new GridLayout(2,2));

        northPanel.setPreferredSize(new Dimension(100, 150));
        southPanel.setPreferredSize(new Dimension(150, 100));

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        southPanel.add(button1);
        southPanel.add(button2);
        southPanel.add(button3);
        southPanel.add(button4);

        eastNorthNorthPanel.add(radioButton1);
        eastNorthNorthPanel.add(radioButton2);
        eastNorthNorthPanel.add(radioButton3);

        westNorthNorthPanel.add(opponentRadioButton1);
        westNorthNorthPanel.add(opponentRadioButton2);
        westNorthNorthPanel.add(opponentRadioButton3);

        centralNorthNorthPanel.add(themeLabel);

        northNorthPanel.add(westNorthNorthPanel, BorderLayout.WEST);
        northNorthPanel.add(eastNorthNorthPanel, BorderLayout.EAST);
        northNorthPanel.add(centralNorthNorthPanel, BorderLayout.CENTER);

        northPanel.add(northNorthPanel, BorderLayout.NORTH);
        northPanel.add(centralNorthPanel, BorderLayout.CENTER);
        northPanel.add(southNorthPanel, BorderLayout.SOUTH);

        centralNorthPanel.add(questionFrame, BorderLayout.CENTER);
        southNorthPanel.add(progressBar, BorderLayout.CENTER);

        basePanel.add(northPanel, BorderLayout.NORTH);
        basePanel.add(southPanel);
        add(basePanel);

        setSize(350,300);
        setLocation(100,100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        Graphics g = new Graphics();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}