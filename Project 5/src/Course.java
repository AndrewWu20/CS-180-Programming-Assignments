import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Course {
    private String courseName;
    private ArrayList<Quiz> quizList;

    public Course(String courseName) {
        this.courseName = courseName;
        this.quizList = new ArrayList<Quiz>();
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }



    public Quiz readQuiz(String quizName) {
        ArrayList<Question> questions = new ArrayList<Question>();
        String questionMessage;
        Quiz quiz = new Quiz(quizName, questions); //should update questions with code below?
        int i = 0;
        try (BufferedReader bfr = new BufferedReader(new FileReader(String.format("%s.txt", quizName)))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.charAt(0) == '@') {
                    questionMessage = String.format("%s", line.substring(1));
                    Question question = new Question(questionMessage);
                    questions.add(question);
                    i++;
                } else if (line.charAt(0) == '~') {
                    questions.get(i-1).addChoice(String.format("%s", line.substring(1))); //adds the choice message
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
            PrintWriter writer = new PrintWriter(String.format("%s.txt", courseName), "UTF-8");
            writer.println(String.format("%s.txt", quiz.getQuizName()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void editQuiz() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which quiz would you like to edit? 1, 2, etc.");
        int quizChoice = scan.nextInt();
        scan.nextLine();
        Quiz quizToEdit = quizList.get(quizChoice);
        System.out.println("1. Edit quiz name\n2. Replace/Edit a question\n3. Delete a question");
        int editChoice = scan.nextInt();
        scan.nextLine();
        if (editChoice == 1) {
            System.out.println("Enter the new quiz name");
            quizToEdit.setQuizName(scan.nextLine());
        } else if (editChoice == 2) {
            quizToEdit.editQuestion();
        } else if (editChoice == 3) {
            System.out.println("Which question do you want to delete?");
            int questionToDelete = scan.nextInt();
            scan.nextLine();
            quizToEdit.deleteQuestion(questionToDelete);
        }
    }

    public void deleteQuiz(Quiz quiz) {
        String quizName = quiz.getQuizName();
        quizList.remove(quiz);
        try {
            ArrayList<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new FileReader(String.format("%s.txt", courseName)));
            PrintWriter writer = new PrintWriter(String.format("%s.txt", courseName));
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
            System.out.println("There was an error editing " + courseName + ".txt");
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
}
