import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Account {

    private ArrayList<Course> courses = new ArrayList<Course>();
    Scanner s = new Scanner(System.in);

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean createCourse(Account a, String courseName) {
        if (checkCourseExistence(courseName) != -1) {
            JOptionPane.showMessageDialog(null, "Course " + courseName + " " +
                            "already exists.\nPlease choose a unique course name.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        courses.add(new Course(courseName));

        try {
            System.out.println(a.globalUsername);
            PrintWriter pw = new PrintWriter(new FileWriter(new File("@" + a.globalUsername + ".txt"), true));
            //know what tags to choose when writing to main.txt
            pw.println("$" + courseName + ".txt");
            writeFile("$" + courseName + ".txt", "");
            JOptionPane.showMessageDialog(null, "Successfully created Course " + courseName,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int checkCourseExistence(String courseName) {
        int i = 0;
        for (Course e : courses) {
            if (e.getCourseName().equals(courseName))
                return i;
            i++;
        }
        return -1;
    }

    public int printSubmissions(ArrayList<String> submissions, int index, String studentName) {
        for (int i = index; !submissions.get(i).split("_")[0].equals(studentName); i++) {
            System.out.println(submissions.get(i).split("_")[2]);
            index++;
        }
        return index;
    }

    public ArrayList<String> parseSubmissions(int courseIndex, String quizName) {
        ArrayList<String> submissions = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(quizName + "_attempts.txt"));
            String line = br.readLine();
            while (line != null) {
                submissions.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return submissions;
    }

    public int checkStudentSubmission(ArrayList<String> submissions, String studentName) {
        for (int i = 0; i < submissions.size(); i++) {
            if (submissions.get(i).split("_")[0].equals(studentName))
                return i;
        }
        return -1;
    }
}
