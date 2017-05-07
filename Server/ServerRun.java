package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRun {

    public static void main(String[] args) {
        try {
            Server server = new Server(51515, "localhost");
            server.send("1");
            server.send("2");
            server.send("3");
        } catch (Exception e) {
            main(null);
        }
    }
}
class Server implements Runnable {

    ServerSocket serverSocket;
    Socket connectionSocket;

    BufferedReader in;
    BufferedWriter out;

    DataOutputStream outToServer;

    public Server(int PORT, String IP) throws IOException {
        serverSocket = new ServerSocket(PORT);
        connectionSocket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
        outToServer = new DataOutputStream(connectionSocket.getOutputStream());
    }

    public void run() {
        while(true) {
            try {
                System.out.println(in.readLine());
            } catch (Exception e) {

            }
        }
    }

    public void send(String Message) throws IOException {
        outToServer.writeBytes(Message + "\n");
        outToServer.flush();
    }

}

