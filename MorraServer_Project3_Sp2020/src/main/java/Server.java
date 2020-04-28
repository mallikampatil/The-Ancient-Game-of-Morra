import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

    int count = 0;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    private Consumer<MorraInfo> callback;
    int port;
    int numReceived;

    int p1Points, p2Points;
    int p1Plays, p2Plays;
    int p1Guess, p2Guess;


    Server(Consumer<MorraInfo> call, int p){

        callback = call;
        port = p;
        server = new TheServer();
        server.start();

    }


    public class TheServer extends Thread{

        public void run() {

            try(ServerSocket mysocket = new ServerSocket(port);){

                while(true) {

                    count += 1;

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    // game mode being 0 meaning player 1 has just joined the game
                    if (count == 1)
                        callback.accept(new MorraInfo(0, -1, -1, -1, -1, -1, -1, false, false));
                    // game mode being 1 meaning player 2 has just joined the game
                    else if (count == 2)
                        callback.accept(new MorraInfo(1, -1, -1, -1, -1, -1, -1, false, false));

                    clients.add(c);
                    c.start();

                }
            }//end of try
            catch(Exception e) {
                // game mode being -999 indicates connection error
                callback.accept(new MorraInfo(-999, -1, -1, -1, -1, -1, -1, false, false));
            }
        }//end of while
    }


    class ClientThread extends Thread {

        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        public void updateClients(MorraInfo data) {

            if (clients.size() == 1) {

                ClientThread t1 = clients.get(0);
                try {
                    data.setP1(true);
                    data.setP2(false);
                    t1.out.writeObject(data);
                } catch (Exception e) {}

            }
            else if (clients.size() == 2) {

                ClientThread t1 = clients.get(0);
                try {
                    data.setP1(true);
                    data.setP2(false);
                    t1.out.writeObject(data);
                } catch (Exception e) {
                }
                ClientThread t2 = clients.get(1);
                try {
                    data.setP1(false);
                    data.setP2(true);
                    t2.out.writeObject(data);
                } catch (Exception e) {}

            }

        }

        public void run(){

            try {

                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);

            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

            // game mode being 2 meaning the server is determining which each client are, p1 or p2
            updateClients(new MorraInfo(2, -1, -1, -1, -1, -1, -1, false, false));

            numReceived = 0;

            while(true) {
                try {

                    MorraInfo data = (MorraInfo)in.readObject();
                    // if game mode of the in stream is 3, that means the client just sent something to the server
                    if (data.gameMode == 3) {

                        numReceived += 1;

                        if (data.p1 == true) {
                            p1Plays = data.p1Plays;
                            p1Guess = data.p1Guess;
                            p1Points = data.p1Points;
                        } else {
                            p2Plays = data.p2Plays;
                            p2Guess = data.p2Guess;
                            p2Points = data.p2Points;
                        }

                        if (numReceived == 2) {

                            evaluate();
                            // game mode being 3 on server side meaning server has the final result and is pushing it to GUI
                            callback.accept(new MorraInfo(3, p1Points, p2Points, p1Plays, p2Plays, p1Guess, p2Guess, false, false));
                            // game mode being 3 on client side meaning server is sending the final result to clients
                            updateClients(new MorraInfo(3, p1Points, p2Points, p1Plays, p2Plays, p1Guess, p2Guess, false, false));

                        }

                    }

                }
                catch(Exception e) {

                    // game mode being -999 indicates connection error
                    callback.accept(new MorraInfo(-999, -1, -1, -1, -1, -1, -1, false, false));
                    updateClients(new MorraInfo(-999, -1, -1, -1, -1, -1, -1, false, false));
                    clients.remove(this);
                    break;

                }
            }
        }//end of run

        private void evaluate() {

            int actualTotal = p1Plays + p2Plays;
            boolean p1Correct = false, p2Correct = false;

            if (p1Guess == actualTotal)
                p1Correct = true;
            if (p2Guess == actualTotal)
                p2Correct = true;

            if (p1Correct == true && p2Correct == false)
                p1Points += 1;
            else if (p1Correct == false && p2Correct == true)
                p2Points += 1;

        }


    }//end of client thread
}