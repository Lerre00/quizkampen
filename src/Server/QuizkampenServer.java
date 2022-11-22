package Server;

import java.net.ServerSocket;
public class QuizkampenServer {
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Quizkampen Server is Running");
        try {
            while (true) {
                ServerSideGame game = new ServerSideGame();

                ServerSidePlayer player1 = new ServerSidePlayer(listener.accept(), '1', game);
                ServerSidePlayer player2 = new ServerSidePlayer(listener.accept(), '2', game);

                player1.setOpponent(player2);
                player2.setOpponent(player1);
                game.currentPlayer = player1;
                player1.start();
                player2.start();

                //ALternativ approach
                //Game2 game2 = new Game2(socket1, socket2);

            }
        } finally {
            listener.close();
        }
    }
}
