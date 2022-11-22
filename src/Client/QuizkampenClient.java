package Client;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;
public class QuizkampenClient extends JFrame{

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
        String response;
        char mark = 'S';
        char opponentMark = 'P';
        try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {
                mark = response.charAt(8);
                opponentMark = (mark == 'X' ? 'O' : 'X');
                frame.setTitle("Tic Tac Toe - Player " + mark);
            }
            while (true) {
                response = in.readLine();
                if (response.startsWith("VALID_MOVE")) {
                    messageLabel.setText("Valid move, please wait");
                    currentSquare.setText(String.valueOf(mark));
                    currentSquare.repaint();
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    int loc = Integer.parseInt(response.substring(15));
                    board[loc].setText(String.valueOf(opponentMark));
                    board[loc].repaint();
                    messageLabel.setText("Opponent moved, your turn");
                } else if (response.startsWith("VICTORY")) {
                    messageLabel.setText("You win");
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    messageLabel.setText("You lose");
                    break;
                } else if (response.startsWith("TIE")) {
                    messageLabel.setText("You tied");
                    break;
                } else if (response.startsWith("MESSAGE")) {
                    messageLabel.setText(response.substring(8));
                }
            }
            out.println("QUIT");
        }
        finally {
            socket.close();
        }
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
}
