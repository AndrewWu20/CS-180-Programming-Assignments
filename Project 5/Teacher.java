import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Account{

    private ArrayList<Course> courses = new ArrayList<Course>();
    Scanner s = new Scanner(System.in);


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
            PrintWriter pw = new PrintWriter(new FileWriter(new File("#" + a.getUserName() + ".txt"), true));
            //know what tags to choose when writing to main.txt
            pw.println(courseName + ".txt");
            writeFile("$" + courseName + ".txt", "");
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
        a.login();
        createCourse(a);
    }

}
