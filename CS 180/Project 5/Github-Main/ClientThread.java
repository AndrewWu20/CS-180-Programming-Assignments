import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread implements Runnable {

    private Socket clientSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Account a;
    private JFrame frame;
    Teacher t;
    CourseData courseData;

    public ClientThread(Socket client) throws IOException {

        this.clientSocket = client;
        frame = new JFrame("Quiz39");
        a = new Account();
        t = new Teacher();
        courseData = new CourseData();
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ois = new ObjectInputStream(clientSocket.getInputStream());

    }

    public void run() {

        PrintWriter pw = null;
        BufferedReader br = null;
        init();

        try {
            //sends output to client
            pw = new PrintWriter(clientSocket.getOutputStream());
            //gets input from client
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line = br.readLine();
            boolean loop = true;
            while (loop) {
                synchronized (this) {
                    if (line.startsWith("createAccount")) {
                        pw.println(a.createAccount(line.split("\t.")[1], line.split("\t.")[2], line.split("\t.")[3]));
                        pw.flush();
                    } else if (line.startsWith("logIn")) {
                        pw.println(a.login(line.split("\t.")[1], line.split("\t.")[2], line.split("\t.")[3]));
                        pw.flush();
                    } else if (line.startsWith("getCourseNames")) {
                        courseData.init();
                        oos.writeObject(courseData.getCourseNames());
                    } else if (line.startsWith("changePassword")) {
                        pw.println(a.changePassword(Integer.parseInt(line.split("\t.")[1]), line.split("\t.")[2]));
                        pw.flush();
                    } else if (line.startsWith("deleteAccount")) {
                        a.deleteAccount(Integer.parseInt(line.split("\t.")[1]));
                    } else if (line.startsWith("createCourse")) {
                        pw.println(t.createCourse(a, line.split("\t.")[1]));
                        pw.flush();
                    } else if (line.startsWith("changeUserName")) {
                        pw.println(a.changeUserName(Integer.parseInt(line.split("\t.")[1]), line.split("\t.")[2]));
                        pw.flush();
                    } else if (line.startsWith("exit")) {
                        loop = false;
                    } else if (line.startsWith("getQuizList")) {
                        courseData.init();
                        oos.writeObject(courseData.getQuizList(line.split("\t.")[1]));
                    } else if (line.startsWith("getQuizNames")) {
                        courseData.init();
                        oos.writeObject(courseData.getQuizNames(line.split("\t.")[1]));
                    } else if (line.startsWith("editCourseName")) {
                        String newCourseName = line.split("\t.")[1];
                        t.getCourses().get(t.checkCourseExistence(newCourseName)).setCourseName(newCourseName);
                    } else if (line.startsWith("importQuiz")) {
                        Quiz q = new Quiz();
                        q.importQuiz(line.split("\t.")[1], line.split("\t.")[2]);
                    }
                    if (loop)
                        line = br.readLine();
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

    private void init() {

    }

}
