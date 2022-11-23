package Client;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class QuizkampenClient extends JFrame implements ActionListener {
    String playerNumber;
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


    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    String response;


    public QuizkampenClient(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        questionFrame.setFont(new Font("Arial", Font.BOLD, 20));

        basePanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        northNorthPanel.setLayout(new BorderLayout());
        eastNorthNorthPanel.setLayout(new GridLayout(3, 1));
        westNorthNorthPanel.setLayout(new GridLayout(3, 1));
        southPanel.setLayout(new GridLayout(2, 2));

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

        westNorthNorthPanel.add(radioButton1);
        westNorthNorthPanel.add(radioButton2);
        westNorthNorthPanel.add(radioButton3);

        eastNorthNorthPanel.add(opponentRadioButton1);
        eastNorthNorthPanel.add(opponentRadioButton2);
        eastNorthNorthPanel.add(opponentRadioButton3);

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

    }

    public void play() throws Exception {
        playerNumber = in.readLine();

        String question = in.readLine();
        String wrongAlternative1 = in.readLine();
        String wrongAlternative2 = in.readLine();
        String wrongAlternative3 = in.readLine();
        String rightAlternative = in.readLine();

        ArrayList <String> allAlternatives = new ArrayList<>();
        allAlternatives.add(wrongAlternative1);
        allAlternatives.add(wrongAlternative2);
        allAlternatives.add(wrongAlternative3);
        allAlternatives.add(rightAlternative);

        Collections.shuffle(allAlternatives);

        questionFrame.setText(question);
        button1.setText(allAlternatives.get(0));
        button2.setText(allAlternatives.get(1));
        button3.setText(allAlternatives.get(2));
        button4.setText(allAlternatives.get(3));


        if(rightAlternative == in.readLine()){
            radioButton1.setSelected(true);
            questionFrame.setText("Rätt svar!");
        } else {
            questionFrame.setText("Fel svar!");
        }

        //take response from server, update client with info if answer is true or false
        /*
        String response;
        try {
            while (true) {
                response = in.readLine();
                if (response.equals("CORRECT_ANSWER") && playerNumber.equals("player1")) {
                    System.out.println(playerNumber);
                    radioButton1.setSelected(true);
                    questionFrame.setText("Rätt svar!");
                    this.repaint();
                }
                if (response.equals("CORRECT_ANSWER") && playerNumber.equals("player2")) {
                    System.out.println(playerNumber);
                    opponentRadioButton1.setSelected(true);
                    questionFrame.setText("Rätt svar!");
                    this.repaint();
                }
                if (response.equals("INCORRECT_ANSWER")) {
                    questionFrame.setText("Fel svar");
                    this.repaint();
                }
            }

        } finally {
            socket.close();
        }*/
    }

    private boolean wantsToPlayAgain() {
        int response = JOptionPane.showConfirmDialog(this, "Want to play again?", "Tic Tac Toe is Fun Fun Fun", JOptionPane.YES_NO_OPTION);
        dispose();
        return response == JOptionPane.YES_OPTION;
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            QuizkampenClient client = new QuizkampenClient(serverAddress);
            client.setSize(350, 300);
            client.setLocation(100, 100);
            client.setVisible(true);
            client.setDefaultCloseOperation(EXIT_ON_CLOSE);
            client.play();
            if (!client.wantsToPlayAgain()) {
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            out.println(playerNumber + button1.getText());
        }
        if (e.getSource() == button2) {
            out.println(playerNumber + button2.getText());
        }
        if (e.getSource() == button3) {
            out.println(playerNumber + button3.getText());
        }
        if (e.getSource() == button4) {
            out.println(playerNumber + button4.getText());
        }
    }
}
