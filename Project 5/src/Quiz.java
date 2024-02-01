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

    public void addQuestion(int i) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the message for question " + (i + 1));
        String questionMessage = scan.nextLine();
        System.out.println("How many choices does this question have?");
        int numChoices = scan.nextInt();
        scan.nextLine();
        Question thisQuestion = new Question(questionMessage);
        for (int j = 0; j < numChoices; j++) {
            thisQuestion.addChoice(j);
        }
        questionList.add(thisQuestion);
    }

    public void editQuestion(int questionToEdit) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1.Replace question message\n2.Edit choice");
        int editChoice = scan.nextInt();
        scan.nextLine();
        if (editChoice == 1) {
            System.out.println("Enter new question message");
            questionList.get(questionToEdit).setQuestionMessage(scan.nextLine());
        } else if (editChoice == 2) {
            questionList.get(questionToEdit).editChoice();
        } else {
            System.out.println("Invalid input");
        }
    }

    public void deleteQuestion(int questionToDelete) { //deletes a question after creating a new quiz
        questionList.remove(questionToDelete - 1);
    }

    public void exportQuizToFile(String name) {
        String filename = name + ".txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) { //writing the copied file to a new file name QuizName
            for (Question question : questionList) {
                pw.write("@" + question.getQuestionMessage() + "\n"); //should print @ question message
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


    public void importQuiz(String filepath, String quizName) { //gonna assume the teacher reads in the file in the @~ format
        ArrayList<String> copyFile = new ArrayList<>();
        Path path = Paths.get(filepath);
        try (BufferedReader bfr = Files.newBufferedReader(path)) {
            String line;
            while ((line = bfr.readLine()) != null) {

                copyFile.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(quizName + ".txt"))) { //writing the copied file to a new file name QuizName
            for (int i = 0; i < copyFile.size(); i++) {
                pw.println(copyFile.get(i));
            }
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Quiz readQuiz(String quizFileName, String quizName) {
        ArrayList<Question> questions = new ArrayList<Question>();
        String questionMessage;
        Quiz quiz = new Quiz(quizName, questions); //should update questions with code below?
        int i = 0;
        try (BufferedReader bfr = new BufferedReader(new FileReader(quizFileName))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.charAt(0) == '@') {
                    questionMessage = String.format("%s", line.substring(1));
                    Question question = new Question(questionMessage);
                    questions.add(question);
                    i++;
                } else if (line.charAt(0) == '~') {
                    questions.get(i - 1).addChoice(String.format("%s", line.substring(1))); //adds the choice message
                }
            }
            this.quizName = quizName; //set these fields for the toString() method
            this.questionList = questions;
        } catch (IOException e) {
            System.out.println("INVALID FILE! Exiting the program...");
            e.printStackTrace();
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

    public void editQuestion() {
    }
}