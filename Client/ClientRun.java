package Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientRun {

    public static void main(String[] args) {
        try {
            (new Thread(new Client(51515, "localhost"))).start();
        } catch (Exception e) {
            main(null);
        }
    }
}
class Client extends Thread {

    BufferedReader inFromUser;
    BufferedReader inFromServer;

    Socket socket;

    DataOutputStream outToServer;

    public Client(int PORT, String IP) throws IOException {
        socket = new Socket(IP, PORT);
        inFromUser = new BufferedReader(new InputStreamReader(System.in));
        inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outToServer = new DataOutputStream(socket.getOutputStream());
        this.start();
    }

    public void run() {
        while(true) {
            try {
                System.out.println(inFromServer.readLine());
            } catch (Exception e) {

            }
        }
    }

    public void close() throws IOException {
        socket.close();
    }

    public void send(String Message) throws IOException {
        outToServer.writeBytes(Message + "\n");
        outToServer.flush();
    }

}

