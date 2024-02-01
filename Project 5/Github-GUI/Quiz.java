import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Quiz {
    private String quizName;
    private ArrayList<Question> questionList;

    //field for recording a student's quiz choices in takeQuiz() method
    private ArrayList<String> studentQuizChoices;

    //field for recording all of the submissions for the instance of THIS quiz
    private ArrayList<String> submissionList;

    public Quiz(String quizName, ArrayList<Question> quiz) {
        this.quizName = quizName;
        this.questionList = quiz;
        this.studentQuizChoices = new ArrayList<String>();
        this.submissionList = new ArrayList<String>();
    }

    public Quiz(String quizName) {
        //blank constructor to call methods of quiz in the menu class?
        this.quizName = quizName;
        this.questionList = new ArrayList<Question>();
        this.studentQuizChoices = new ArrayList<String>();
        this.submissionList = new ArrayList<String>();
    }

    public Quiz() {
        this.quizName = "";
        this.questionList = new ArrayList<Question>();
        this.studentQuizChoices = new ArrayList<String>();
        this.submissionList = new ArrayList<String>();
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String name) {
        this.quizName = name;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    public void addQuestion(int i) { //TODO: edited this with exception handling

        String questionMessage = JOptionPane.showInputDialog(null,
                "Please enter the message for question " + (i + 1), "Add a Question",
                JOptionPane.QUESTION_MESSAGE);

        String numChoices = JOptionPane.showInputDialog(null,
                "\"How many choices does this question have?", "Add a Question",
                JOptionPane.QUESTION_MESSAGE);
        boolean cont1 = true;
        int actualNum = 0;
        do {
            try {
                actualNum = Integer.parseInt(numChoices);
                cont1 = false;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer input",
                        "Error", JOptionPane.ERROR_MESSAGE);
                numChoices = JOptionPane.showInputDialog(null,
                        "\"How many choices does this question have?", "Add a Question",
                        JOptionPane.QUESTION_MESSAGE);
            }
        } while (cont1);

        String points = JOptionPane.showInputDialog(null,
                "How many points is this question worth?", "Add a Question",
                JOptionPane.QUESTION_MESSAGE);

        boolean cont = true;
        double pointValue = 0;
        do {
            try {
                pointValue = Double.parseDouble(points);
                cont = false;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid double input",
                        "Error", JOptionPane.ERROR_MESSAGE);
                points = JOptionPane.showInputDialog(null,
                        "How many points is this question worth?", "Add a Question",
                        JOptionPane.QUESTION_MESSAGE);
            }
        } while (cont);
        Question thisQuestion = new Question(questionMessage, pointValue);
        for (int j = 0; j < actualNum; j++) {
            thisQuestion.addChoice(j);
        }
        questionList.add(thisQuestion);
    }

    public void editQuestion() {

        int questionToEdit = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Which question would you like to edit? 1, 2, etc.", "Edit a Question",
                JOptionPane.QUESTION_MESSAGE));

        JFrame frame = new JFrame("Edit a Question");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("What would you like to do today?");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new GridLayout(3, 1));
        JButton replaceQuestionButton = new JButton("Replace question message");
        JButton editChoiceButton = new JButton("Edit choice");
        JButton editPointsButton = new JButton("Edit point value");
        panel2.add(replaceQuestionButton);
        panel2.add(editPointsButton);
        panel2.add(editChoiceButton);
        content.add(panel2);

        replaceQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newQuestion = JOptionPane.showInputDialog(null,
                        "How many points is this question worth?", "Add a Question",
                        JOptionPane.QUESTION_MESSAGE);
                questionList.get(questionToEdit - 1).setQuestionMessage(newQuestion);

            }
        });

        editChoiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                questionList.get(questionToEdit - 1).editChoice();

            }
        });

        editPointsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String points = JOptionPane.showInputDialog(null,
                        "Enter new point value", "Add a Question", JOptionPane.QUESTION_MESSAGE);
                boolean cont = true;
                double pointValue = 0;
                do {
                    try {
                        pointValue = Double.parseDouble(points);
                        cont = false;
                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid double input",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        points = JOptionPane.showInputDialog(null,
                                "How many points is this question worth?", "Add a Question",
                                JOptionPane.QUESTION_MESSAGE);
                    }
                } while (cont);
                questionList.get(questionToEdit - 1).setPoints(pointValue);

            }
        });
    }

    public void deleteQuestion(int questionToDelete) { //deletes a question after creating a new quiz
        questionList.remove(questionToDelete - 1);
    }

    public void exportQuizToFile(String name) {
        String filename = name + ".txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) { //writing the copied file to a new file name QuizName
            for (Question question : questionList) {
                pw.write("@" + question.getQuestionMessage() + "\t" + question.getPoints() + "\n"); //should print @ question message
                for (String choice : question.getChoiceList()) {
                    pw.write("~" + choice + "\n"); //should print ~ choice
                }
            }
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quizName + "\n");
        for (int i = 0; i < questionList.size(); i++) {
            sb.append(String.valueOf(i + 1) + "Q: " + questionList.get(i) + "\n");
        }
        return sb.toString();
    }


    public boolean importQuiz(String filepath, String quizName) { //gonna assume the teacher reads in the file in the @~ format
        ArrayList<String> copyFile = new ArrayList<>();
        boolean paths = true;
        try {
            Path path = Paths.get(filepath);
            BufferedReader bfr = Files.newBufferedReader(path);
            String line;
            while ((line = bfr.readLine()) != null) {
                copyFile.add(line);
            }
            bfr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found! Unable to import file!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            paths = false;
        }
        if (paths) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(quizName + ".txt"))) {
                for (int i = 0; i < copyFile.size(); i++) {
                    pw.println(copyFile.get(i));
                }
                pw.flush();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "File not copied!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return paths;
    }


    public Quiz readQuiz(String quizFileName, String quizName) {
        ArrayList<Question> questions = new ArrayList<Question>();
        String questionMessage;
        double points = 0;
        Quiz quiz = new Quiz(quizName, questions); //should update questions with code below?
        int i = 0;
        try (BufferedReader bfr = new BufferedReader(new FileReader(quizFileName))) {
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
            this.quizName = quizName; //set these fields for the toString() method
            this.questionList = questions;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Quiz not found please enter a valid quiz file!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return quiz; //feed this into shuffle quiz to create a copy
    }

    public void shuffleQuiz(Quiz quiz) {
        Random random = new Random();
        //don't need to clone since only student will login, teacher will still get original quiz by calling readQuiz method
        questionList = quiz.getQuestionList();
        for (int i = questionList.size() - 1; i >= 1; i--) {
            //swap current index value with random index value
            //Collections.swap - documentation swap(List list, int i, int j)- i is current index, j is random element to be swapped with
            Collections.swap(questionList, i, random.nextInt(i + 1)); // nextInt(int bound) -> returns a pseudorandom distributed between 0 and bound
        }
        //should now have a randomized list of questions
        for (Question question : questionList) {
            // add a get and set choiceList in the questions class
            for (int i = question.getChoiceList().size() - 1; i >= 1; i--) {
                Collections.swap(question.getChoiceList(), i, random.nextInt(i + 1)); //can't tell if this will swap the choices correctly
            }
        }

    }

    public void takeQuiz(String username, int attempt) { //should now have the questionList field randomized
        studentQuizChoices.clear();
        Scanner scan = new Scanner(System.in);
        for (Question question : questionList) {
            System.out.println(question.toString());
            studentQuizChoices.add(question.getQuestionMessage());
            studentQuizChoices.add(question.getPoints() + "");
            studentQuizChoices.add(scan.nextLine());
        }
        String submissiontxt = username + "_" + quizName + "_" + attempt + ".smb.txt";
        try {
            BufferedWriter bfr = new BufferedWriter(new FileWriter(submissiontxt));
            for (String choice : studentQuizChoices) {
                bfr.write(choice + "\n");
            } //TODO: adding .sub file to a common quiz file with -time stamp
            bfr.write("\n");
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you, " + username + ", your submission has been recorded");
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            BufferedWriter bfr = new BufferedWriter(new FileWriter(quizName + "_attempts.txt", true));
            bfr.write(submissiontxt + " : " + formatter.format(ts) + "\n");
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }

}
