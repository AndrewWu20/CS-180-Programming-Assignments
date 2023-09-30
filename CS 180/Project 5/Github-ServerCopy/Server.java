import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Server {

    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<Double> scores = new ArrayList<Double>();


    public static void main(String[] args) {

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
        }
    }

    private void readClient() {

    }

}
