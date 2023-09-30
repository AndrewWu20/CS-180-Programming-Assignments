import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientThread implements Runnable{

    private Socket clientSocket;
    private Account a;
    private JFrame frame;

    public ClientThread(Socket client) {
        this.clientSocket = client;
        frame = new JFrame("Quiz39");
        a = new Account();

    }

    public void run() {

        CourseData courseData = new CourseData();
        courseData.init();

        PrintWriter pw = null;
        BufferedReader br = null;

        try {
            //gets output from client
            pw = new PrintWriter(clientSocket.getOutputStream());
            //gets input from client
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line = br.readLine();
            System.out.println(line);
            while (true) {
                synchronized (this) {
                    if (line.startsWith("createAccount")) {
                        pw.println(a.createAccount(line.split(" ")[1], line.split(" ")[2], line.split(" ")[3]));
                        pw.flush();
                    } else if (line.startsWith("logIn")) {
                        pw.println(a.login(line.split(" ")[1], line.split(" ")[2], line.split(" ")[3]));
                        pw.flush();
                    } else if (line.startsWith("viewCourse")) {
                        ArrayList<String> courseNames = courseData.getCourseNames();
                        for (String courseName : courseNames) {
                            pw.println(courseName);
                        }
                        pw.flush();
                    } else if (line.startsWith("editAccount")) {
                        a.editAccount(2);
                    } else if (line.equals("deleteAccount")) {
                        a.deleteAccount(a.getUserName(), 2);
                    }
                    line = br.readLine();
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                br.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
