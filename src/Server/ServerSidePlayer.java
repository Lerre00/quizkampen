package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ServerSidePlayer extends Thread {
    char mark;
    ServerSidePlayer opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    ServerSideGame game;


    public ServerSidePlayer(Socket socket, char mark, ServerSideGame game) {
        this.socket = socket;
        this.mark = mark;
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


    public void run() {
        try {

            //Print question and alternatives

            output.println("Hur stor är?");
            output.println("Alternativ 1");
            output.println("Alternativ 2");
            output.println("Alternativ 3");
            output.println("Alternativ 4");

            //Recieve answer from player
            String answer = input.readLine();
            System.out.println(answer);

            //Look if answer was correct and give point if true



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
