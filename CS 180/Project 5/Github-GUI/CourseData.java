import java.io.*;
import java.util.*;

public class CourseData {

    private ArrayList<Course> courseList;

    public CourseData() {
        this.courseList = new ArrayList<Course>();
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

        }
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<String> getCourseNames() {
        ArrayList<String> courseNames = new ArrayList<String>();
        for (Course course: courseList) {
            courseNames.add(course.getCourseName());
        }
        return courseNames;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public Course getLastCourse() {
        return courseList.get(courseList.toArray().length - 1);
    }

    public void eraseData() {
        courseList.clear();
    }
}
