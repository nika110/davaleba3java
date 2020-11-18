package ge.edu.btu.chat.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        ObjectOutputStream objectOutput = null;
        Socket somesocket;
        boolean startIt = true;

        String stopbot = "bye";
        try {
            somesocket = new Socket("localhost", 8080);
            System.out.println("Connection established to - localhost:8080");

            Scanner someObj = new Scanner(System.in);

            while (startIt) {
                System.out.println("Question : ");
                String question = someObj.nextLine();
                objectOutput = new ObjectOutputStream(somesocket.getOutputStream());
                objectOutput.writeObject(question);

                ObjectInputStream objectInput = new ObjectInputStream(somesocket.getInputStream());
                String str = (String) objectInput.readObject();
                System.out.println(str);

                if(question.equals(stopbot)){
                    startIt = false;
                }
            }
            objectOutput.close();
            somesocket.close();
            System.out.println("Connection Closed");

        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }
}