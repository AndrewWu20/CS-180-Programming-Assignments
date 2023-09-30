import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String [] args) {

        try {

            Socket socket = new Socket("localhost", 1239);
            //writes to server
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //reads from server
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //reads object from server
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            Menu m = new Menu(pw, br, ois, oos);
            m.run();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't connect to server or server is down",
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
