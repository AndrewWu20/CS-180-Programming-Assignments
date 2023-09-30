import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseTest {
    public static void main(String[] args) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        try {
            BufferedReader mainReader = new BufferedReader(new FileReader("main.txt"));
            String line;
            while ((line = mainReader.readLine()) != null) {
                if (line.charAt(0) == '#') {
                    // read teacher logic
                    String teacherName = line.substring(1, line.length() - 4);
                    BufferedReader teacherReader = new BufferedReader(new FileReader(String.format("%s.txt", teacherName)));
                    String teacherLine;
                    while ((teacherLine = teacherReader.readLine()) != null) {
                        if (teacherLine.charAt(0) == '$') {
                            //read course logic
                            String courseName = teacherLine.substring(1, teacherLine.length() - 4);
                            Course currCourse = new Course(courseName);
                            courseList.add(currCourse);
                            BufferedReader courseReader = new BufferedReader(new FileReader(String.format("%s.txt", courseName)));
                            String courseLine;
                            while ((courseLine = courseReader.readLine()) != null) {
                                //to the current course, add the quiz that's read in by the filename given in the line
                                currCourse.addQuiz(currCourse.readQuiz(courseLine.substring(0, courseLine.length() - 4)));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(courseList);
        System.out.println(courseList.get(0).getQuizList());

    }
}
