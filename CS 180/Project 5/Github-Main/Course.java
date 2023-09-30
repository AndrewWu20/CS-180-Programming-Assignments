import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Course class which reads, edits and deltes quizzes under a course.
 *
 * @author Zachary Garcia, lab sec L-10
 * @version April 11, 2022
 */

public class Course implements Serializable {
    private String courseName;
    private ArrayList<Quiz> quizList;

    public Course(String courseName) {
        this.courseName = courseName;
        this.quizList = new ArrayList<Quiz>();
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }

    public Quiz readQuiz(String quizName) {
        ArrayList<Question> questions = new ArrayList<Question>();
        String questionMessage;
        double points = 0;
        Quiz quiz = new Quiz(quizName, questions); //should update questions with code below?
        int i = 0;
        try (BufferedReader bfr = new BufferedReader(new FileReader(String.format("%s.txt", quizName)))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.charAt(0) == '@') {
                    questionMessage = String.format("%s", line.substring(1, line.indexOf("\t")));
                    points = Double.parseDouble(line.substring(line.indexOf("\t") + 1));
                    Question question = new Question(questionMessage, points);
                    questions.add(question);
                    i++;
                } else if (line.charAt(0) == '~') {
                    questions.get(i - 1).addChoice(String.format("%s", line.substring(1))); //adds the choice message
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quiz; //feed this into shuffle quiz to create a copy
    }

    public void addQuiz(Quiz quiz) {
        quizList.add(quiz);
        try {
            PrintWriter writer = new PrintWriter(String.format("$%s.txt", courseName), "UTF-8");
            writer.println(String.format("%s.txt", quiz.getQuizName()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void editQuiz() {

        JFrame frame = new JFrame("Menu");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("Which quiz would you like to edit? 1, 2, etc.");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        JButton editQuizButton = new JButton("Edit Quiz");
        JTextField quizNumberField = new JTextField();
        panel2.add(quizNumberField);
        panel2.add(editQuizButton);
        content.add(panel2);

        editQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Quiz quizToEdit = quizList.get(Integer.parseInt(quizNumberField.getText()));

                JFrame frame2 = new JFrame("Menu");
                Container content = frame2.getContentPane();
                content.setLayout(new BorderLayout());

                frame2.setSize(500, 500);
                frame2.setLocationRelativeTo(null);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel3 = new JPanel(new GridLayout(1, 3));
                JButton editQuizNameButton = new JButton("Edit Quiz Name");
                JButton replaceQuestionButton = new JButton("Replace/Edit a Question");
                JButton deleteQuestionButton = new JButton("Delete a Question");
                panel2.add(editQuizNameButton);
                panel2.add(replaceQuestionButton);
                panel2.add(deleteQuestionButton);
                content.add(panel3);

                frame2.setVisible(true);

                editQuizNameButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String newQuizName = JOptionPane.showInputDialog(null,
                                "Enter new Quiz Name", "Edit Quiz Name",
                                JOptionPane.QUESTION_MESSAGE);
                        quizToEdit.setQuizName(String.valueOf(newQuizName));
                        JOptionPane.showMessageDialog(null, "The quiz name was edited" +
                                        " successfully!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);

                    }
                });
                replaceQuestionButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        quizToEdit.editQuestion();
                    }
                });

                deleteQuestionButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int deleteQuestionNumber = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "Enter question to be deleted", "Delete Quiz Quiz",
                                JOptionPane.QUESTION_MESSAGE));
                        quizToEdit.deleteQuestion(deleteQuestionNumber);
                        JOptionPane.showMessageDialog(null, "The quiz was deleted" +
                                        " successfully!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                frame.setVisible(true);
            }
        });
    }

    public void deleteQuiz(Quiz quiz) {
        String quizName = quiz.getQuizName();
        this.quizList.remove(quiz);
        try {
            ArrayList<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new FileReader(String.format("$%s.txt", courseName)));
            PrintWriter writer = new PrintWriter(String.format("$%s.txt", courseName));
            String line = reader.readLine();
            while (line != null) {
                if (!line.equals(String.format("%s.txt", quiz.getQuizName()))) {
                    lines.add(line);
                }
                line = reader.readLine();
            }
            for (String thisLine : lines) {
                writer.write(thisLine);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error editing " + courseName + ".txt",
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(courseName + "\n");
        for (int i = 0; i < quizList.size(); i++) {
            sb.append(quizList.get(i).getQuizName() + "\n");
        }
        return sb.toString();
    }

    public Quiz getQuiz(String quizName) {
        for (Quiz e : quizList) {
            if (e.getQuizName().equals(quizName))
                return e;
        }
        return null;
    }

    public boolean hasQuizzes() {
        return quizList.size() != 0;
    }

    public void printQuizList() {
        for (Quiz q : quizList) {
            JOptionPane.showMessageDialog(null, q.getQuizName(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public ArrayList<String> getQuizNames() {
        ArrayList<String> quizNames = new ArrayList<String>();
        for (Quiz q : quizList) {
            quizNames.add(q.getQuizName());
            System.out.println(q.getQuizName());
        }
        return quizNames;
    }

}
