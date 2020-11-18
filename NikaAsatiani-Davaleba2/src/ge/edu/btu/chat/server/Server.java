package ge.edu.btu.chat.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public  static  void main(String[] args) {
        ServerSocket serverSocket;
        Socket somesocket = null;
        String str;
        int id = 0;
        boolean start= true;
        ServerThread serverThread;


        try {
            serverSocket = new ServerSocket(8080);

            while (start) {
                somesocket = serverSocket.accept();
                serverThread = new ServerThread(somesocket, id);
                serverThread.start();
                id++;
            }

        } catch (Exception e) {
            System.out.println("Exception:  " + e);
        }
    }
}