package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import javax.swing.*;
public class QuizkampenClient extends JFrame implements ActionListener {
    String playerNumber;
    JPanel basePanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel northNorthPanel = new JPanel();
    JPanel southNorthPanel = new JPanel();
    JLabel questionFrame = new JLabel("Waiting for opponent..");
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JProgressBar progressBar = new JProgressBar();

    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;


    public QuizkampenClient(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        questionFrame.setFont(new Font("Arial", Font.BOLD, 20));
        basePanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new GridLayout(2,2));
        northPanel.setPreferredSize(new Dimension(100, 100));
        southPanel.setPreferredSize(new Dimension(150, 150));
        button1.addActionListener(this);
        button2.addActionListener( this);
        button3.addActionListener( this);
        button4.addActionListener( this);
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

    }

    public void play() throws Exception {
        playerNumber = in.readLine();

        questionFrame.setText(in.readLine());
        button1.setText(in.readLine());
        button2.setText(in.readLine());
        button3.setText(in.readLine());
        button4.setText(in.readLine());

        in.readLine();
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
            client.setSize(350,350);
            client.setLocation(100,100);
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
        if (e.getSource() == button1){

            out.println(playerNumber + button1.getText());
        }
        if (e.getSource() == button2){
            out.println(playerNumber +button2.getText());
        }
        if (e.getSource() == button3){
            out.println(playerNumber +button3.getText());
        }
        if (e.getSource() == button4){
            out.println(playerNumber +button4.getText());
        }
    }
}
