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

    public void createCourse(Account a) {
        String courseName = JOptionPane.showInputDialog(null,
                "What name would you like to give this Course?", "Add a Question",
                JOptionPane.QUESTION_MESSAGE);

        while (checkCourseExistence(courseName) != -1) {
            JOptionPane.showMessageDialog(null, "Course " + courseName + " " +
                            "already exists.\nPlease choose a unique course name.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            courseName = JOptionPane.showInputDialog(null,
                    "What name would you like to give this Course?", "Add a Question",
                    JOptionPane.QUESTION_MESSAGE);
        }
        courses.add(new Course(courseName));

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File("@" + Account.globalUsername + ".txt"), true));
            //know what tags to choose when writing to main.txt
            pw.println("$" + courseName + ".txt");
            writeFile("$" + courseName + ".txt", "");
            JOptionPane.showMessageDialog(null, "Successfully created Course " + courseName,
                    "Success", JOptionPane.INFORMATION_MESSAGE);

                JFrame frame = new JFrame("Course");
                Container content = frame.getContentPane();
                content.setLayout(new BorderLayout());

                frame.setSize(300, 250);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                JPanel panel = new JPanel();
                JLabel initialQ = new JLabel("Would you like to create another course?");
                panel.add(initialQ);
                content.add(panel, BorderLayout.NORTH);

                JPanel panel2 = new JPanel();
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");
                panel2.add(yesButton);
                panel2.add(noButton);
                content.add(panel2);

                yesButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createCourse(a);
                    }
                });

                noButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int checkCourseExistence(String courseName) {
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

    public static void main(String[] args) throws IOException {
        Teacher t = new Teacher();
        t.run();
    }

    public void run() throws IOException {
        Account a = new Account();
        a.createAccount();
        a.login();
        createCourse(a);
        courses.get(0).addQuiz(new Quiz("Quiz1"));
        courses.get(0).getQuiz("Quiz1").addQuestion(0);
        courses.get(0).getQuiz("Quiz1").addQuestion(1);
        courses.get(0).getQuiz("Quiz1").addQuestion(2);
        courses.get(0).getQuiz("Quiz1").addQuestion(3);
        Account s = new Account();
        s.createAccount();
        s.login();
        courses.get(0).getQuiz("Quiz1").takeQuiz(Account.globalUsername, 1);
    }
}
