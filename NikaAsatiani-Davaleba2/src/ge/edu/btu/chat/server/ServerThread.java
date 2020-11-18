package ge.edu.btu.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private  int privateId;
    private Socket client;


    public ServerThread(Socket client, int privateId) {
        this.client = client;
        this.privateId = privateId;
    }

    ObjectOutputStream objectOutput = null;

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());
                String str = (String) objectInput.readObject();

                String ans = " ";

                switch (str) {
                    case "hello":
                        ans = "Hello, thanks for choosing us, we are here to help you.";
                        break;
                    case "what is national currency?":
                        ans = "$3.5";
                        break;
                    case "show me addresses":
                        ans = "Address is : 82 ilia chavchavadze avenue";
                        break;
                    case "bye":
                        ans = "Bye, have a nice day.";
                        break;
                    default:
                        ans = "Sorry, i am not that smart to answer your question.";
                }
                System.out.println(ans);
                objectOutput = new ObjectOutputStream(client.getOutputStream());
                objectOutput.writeObject(ans);
            } catch (Exception e) {
                System.out.println("Exception : " + e);
            }
        }
    }
}