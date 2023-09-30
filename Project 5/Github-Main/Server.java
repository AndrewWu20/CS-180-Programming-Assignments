import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Server s = new Server();
        s.run();
    }

    private void run() {
        initFiles();
        openServer();
    }

    private void openServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1239);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientThread client = new ClientThread(clientSocket);
                new Thread(client).start();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't connect to server or server is down",
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Can't connect to server or server is down",
                        "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void initFiles() {
        File usernames = new File("usernames.txt");
        File main = new File("main.txt");
        try {
            usernames.createNewFile();
            main.createNewFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in creating file.",
                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initMain() {
        
    }


}
