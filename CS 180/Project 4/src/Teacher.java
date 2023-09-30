import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Account{

    private ArrayList<Course> courses = new ArrayList<Course>();
    Scanner s = new Scanner(System.in);


    public ArrayList<Course> getCourses() {
        return courses;
    }


    public void createCourse(Account a) {
        System.out.println("What name would you like to give this Course?");
        String courseName = "";
        int i = 0;
        boolean cont = true;
        courseName = s.nextLine();

        while (checkCourseExistence(courseName) != -1) {
            System.out.println("Course " + courseName + " already exists.\nPlease choose a unique course name.");
            courseName = s.nextLine();
        }
        courses.add(new Course(courseName));
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(  "@" + a.getUserName() + ".txt"), true));
            //know what tags to choose when writing to main.txt
            pw.println("$" + courseName + ".txt");
            writeFile(  "$" + courseName + ".txt", "");
            System.out.println("Successfully created Course " + courseName);
            boolean notValid = false;
            do {
                System.out.println("Would you like to create another course?\n1. Yes\n2. No");
                String choice = s.nextLine();
                if (choice.equals("1"))
                    createCourse(a);
                else if (choice.equals("2")) {
                    pw.close();
                    return;
                }
                else {
                    System.out.println("Please enter a valid input");
                    notValid = true;
                }
            } while (notValid);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCourse() {
        System.out.println("Which course would you like to edit?");
        //gets the index of the course the user wants to edit
        int courseIndex = checkCourseExistence(s.nextLine());
        if (courseIndex != -1) { // if the course exists then do the following
            System.out.println("Please choose how to edit this course:\n1. Create Quiz\n2. View Quiz");
            boolean cont = false; //continues while user enters bad input.
            do {
                String choice = s.nextLine();
                boolean createCont = false;
                if (choice.equals("1")) {
                    System.out.println("Would you like to:\n1. Import Quiz\n2. Create Quiz through terminal");
                    String quizCreationChoice = "";
                    do {
                        quizCreationChoice = s.nextLine();
                        if (quizCreationChoice.equals("1")) {
                            //ALREADY DONE
                        } else if (quizCreationChoice.equals("2")) {
                            //ALREADY DONE
                        } else {
                            System.out.println("Please enter a valid option.");
                            createCont = true;
                        }
                    } while (createCont);
                }
                //If the choice is 2, then we need to view a quiz
                //Starts by selecting which quiz to view
                else if (choice.equals("2")) {
                    System.out.println("Please select a quiz to view.");
                    String quizName = s.nextLine(); //sets quiz name as per user input
                    Quiz q = courses.get(courseIndex).getQuiz(quizName);
                    while (q == null) { //loops through until valid quiz name is entered
                        System.out.println("Please enter a valid quiz name.");
                        quizName = s.nextLine();
                        q = courses.get(courseIndex).getQuiz(quizName);
                    }
                    System.out.println("1. Delete Quiz\n2. Edit Quiz\n3. View Quiz Scores");
                    boolean contQuizChoice = false;
                    do { //loops until valid input
                        String quizChoice = s.nextLine();
                        if (quizChoice.equals("1")) {
                            courses.get(courseIndex).deleteQuiz(q);
                        } else if (quizChoice.equals("2")) {
                            courses.get(courseIndex).editQuiz();
                        } else if (quizChoice.equals("3")) {
                            //TODO: view quiz scores
                        } else {
                            System.out.println("Please select a valid option.");
                            contQuizChoice = true;
                        }
                    } while (contQuizChoice);
                } else {
                    System.out.println("Please enter a valid input.");
                    cont = true;
                }
            } while (cont);
        } else {
            System.out.println("Please enter a valid course name that already exists.");
            editCourse();
        }
    }

    public void viewStudent() {
        System.out.println("Please enter a student username.\n");
        String studentName = s.nextLine();
        //TODO: view student scores
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
        courses.get(0).getQuiz("Quiz1").takeQuiz(s.getUserName(), 1);
        gradeQuiz();
    }

    public void gradeQuiz() {
        Scanner s = new Scanner(System.in);
        System.out.println("Which course would you like to see?");
        String courseName = s.nextLine();
        while (checkCourseExistence(courseName) == -1) {
            System.out.println("Please enter a valid course.");
            courseName = s.nextLine();
        }

        System.out.println("Which quiz would you like to grade?");
        String quizName = s.nextLine();
        while (courses.get(checkCourseExistence(courseName)).getQuiz(quizName) != null) {
            System.out.println("Please enter a valid quiz.");
            quizName = s.nextLine();
        }

        ArrayList<String> submissions = parseSubmissions(checkCourseExistence(courseName), quizName);

        System.out.println("Which student would you like to grade?");
        String studentName = s.nextLine();
        while (checkStudentSubmission(submissions, studentName) == -1) {
            System.out.println("Please enter a valid student name who has taken the quiz");
            studentName = s.nextLine();
        }

        System.out.println("Which submission would you like to grade?");
        int totalAttempts = printSubmissions(submissions, checkStudentSubmission(submissions, studentName), studentName);
        for (int i = 1; i <= totalAttempts; i++) {
            System.out.println(i);
        }
        String attemptNumber = s.nextLine();
        int attempt = 0;
        boolean cont = true;
        do {
            try {
                attempt = Integer.parseInt(attemptNumber);
                cont = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid attempt number");
                cont = true;
            }
        } while (cont);
        try {
            BufferedReader br = new BufferedReader(new FileReader(studentName + "_" + quizName + "_" + attempt + ".smb.txt"));
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
                System.out.println(line);

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(studentName + "\n" + quizName + "\n" + attempt + "\n");
            e.printStackTrace();
        }
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