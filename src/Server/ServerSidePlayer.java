package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerSidePlayer extends Thread {
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
        //Sending playerNumber to clients
        output.println(playerNumber);


        //Print question and alternatives
        output.println(Question.getTestQuestion().getQuestion());

        output.println(Question.getTestQuestion().getWrongAlternative1());
        output.println(Question.getTestQuestion().getWrongAlternative2());
        output.println(Question.getTestQuestion().getWrongAlternative3());

        output.println(Question.getTestQuestion().getRightAlternative());

        //Correct answer
        String correctAnswer = Question.getTestQuestion().getRightAlternative();

        try {
            //Receive answer from player
            String playerNumberAndAnswerFromClient = input.readLine();


            //Look if answer was correct and give point if true
            String playerNumberFromClient = playerNumberAndAnswerFromClient.substring(0, 7);
            String answerFromClient = playerNumberAndAnswerFromClient.substring(7);
            if (playerNumberFromClient.equals("player1") && answerFromClient.equals(correctAnswer)) {
                setPointsPlayer1(getPointsPlayer1() + 1);
            }
            if (playerNumberFromClient.equals("player2") && answerFromClient.equals(correctAnswer)) {
                setPointsPlayer2(getPointsPlayer2() + 1);
            }

            //Send back client answer
            output.println(answerFromClient);
            System.out.println(pointsPlayer1 + pointsPlayer2);
            System.out.println(playerNumberFromClient + " answer: " + answerFromClient);


        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
