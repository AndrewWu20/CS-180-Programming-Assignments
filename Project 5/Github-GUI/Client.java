
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String [] args) {

        try {

            Socket socket = new Socket("localhost", 1239);
            //writes to server
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //reads from server
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Menu m = new Menu(pw, br);
            m.run();


        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't connect to server or server is down",
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
