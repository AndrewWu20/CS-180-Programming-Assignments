//import java.util.ArrayList;
//import java.util.Scanner;
//import java.io.*;
//
//public class PotentialMenu {
//    public static void main(String[] args) throws IOException {
//
//        /*FILE FLOW GOES HERE
//        main.txt is parsed for course, teacher, and student files
//        (teacher, student, course files will show up in any particular order
//        we use tags to distinguish file types)
//        Teacher1.txt
//        ...
//        TeacherN.txt
//        Student1.txt
//        ...
//        StudentN.txt
//        Teacher file:
//            login stuff
//            Course 1.txt
//            ...
//            Course N.txt
//        Student file:
//            login stuff
//            quiz submissions
//        Course file:
//            quiz1.txt
//            ...
//            quizN.txt
//        Quiz file:
//            quizNameSubmissions.txt
//            question 1: blah blah
//                choice 1
//                ...
//                choice N
//            ...
//            question N
//                choice 1
//                ...
//                choice N
//        quizNameSubmissions.txt:
//            Student1
//                answers
//            ...
//            StudentN
//                answers
//        END FILE FLOW
//        */
//
//
//        //for now, teacher identifier is @, student is #, course is $
//        //iterate through main.txt, use identifiers for teacher, student, course
//        //iterate through each teacher.txt and call constructor with information
//        //iterate through each student.txt and call constructor with information
//        //iterate through each course.txt and call constructor with information
//        //iterate through each quiz.txt and call constructor with information
//        //after quizzes are created, add them to corresponding course
//        //after main.txt is parsed, every teacher, student, course, quiz, and question object should be re-instantiated
//
//        ArrayList<Course> courseList = new ArrayList<Course>();
//        try {
//            BufferedReader mainReader = new BufferedReader(new FileReader("main.txt"));
//            String line = mainReader.readLine();
//            while (line != null) {
//                if (line.charAt(0) == '@') {
//                    //read teacher logic
//                } else if (line.charAt(0) == '#') {
//                    //read student logic
//                } else if (line.charAt(0) == '$') {
//                    //read course logic
//                    String courseName = line.substring(0, line.length() - 3);
//                    Course currCourse = new Course(courseName);
//                    BufferedReader courseReader = new BufferedReader(new FileReader(String.format("%s.txt", courseName)));
//                    String courseLine = courseReader.readLine();
//                    while (courseLine != null) {
//                        //to the current course, add the quiz that's read in by the filename given in the line
//                        currCourse.addQuiz(currCourse.readQuiz(courseLine.substring(0, courseLine.length() - 3)));
//
//                        courseLine = courseReader.readLine();
//                    }
//                    courseList.add(currCourse);
//                }
//                line = mainReader.readLine();
//            }
//        } catch (IOException e) {
//            System.out.println("There was an issue reading from main.txt");
//        }
//
//
//        Scanner scanner = new Scanner(System.in);
//        Account account = new Account();
//        boolean repeat = true;
//        do {
//            System.out.println("Welcome to our interface! Would you like to\n1. Create Account\n2. Sign in");
//            int userCASIResponse = scanner.nextInt();
//            switch (userCASIResponse) {
//                case 1:
//                    account.createAccount();
//                    repeat = true;
//                    break;
//                case 2:
//                    account.login();
//                    if (account.checkUserType(account.getUserName()).equals("teacher")) {
//                        repeat = false;
//
//                        System.out.println("Would you like to 1. See course quizzes 2. Edit course 3. View student 4. Edit account");
//                        int choiceTeacher = scanner.nextInt();
//                        scanner.nextLine();
//                        switch (choiceTeacher) {
//                            case 1:
//                                //Display quizzes associated with teacher and course
//
//                                return;
//                            case 2:
//                                System.out.println("Would you like to: \n1. Create a quiz \n2. Edit/Delete a quiz \n3. View a quiz");
//                                int choiceQuiz = scanner.nextInt();
//                                switch (choiceQuiz) {
//                                    case 1:
//                                        System.out.println("Would you like to: \n1. Import a file\n2. Manually create a quiz\n3.Randomize a quiz");
//                                        int createQuiz = scanner.nextInt();
//                                        switch (createQuiz) {
//                                            case 1:
//                                                System.out.println("Enter the file path of the file you would like to import.");
//                                                System.out.println("Please only import files with the correct format:");
//                                                System.out.println("@Question\n~question choice\nIncorrectly formatted files will not be imported");
//                                                String filepath = scanner.nextLine();
//                                                System.out.println("Please enter a name for this quiz");
//                                                String quizName = scanner.nextLine();
//                                                Quiz quiz = new Quiz();
//                                                quiz.importQuiz(filepath, quizName); //creates a new file with quizName.txt - if not valid format won't create a quiz object
//                                                return;
//                                            case 2:
//                                                System.out.println("Please enter a name for this quiz");
//                                                String nameQuiz = scanner.nextLine();
//                                                //manually create a quiz - ask for how many questions- loop through those questions- ask for choices- loops through choices-export it
//                                            case 3:
//                                                //randomize quiz-ask for quiz-read it in-randomize it-overwrite the old file?-export the file
//                                        }
//
//                                    case 2:
//                                        //randomize te quiz, delete question, add question, modift question
//                                        //edit choice, delete choice, add choice, modify choice
//                                        System.out.println("Would you like to: \n1.Add a question \n2.Delete a question \n3.Modify a question");
//                                        int edit = scanner.nextInt();
//                                        switch (edit) {
//                                            case 1:
//                                                return;
//                                            case 3:
//                                                //to view the quiz, enter the quiz file -> create the object using the readQuiz -> tostring
//                                                System.out.println("Enter a quiz name to view the quiz");
//                                                String quizzName = scanner.nextLine();
//                                                Quiz quiz = new Quiz();
//                                                quiz.readQuiz(quizzName + ".txt", quizzName);
//                                                quiz.toString();
//                                                return;
//                                            default:
//                                                return;
//                                        }
//                                    case 3:
//                                        System.out.println("Which student would you like to view?");
//                                        String student = scanner.nextLine();
//                                        //Depending on student, view their quiz scores
//
//                                        return;
//                                    case 4:
//                                        System.out.println("Would you like to 1. Change your username 2. Change your password 3. Delete your account");
//                                        int studentEdit = scanner.nextInt();
//                                        scanner.nextLine();
//                                        switch (studentEdit) {
//                                            case 1:
//                                                System.out.println("What is your new username?");
//                                                String usernameNew = scanner.nextLine();
//                                                //Update username
//
//                                                return;
//                                            case 2:
//                                                System.out.println("What is your new password");
//                                                String passwordNew = scanner.nextLine();
//                                                //Update password
//
//                                                return;
//                                            case 3:
//                                                System.out.println("Are you sure you want to delete your account?");
//                                                String delete = scanner.nextLine();
//                                                if (delete.equalsIgnoreCase("yes")) {
//                                                    //delete account
//
//
//                                                } else if (account.checkUserType(account.getUserName()).equals("student")) {
//                                                    repeat = false;
//
//                                                    System.out.println("What would you like to do? 1. Edit account 2. Choose course (Answer with 1 or 2)");
//                                                    int choiceStudent = scanner.nextInt();
//                                                    scanner.nextLine();
//                                                    switch (choiceStudent) {
//                                                        case 1:
//                                                            account.editAccount(account.getUserName());
//
//                                                        case 2:
//                                                            System.out.println("Which course would you like to view?");
//                                                            String course = scanner.nextLine();
//                                                            //Checker to make sure that the student is in the class
//                                                            //Display list of quizzes
//
//                                                            System.out.println("Would you like to 1. See your quiz score 2. Take the quiz");
//                                                            int quizOption = scanner.nextInt();
//                                                            scanner.nextLine();
//                                                            switch (quizOption) {
//                                                                case 1:
//                                                                    System.out.println("Which quiz would you like to view?");
//                                                                    String quizName = scanner.nextLine();
//                                                                    //Method to show quiz score based on user input
//
//                                                                    return;
//                                                                case 2:
//                                                                    boolean quizBool = false;
//                                                                    do {
//                                                                        System.out.println("Which quiz would you like to take?");
//                                                                        String quizTake = scanner.nextLine();
//                                                                        //Method to take quiz based on user input
//                                                                        String quizTxt = quizTake + ".txt";
//                                                                        File f = new File(quizTxt);
//                                                                        if (f.exists()) {
//                                                                            Quiz quiz = new Quiz(); //calls the blank quiz constructor
//                                                                            System.out.println("Please enter your username");
//                                                                            String username = scanner.nextLine();
//                                                                            System.out.println("Please enter your attempt number for this quiz");
//                                                                            int attempt = scanner.nextInt();
//                                                                            System.out.println("Please enter a choice or attach a file for each question");
//                                                                            System.out.println("Incorrect file inputs will not be graded!");
//                                                                            quiz.shuffleQuiz(quiz.readQuiz(quizTxt, quizTake)); //makes a Quiz from file and shuffles the question field
//                                                                            quiz.takeQuiz(username, attempt); //add the answers to a file username_quizName_1.smb.txt
//                                                                            //find a way to append the txt file to student account and the quiz file -> use the name pattern?
//
//                                                                        } else {
//                                                                            //throw new quizNotExistException ("quiz does not exist")
//                                                                            quizBool = true;
//                                                                        }
//                                                                    } while (quizBool);
//                                                                    return;
//                                                                default:
//                                                                    return;
//                                                            }
//                                                        default:
//                                                            return;
//                                                    }
//
//                                                }
//                                        }
//                                }
//                        }
//                    }
//            }
//        }while (repeat);
//    }
//}