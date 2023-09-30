//notes for future development
/*

Store port number in account file or generate new one with every connection?
Objects will be created on client side and sent to server to be added/updated

*/

import org.jetbrains.annotations.NotNull;

import java.lang.module.ModuleDescriptor;
import java.nio.Buffer;
import java.util.Random;
import java.io.*;
import java.util.ArrayList;
import java.net.*;

public class Server {


    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<Double> scores = new ArrayList<Double>();

    //if we get a request from client, send the teacher/student object in stream along with everything else


    public static void main(String[] args) {
        Server server = new Server();
        server.init();
        int port = 0; //(for now)
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            server.writeData(writer);
            writer.flush();
            writer.close();
            server.readData(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void init() {

        try {
            BufferedReader mainReader = new BufferedReader(new FileReader("main.txt"));
            String line;
            while ((line = mainReader.readLine()) != null) {
                if (line.charAt(0) == '@') {
                    // read teacher logic
                    String teacherName = line.substring(0, line.length() - 4);
                    BufferedReader teacherReader = new BufferedReader(new FileReader(String.format("%s.txt", teacherName)));
                    String teacherLine;
                    while ((teacherLine = teacherReader.readLine()) != null) {
                        if (teacherLine.charAt(0) == '$') {
                            //read course logic
                            String courseName = teacherLine.substring(1, teacherLine.length() - 4);
                            Course currCourse = new Course(courseName);
                            courseList.add(currCourse);
                            BufferedReader courseReader = new BufferedReader(new FileReader(String.format("$%s.txt", courseName)));
                            String courseLine;
                            while ((courseLine = courseReader.readLine()) != null && !courseLine.equals("")) {
                                //to the current course, add the quiz that's read in by the filename given in the line
                                currCourse.addQuiz(currCourse.readQuiz(courseLine.substring(0, courseLine.length() - 4)));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Please ensure there is a main.txt file prior to running the program.");
        }
    }

    //writes all contents after initializing main to outputstream to be read by client
    //so that client may instantiate all objects to be manipulated and returned to server
    //for updating storage (this constitutes one run of client)
    public void writeData(@NotNull PrintWriter writer) {
        for (Course course : courseList) {
            String courseName = course.getCourseName();
            writer.println("?" + courseName);
            for (Quiz quiz : course.getQuizList()) {
                writer.println("%" + quiz.toString());
            }
        }

    }

    //will be used on client side to read data in stream from server
    public ArrayList<Course> readData(@NotNull BufferedReader reader) throws IOException {
        ArrayList<Course> courseList = new ArrayList<Course>();
        String line;
        int courseCounter = 0;
        int quizCounter = 0;
        int questionCounter = 0;
        while ((line = reader.readLine()) != null) {
            if (line.charAt(0) == '?') {
                Course course = new Course(line.substring(1));
                courseCounter++;
                quizCounter = 0;
                courseList.add(course);
            } else if (line.charAt(0) == '%') {
                Quiz currQuiz = new Quiz(line.substring(1));
                courseList.get(courseCounter - 1).getQuizList().add(currQuiz);
                quizCounter++;
                questionCounter = 0;
            } else if (line.charAt(0) == '@') {
                courseList.get(courseCounter - 1).getQuizList().get(quizCounter - 1).getQuestionList().add(new Question(line.substring(1), Double.parseDouble(line.substring(line.indexOf("\t") + 1))));
                questionCounter++;
            } else if (line.charAt(0) == '~') {
                courseList.get(courseCounter - 1).getQuizList().get(quizCounter - 1).getQuestionList().get(questionCounter - 1).getChoiceList().add(line.substring(1));
                //case for why OOP is bad^
            }
        }
        return courseList;
    }
}
