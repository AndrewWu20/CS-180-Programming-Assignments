import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner s = new Scanner(System.in);
    private Account a = new Account();
    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<Double> scores = new ArrayList<Double>();

    public static void main(String[] args) {
        Menu m = new Menu();
        JOptionPane.showMessageDialog(null, "Welcome to our Application!", "Welcome",
                JOptionPane.INFORMATION_MESSAGE);
        m.init();
        m.run();
        JOptionPane.showMessageDialog(null, "Goodbye!", "Goodbye",
                JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Please ensure there is a main.txt " +
                            "file prior to running the program.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void run() {

        System.out.println("Would you like to:\n1. Create an Account\n2. Sign in\n3. Exit");
        String userCASIOption = "";
        int userType = 0;
        do {
            if (userCASIOption == null)
                System.out.println("Would you like to:\n1. Create an Account\n2. Sign in\n3. Exit");
            userCASIOption = s.nextLine();
            if (userCASIOption.equals("1")) {
                createAccount();
                userCASIOption = null;
            } else if (userCASIOption.equals("2")) {
                try {
                    userType = a.login();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Please ensure you have a usernames.txt file and an account has been created." +
                                    "\nPlease enter a number from 1 - 2", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    userCASIOption = "-1";
                }
            } else if (userCASIOption.equals("3")) {
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a number from 1 - 3", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (userCASIOption == null || !userCASIOption.equals("2"));

        if (userType == 1) {
            if (!teacherFlow(a))
                run();
        } else if (userType == 2) {
            if (!studentFlow(a))
                run();
        }

    }

    private boolean teacherFlow(Account a) {

        Teacher t = new Teacher();
        String userResponse = "";
        boolean cont = true;
        do {
            System.out.println("\nWould you like to:\n1. Create a course\n2. View Course Quizzes\n" +
                    "3. Edit a course\n4. Grade Quiz\n5. View a student's scores\n" +
                    "6. Edit Account\n7. Delete Account\n8. Back\n9. Exit");
            userResponse = s.nextLine();
            if (userResponse.equals("1")) {
                t.createCourse(a);
                init();
            } else if (userResponse.equals("2")) {

                try {
                    chooseCourse().printQuizList();
                } catch (NullPointerException e) {
                    cont = true;
                }

            } else if (userResponse.equals("3")) {

                Course c = chooseCourse();

                if (c == null) {
                    JOptionPane.showMessageDialog(null, "Not a valid course", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    cont = true;
                } else {
                    String quizResponse = "";
                    boolean back = true;
                    do {
                        System.out.println("Would you like to:\n1. Create a quiz\n2. Edit/Delete a quiz\n3. View Quiz\n4. Back");
                        quizResponse = s.nextLine();
                        if (quizResponse.equals("1")) {
                            boolean quizBack = true;
                            do {
                                System.out.println("Would you like to: \n1. Import a file\n2. Manually create a quiz\n3. Randomize a quiz" +
                                        "\n4. Back");
                                String createQuiz = s.nextLine();
                                if (createQuiz.equals("1")) {

                                    System.out.println("Enter the file path of the file you would like to import.");
                                    System.out.println("Please only import files with the correct format:");
                                    System.out.println("@Question\n~question choice\nIncorrectly formatted files will not be imported");
                                    String filepath = s.nextLine();
                                    System.out.println("Please enter a name for this quiz");
                                    String quizName = s.nextLine();
                                    Quiz quiz = new Quiz(quizName);
                                    quiz.importQuiz(filepath, quizName);
                                    c.addQuiz(quiz);

                                } else if (createQuiz.equals("2")) {

                                    System.out.print("Please enter a name for this quiz\n");
                                    String nameQuiz = s.nextLine();
                                    System.out.println("Enter the number of questions you would like to add");
                                    boolean questionNum = true;
                                    do {
                                        String questionNumber = s.nextLine();
                                        Quiz addQuiz = new Quiz(nameQuiz);
                                        try {
                                            for (int i = 0; i < Integer.parseInt(questionNumber); i++) {
                                                addQuiz.addQuestion(i);
                                            }
                                            if (addQuiz.getQuestionList() != null) {
                                                addQuiz.exportQuizToFile(nameQuiz);
                                            }
                                            questionNum = false;
                                            c.addQuiz(addQuiz);
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Please enter a valid number of questions.", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        } catch (InputMismatchException e) {
                                            JOptionPane.showMessageDialog(null, "Please enter valid input", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                    } while (questionNum);

                                } else if (createQuiz.equals("3")) {

                                    //randomize quiz-ask for quiz-read it in-randomize it-overwrite the old file?-export the file
                                    System.out.println("Enter the quiz that you would like to randomize");
                                    String randomQuiz = s.nextLine();
                                    Quiz quizRandom = new Quiz(randomQuiz);
                                    quizRandom.shuffleQuiz(quizRandom.readQuiz(randomQuiz + ".txt", randomQuiz));
                                    quizRandom.exportQuizToFile(randomQuiz); //should overwrite the current quiz file to a randomized version

                                } else if (createQuiz.equals("4")) {
                                    quizBack = false;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Please enter a number from 1 - 4", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    createQuiz = s.nextLine();
                                }
                            } while (quizBack);


                        } else if (quizResponse.equals("2")) {

                            //add question, delete question, edit question
                            System.out.println("Are you editing this quiz or deleting it?\n1. Edit\n2. Delete");
                            String editOrDelete = s.nextLine();
                            while (!editOrDelete.equals("1") && !editOrDelete.equals("2")) {
                                JOptionPane.showMessageDialog(null, "Please enter 1 or 2", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                editOrDelete = s.nextLine();
                            }
                            if (editOrDelete.equals("1")) {
                                System.out.println("Enter the name of the quiz you would like to edit");
                                String editQuiz = s.nextLine();
                                while (c.getQuiz(editQuiz) == null) {
                                    JOptionPane.showMessageDialog(null, "Please enter a valid quiz name", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    editQuiz = s.nextLine();
                                }
                                Quiz quizedit = new Quiz(editQuiz);
                                quizedit.setQuizName(editQuiz);
                                quizedit.readQuiz(editQuiz + ".txt", editQuiz);
                                System.out.println("Would you like to: \n1. Add a question/choice \n2. Delete a question/choice " +
                                        "\n3. Modify a question/choice\n4. Back");
                                String edit = "";
                                boolean editCont = true;
                                do {
                                    edit = s.nextLine();
                                    if (edit.equals("1")) {

                                        System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
                                        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                                        System.out.println("1. Add Question\n2. Add a Choice to a Question");
                                        String choice = s.nextLine();

                                        if (choice.equals("1")) {
                                            System.out.println("Enter the number of questions you would like to add");
                                            int addQ = s.nextInt();
                                            for (int i = 0; i < addQ; i++) {
                                                quizedit.addQuestion(i);
                                            }
                                            System.out.println("The questions have been added!");
                                            quizedit.exportQuizToFile(editQuiz);
                                            System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
                                        } else if (choice.equals("2")) {
                                            System.out.println("Choose which question you would like to add a choice to");
                                            int choiceQuestion = s.nextInt();
                                            int choiceNumber = quizedit.getQuestionList().get(choiceQuestion - 1).getChoiceList().size();
                                            quizedit.getQuestionList().get(choiceQuestion - 1).addChoice(choiceNumber);
                                            quizedit.exportQuizToFile(editQuiz);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Please enter a valid input from 1 - 3", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else if (edit.equals("2")) {

                                        System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
                                        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                                        System.out.println("1.Delete a Question\n2.Delete a Choice in a Question");
                                        String deleteChoice = s.nextLine();

                                        if (deleteChoice.equals("1")) {
                                            System.out.println("Enter the question number you would like to delete");
                                            int deleteQ = s.nextInt();
                                            quizedit.deleteQuestion(deleteQ);
                                            quizedit.exportQuizToFile(editQuiz);
                                            System.out.println("The question has been deleted!");
                                            System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
                                        } else if (deleteChoice.equals("2")) {
                                            System.out.println("Choose which question you would like to delete a choice from");
                                            int dltQuestion = s.nextInt();
                                            System.out.println("Which choice would you like to delete");
                                            int choicedlt = s.nextInt();
                                            quizedit.getQuestionList().get(dltQuestion - 1).deleteChoice(choicedlt);
                                            quizedit.exportQuizToFile(editQuiz);
                                        }

                                    } else if (edit.equals("3")) {

                                    } else if (edit.equals("4")) {
                                        editCont = false;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Please enter a number between 1 - 4", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                } while (editCont);
                            } else if (editOrDelete.equals("2")) {
                                System.out.println("Enter the name of the quiz you wish to delete");
                                c.printQuizList();
                                String nameOfQuizToDelete = s.nextLine();
                                ArrayList<Quiz> quizList = c.getQuizList();
                                int indexOfQuizToDelete = 0;
                                int i = 0;
                                for (Quiz quizToDelete : quizList) {
                                    if (quizToDelete.getQuizName().equals(nameOfQuizToDelete)) {
                                        indexOfQuizToDelete = i;
                                    }
                                    i++;
                                }
                                c.deleteQuiz(quizList.get(indexOfQuizToDelete));
                                System.out.println(nameOfQuizToDelete + " has been deleted");
                            }

                        } else if (quizResponse.equals("3")) {

                            //to view the quiz, enter the quiz file -> create the object using the readQuiz -> tostring
                            System.out.println("Enter a quiz name to view the quiz");
                            String quizzName = s.nextLine() + s.next();
                            Quiz quiz = new Quiz(quizzName);
                            System.out.println(quiz.readQuiz(quizzName + ".txt", quizzName).toString());

                        } else if (quizResponse.equals("4")) {
                            back = false;
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a number from 1 - 4", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            quizResponse = s.nextLine();
                        }
                    } while (back);
                }
            } else if (userResponse.equals("4")) {

                Course c = chooseCourse();

                if (c == null) {
                    System.out.println("Not a valid course.");
                    cont = true;
                } else {
                    System.out.println("Which quiz would you like to grade?");
                    c.printQuizList();
                    String quizName = s.nextLine();
                    while (courseList.get(checkCourseExistence(c.getCourseName())).getQuiz(quizName) == null) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid quiz", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        quizName = s.nextLine();
                    }

                    ArrayList<String> submissions = t.parseSubmissions(checkCourseExistence(c.getCourseName()), quizName);

                    System.out.println("Which student would you like to grade?");
                    String studentName = s.nextLine();
                    while (t.checkStudentSubmission(submissions, studentName) == -1) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid student name who has taken the quiz", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        studentName = s.nextLine();
                    }

                    System.out.println("Which submission would you like to grade?");
                    int totalAttempts = t.printSubmissions(submissions, t.checkStudentSubmission(submissions, studentName), studentName);
                    for (int i = 1; i <= totalAttempts; i++) {
                        System.out.println(i);
                    }
                    String attemptNumber = s.nextLine();
                    int attempt = 0;
                    boolean cont2 = true;
                    do {
                        try {
                            attempt = Integer.parseInt(attemptNumber);
                            cont2 = false;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid attempt number", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            cont2 = true;
                        }
                    } while (cont2);
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(studentName + "_" + quizName +
                                "_" + attempt + ".smb.txt"));
                        PrintWriter pw = new PrintWriter(new FileWriter(new File(studentName + "_" + quizName +
                                "_" + attempt + ".scr.txt"), true));
                        String line = br.readLine();
                        while (line != null && !line.equals("")) {
                            System.out.println("Question: " + line);
                            pw.print(line + "\t");
                            line = br.readLine();
                            System.out.println("Points: " + line);
                            pw.println(line + " points");
                            line = br.readLine();
                            System.out.println("Answer: " + line);
                            boolean isValid = true;
                            System.out.println("Enter the student's score for this question below.");
                            String scoreStr = "";
                            double score = 0;
                            do {
                                scoreStr = s.nextLine();
                                try {
                                    score = Double.parseDouble(scoreStr);
                                } catch (NumberFormatException e) {
                                    isValid = false;
                                    JOptionPane.showMessageDialog(null, "Please enter a valid score", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } while (!isValid || score < 0);
                            pw.println(score);
                            line = br.readLine();
                        }
                        br.close();
                        pw.close();
                    } catch (IOException e) {
                        System.out.println(studentName + "\n" + quizName + "\n" + attempt + "\n");
                        e.printStackTrace();
                    }

                }

            } else if (userResponse.equals("5")) {
                Course c = chooseCourse();
                System.out.println("Which quiz would you like to view?");
                c.printQuizList();
                String quizName = s.nextLine();
                System.out.println("Which student's scores would you like to view?");
                String studentName = s.nextLine();
                System.out.println("Which submission would you like to see?");
                String submissionNumber = s.nextLine();
                parseScores(c, studentName, quizName, submissionNumber);
            } else if (userResponse.equals("6")) {
                a.editAccount(a.getUserName(), 1);
            } else if (userResponse.equals("7")) {
                try {
                    a.deleteAccount(a.getUserName(), 1);
                    return false;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Can't delete account right now", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else if (userResponse.equals("8")) {
                return false;
            } else if (userResponse.equals("9")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid input", "Error",
                        JOptionPane.ERROR_MESSAGE);
                cont = true;
            }
        } while (cont);
        return true;
    }

    private boolean studentFlow(Account a) {
        String userResponse = "";
        boolean cont = true;
        do {
            System.out.println("Would you like to:\n1. View a course\n2. Edit Account\n3. Delete Account\n4. Back\n5. Exit");
            userResponse = s.nextLine();
            if (userResponse.equals("1")) {

                if (courseList.size() == 0) {
                    System.out.println("No courses available");
                } else {
                    String courseName = "";
                    String quizName = "";
                    System.out.println("Which course would you like to view?");
                    for (Course e : courseList) {
                        System.out.println(e.getCourseName() + "\n");
                    }
                    courseName = s.nextLine();

                    while (courseIndex(courseName) == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a valid course (case sensitive).", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        courseName = s.nextLine();
                    }

                    int index = courseIndex(courseName);
                    if (!courseList.get(index).hasQuizzes()) {
                        System.out.println("Course doesn't have quizzes.");
                    } else {
                        System.out.println("Which quiz would you like to view?");
                        courseList.get(index).printQuizList();
                        quizName = s.nextLine();

                        while (courseList.get(index).getQuiz(quizName) == null) {
                            JOptionPane.showMessageDialog(null, "Please select a valid course (case sensitive).", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            quizName = s.nextLine();
                        }
                        viewQuiz(courseName, quizName);
                    }

                }

            } else if (userResponse.equals("2")) {

                a.editAccount(a.getUserName(), 2);

            } else if (userResponse.equals("3")) {

                try {
                    a.deleteAccount(a.getUserName(), 2);
                    return false;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Can't delete account now.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println("Can't delete account now.");
                    return false;
                }

            } else if (userResponse.equals("4")) {

                return false;

            } else if (userResponse.equals("5")) {

                return true;

            }
        } while (cont);
        return true;
    }


    private void viewQuiz(String courseName, String quizName) {

        String choice = "";
        boolean cont = true;
        do {
            System.out.println("Would you like to:\n1. Take Quiz\n2. View Score\n3. Back");
            choice = s.nextLine();
            if (choice.equals("1")) {

                boolean quizBool = false;
                do {
                    //Method to take quiz based on user input
                    String quizTxt = quizName + ".txt";
                    File f = new File(quizTxt);
                    if (f.exists()) {
                        Quiz quiz = new Quiz(); //calls the blank quiz constructor
                        System.out.println("Please enter your username");
                        String username = s.nextLine();
                        System.out.println("Please enter your attempt number for this quiz");
                        int attempt = s.nextInt();
                        System.out.println("Please enter a choice or attach a file for each question");
                        System.out.println("Incorrect file inputs will not be graded!");
                        quiz.shuffleQuiz(quiz.readQuiz(quizTxt, quizName)); //makes a Quiz from file and shuffles the question field
                        quiz.takeQuiz(username, attempt); //add the answers to a file username_quizName_1.smb.txt
                        //find a way to append the txt file to student account and the quiz file -> use the name pattern?
                        cont = false;
                    } else {
                        //throw new quizNotExistException ("quiz does not exist")
                        quizBool = true;
                    }
                } while (quizBool);

            } else if (choice.equals("2")) {
                System.out.println("Which submission do you want?");
                String submissionNumber = s.nextLine();
                parseScores(courseList.get(courseIndex(courseName)), a.getUserName(), quizName, submissionNumber);
            } else if (choice.equals("3")) {
                cont = false;
            } else {
                System.out.println("Please enter a number from 1-3.");
            }
        } while (cont);

    }

    private int checkCourseExistence(String courseName) {
        int i = 0;
        for (Course e : courseList) {
            if (e.getCourseName().equals(courseName))
                return i;
            i++;
        }
        return -1;
    }

    private Course chooseCourse() {

        if (courseList.size() == 0) {
            System.out.println("No courses available");
            return null;
        }

        System.out.println("Which course would you like to choose?");


        for (Course e : courseList) {
            System.out.println(e.getCourseName());
        }

        String courseName = s.nextLine();

        while (courseIndex(courseName) == -1) {
            JOptionPane.showMessageDialog(null, "Please enter a valid course.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            courseName = s.nextLine();
        }

        return courseList.get(courseIndex(courseName));
    }

    private int courseIndex(String courseName) {
        int i = 0;
        for (Course e : courseList) {
            if (e.getCourseName().equals(courseName)) {
                return i;
            }
            i++;
        }
        return -1;
    }


    private void createAccount() {
        boolean cont = false;
        do {
            try {
                a.createAccount();
                System.out.println("Do you want to create another account?\n1. Yes\n2. No");
                String input = s.nextLine();
                do {
                    if (input.equals("1")) {
                        cont = true;
                    } else if (input.equals("2")) {
                        cont = false;
                    } else {
                        System.out.println("Please enter a number from 1 - 2");
                        input = s.nextLine();
                    }
                } while (!input.equals("1") && !input.equals("2"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Please ensure you have a usernames.txt file created and run the program again.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (cont);
    }

    private void parseScores(Course c, String studentName, String quizName, String submissionNumber) {
        try {
            BufferedReader brSMB = new BufferedReader(new FileReader(new File(studentName + "_" + quizName + "_" +
                    submissionNumber + ".smb.txt")));
            BufferedReader brSRC = new BufferedReader(new FileReader(new File(studentName + "_" + quizName + "_" +
                    submissionNumber + ".scr.txt")));

            brSMB.readLine();
            String lineSMB = brSMB.readLine();

            brSRC.readLine();
            String lineSRC = brSRC.readLine();
            double maxPoints = 0;

            while (lineSMB != null) {
                maxPoints += Double.parseDouble(lineSMB);
                brSMB.readLine();
                brSMB.readLine();
                lineSMB = brSMB.readLine();
            }
            double gainedPoints = 0;
            while (lineSRC != null) {
                gainedPoints += Double.parseDouble(lineSRC);
                brSRC.readLine();
                lineSRC = brSRC.readLine();
            }

            System.out.println("Your score is " + gainedPoints + " out of " + maxPoints);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Please ensure the student quiz has been graded.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


}