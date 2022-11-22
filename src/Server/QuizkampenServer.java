package Server;

import java.net.ServerSocket;
public class QuizkampenServer {
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Quizkampen Server is Running");
        try {
            while (true) {
                ServerSideGame game = new ServerSideGame();

                ServerSidePlayer player1 = new ServerSidePlayer(listener.accept(),"player1" , game);
                ServerSidePlayer player2 = new ServerSidePlayer(listener.accept(), "player2", game);

                player1.setOpponent(player2);
                player2.setOpponent(player1);
                game.currentPlayer = player1;
                player1.start();
                player2.start();

                //Alternativ approach
                //Game2 game2 = new Game2(socket1, socket2);

            }
        } finally {
            listener.close();
        }
    }
}
