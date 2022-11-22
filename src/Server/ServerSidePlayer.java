package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ServerSidePlayer extends Thread {
    String playerNumber;
    ServerSidePlayer opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    ServerSideGame game;

    int pointsPlayer1 = 0;

    int pointsPlayer2 = 0;


    public ServerSidePlayer(Socket socket, String playerName, ServerSideGame game) {
        this.socket = socket;
        this.playerNumber = playerName;
        this.game = game;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }


    public void setOpponent(ServerSidePlayer opponent) {
        this.opponent = opponent;
    }


    public ServerSidePlayer getOpponent() {
        return opponent;
    }


    public int getPointsPlayer1() {
        return pointsPlayer1;
    }

    public int getPointsPlayer2() {
        return pointsPlayer2;
    }

    public void setPointsPlayer1(int pointsPlayer1) {
        this.pointsPlayer1 = pointsPlayer1;
    }

    public void setPointsPlayer2(int pointsPlayer2) {
        this.pointsPlayer2 = pointsPlayer2;
    }

    public void run() {
        try {
            //Sending playerNumber to clients
            output.println(playerNumber);

            //Print question and alternatives

            output.println("Hur stor är?");
            output.println("Alternativ 1");
            output.println("Alternativ 2");
            output.println("Alternativ 3");
            output.println("Alternativ 4");

            //Recieve answer from player
            String playerNumberAndAnswerFromClient = input.readLine();
            System.out.println(playerNumberAndAnswerFromClient);

            //Look if answer was correct and give point if true
            String playerNumberFromClient = playerNumberAndAnswerFromClient.substring(0,7);
            System.out.println(playerNumberFromClient);
            String answerFromClient = playerNumberAndAnswerFromClient.substring(7);
            if (playerNumberFromClient == "player1" && answerFromClient == "Alternativ 3"){
                setPointsPlayer1(getPointsPlayer1() + 1);
            }
            if (playerNumberFromClient == "player2" && answerFromClient == "Alternativ 3"){
              setPointsPlayer2(getPointsPlayer2() + 1);
            }
            System.out.println(answerFromClient);
            System.out.println(getPointsPlayer1());
            System.out.println(getPointsPlayer2());


            //Send next question




            // Repeatedly get commands from the client and process them.
            while (true) {
                String command = input.readLine();
                if (command.startsWith("MOVE")) {
                    int location = Integer.parseInt(command.substring(5));


                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
