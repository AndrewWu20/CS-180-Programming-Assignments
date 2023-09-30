import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {

    public ArrayList<String> userNames = new ArrayList<String>();
    public String passwordCA;
    public String userName;
    public String password;
    public String userType;
    public List<String> usernames = new ArrayList<String>();
    public List<String> userLines = new ArrayList<String>();

    public void writeFile(String fileName, String text) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName), true))) {
            pw.println(text);
        }
    }

    public void createAccount() throws IOException {
        Scanner scanner = new Scanner(System.in);

        boolean repeat = false;
        boolean repeat1 = false;
        boolean repeat2 = false;
        boolean repeat3 = false;

        do {
            System.out.println("Are you a\n1. Teacher \n2. Student");
            userType = scanner.nextLine();
            if (userType.equals("1")) {
                userType = "Teacher";
                repeat1 = false;
            } else if (userType.equals("2")) {
                userType = "Student";
                repeat1 = false;
            } else {
                System.out.println("Invalid Selection. Please Try Again.");
                repeat1 = true;
            }
        } while (repeat1);

        do {
            do {
                System.out.println("What do you want as your username?");
                userName = scanner.nextLine();
                if (userName.contains(" ")) {
                    System.out.println("Please ensure there are no spaces.");
                    repeat3 = true;
                }
            } while (repeat3);

            try (BufferedReader br = new BufferedReader(new FileReader("usernames.txt"))) {
                String line = br.readLine();
                while (line != null) {
                    usernames.add(line);
                    line = br.readLine();
                }
                if (usernames.contains(userName)) {
                    System.out.println("This username already exists. Please enter a different one");
                    repeat = true;
                } else {
                    writeFile("usernames.txt", userName);
                    break;
                }
            }
        } while (repeat);

        do {
            System.out.println("Enter a password:");
            passwordCA = scanner.nextLine();
            if (passwordCA.contains(" ")) {
                System.out.println("Please ensure your password contains no spaces.");
                repeat2 = true;
            }

            try {
                if (userType.equalsIgnoreCase("teacher")) {
                    writeFile("@" + userName + ".txt", userName + " " + passwordCA + " " + userType);
                    writeFile("main.txt", "@" + userName + ".txt");
                } else if (userType.equalsIgnoreCase("student")) {
                    writeFile("#" + userName + ".txt", userName + " " + passwordCA + " " + userType);
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            System.out.println("You have been registered successfully!");
        } while (repeat2);
    }


    public int login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = false;
        boolean repeat1 = false;
        boolean repeat2 = false;

        if (hasUserNames()) {
            do {
                do {
                    System.out.println("Are you a\n1. Teacher \n2. Student");
                    userType = scanner.nextLine();
                    if (userType.equals("1")) {
                        userType = "Teacher";
                        repeat1 = false;
                    } else if (userType.equals("2")) {
                        userType = "Student";
                        repeat1 = false;
                    } else {
                        System.out.println("Invalid Selection. Please Try Again.");
                        repeat1 = true;
                    }
                } while (repeat1);

                System.out.println("What is your username?");
                userName = scanner.nextLine();

                try (BufferedReader br = new BufferedReader(new FileReader("usernames.txt"))) {
                    String line = br.readLine();
                    while (line != null) {
                        usernames.add(line);
                        line = br.readLine();
                    }
                    if (!usernames.contains(userName)) {
                        System.out.println("This username does not exist");
                        repeat = true;
                    } else {
                        do {
                            System.out.println("Enter your password:");
                            password = scanner.nextLine();

                            try {
                                File f;
                                if (userType.equalsIgnoreCase("teacher")) {
                                    f = new File("@" + userName + ".txt");
                                } else {
                                    f = new File("#" + userName + ".txt");
                                }
                                try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
                                    String line1 = bfr.readLine();
                                    String[] credentialsSplit = line1.split(" ");
                                    if (credentialsSplit[0].equals(userName) && credentialsSplit[1].equals(password)) {
                                        System.out.println("Login Successful");
                                        repeat = false;
                                        repeat2 = false;

                                    } else {
                                        System.out.println("Login Unsuccessful\nTry Again");
                                        repeat2 = true;
                                    }

                                }

                            } catch (FileNotFoundException e) {
                                System.out.println("Login Unsuccessful\nTry Again");
                                repeat = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } while (repeat2);

                    }

                }
            } while (repeat);
        } else {
            throw new IOException();
        }

        if (userType.equalsIgnoreCase("teacher"))
            return 1;
        else
            return 2;
    }


    public void deleteAccount(String username, int type) throws IOException {
        File userFile = null;
        if (type == 1)
            userFile = new File("@" + username + ".txt");
        else if (type == 2)
            userFile = new File("#" + username + ".txt");
        userFile.delete();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("usernames.txt")));
        String line = br.readLine();
        while (line != null) {
            usernames.add(line);
            line = br.readLine();
        }
        usernames.remove(username);
        FileWriter writer = new FileWriter("usernames.txt");
        for (String str : usernames) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }


    public void editAccount(String username, int type) {
        Scanner scanner = new Scanner(System.in);
        File userFile = null;
        if (type == 1)
            userFile = new File("@" + username + ".txt");
        else if (type == 2)
            userFile = new File("#" + username + ".txt.");
        try (BufferedReader bfr = new BufferedReader(new FileReader(userFile))) {
            String line = bfr.readLine();
            String[] credentialsSplit = line.split(" ");


            System.out.println("What would you like to change? (Enter the corresponding number)\n1. Username" +
                    "\n2. Password");
            int userResponse = scanner.nextInt();
            scanner.nextLine();
            //TODO: Change to if-else and add do-while
            switch (userResponse) {

                case 1:
                    System.out.println("Enter your new username:");
                    String newUsername = scanner.nextLine();
                    if (type == 1)
                        new File("@" + username + ".txt").renameTo(new File("@" + newUsername + ".txt"));
                    else if (type == 2)
                        new File("#" + username + ".txt").renameTo(new File("#" + newUsername + ".txt"));
                    File userFileNew = null;
                    if (type == 1)
                        userFileNew = new File("@" + newUsername + ".txt");
                    else if (type == 2)
                        userFileNew = new File("#" + newUsername + ".txt");

                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(userFileNew)));
                    String line1 = br.readLine();
                    while (line1 != null) {
                        userLines.add(line1);
                        line1 = br.readLine();
                    }
                    FileWriter writer = new FileWriter(userFileNew);
                    userLines.remove(0);
                    userLines.add(0, newUsername + " " + credentialsSplit[1] + credentialsSplit[2]);
                    for (String str : userLines) {
                        writer.write(str + System.lineSeparator());
                    }
                    writer.close();

                    BufferedReader rb = new BufferedReader(new InputStreamReader(new FileInputStream("usernames.txt")));
                    String line3 = rb.readLine();
                    while (line3 != null) {
                        usernames.add(line3);
                        line3 = rb.readLine();
                    }
                    usernames.remove(username);
                    usernames.add(newUsername);

                    FileWriter writer1 = new FileWriter("usernames.txt");
                    for (String str : usernames) {
                        writer1.write(str + System.lineSeparator());
                    }
                    writer1.close();

                    break;

                case 2:
                    System.out.println("Enter your new password:");
                    String newPassword = scanner.nextLine();
                    File userFileNew1 = null;
                    if (type == 1)
                        userFileNew1 = new File("@" + username + ".txt");
                    else if (type == 2)
                        userFileNew1 = new File("#" + username + ".txt");

                    BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(userFileNew1)));
                    String line2 = r.readLine();
                    while (line2 != null) {
                        userLines.add(line2);
                        line2 = r.readLine();
                    }
                    FileWriter w = new FileWriter(userFileNew1);
                    userLines.remove(0);
                    userLines.add(0, username + " " + newPassword + credentialsSplit[2]);
                    for (String str : userLines) {
                        w.write(str + System.lineSeparator());
                    }
                    w.close();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public String checkUserType(String userName) throws FileNotFoundException {
//        String userRole = null;
//        File f = new File(userName + ".txt");
//        try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
//            String line = bfr.readLine();
//            String[] credentialsSplit = line.split(" ");
//            if (credentialsSplit[2].equals("teacher")) {
//                userRole = "teacher";
//            } else if (credentialsSplit[2].equals("student")) {
//                userRole = "student";
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return userRole;
//    }

    private boolean hasUserNames() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("usernames.txt"));
            return br.readLine() != null;
        } catch (IOException e) {
            return false;
        }
    }


    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(ArrayList<String> userNames) {
        this.userNames = userNames;
    }

    public String getPasswordCA() {
        return passwordCA;
    }

    public void setPasswordCA(String passwordCA) {
        this.passwordCA = passwordCA;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

}




//import java.util.ArrayList;
//import java.util.Scanner;
//import java.io.*;
//
//public class Menu {
//    public static void main(String[] args) throws IOException {
//
//        /*FILE FLOW GOES HERE
//        main.txt is parsed for course, teacher, and student files
//        (teacher, student, course files will show up in any particular order
//        we use tags to distinguish file types)
//
//        Teacher1.txt
//        ...
//        TeacherN.txt
//
//        Student1.txt
//        ...
//        StudentN.txt
//
//
//
//
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
//
//
//
//
//
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
//            String line;
//            while ((line = mainReader.readLine()) != null) {
//                if (line.charAt(0) == '#') {
//                    // read teacher logic
//                    String teacherName = line.substring(1, line.length() - 4);
//                    BufferedReader teacherReader = new BufferedReader(new FileReader(String.format("%s.txt", teacherName)));
//                    String teacherLine;
//                    while ((teacherLine = teacherReader.readLine()) != null) {
//                        if (teacherLine.charAt(0) == '$') {
//                            //read course logic
//                            String courseName = teacherLine.substring(1, teacherLine.length() - 4);
//                            Course currCourse = new Course(courseName);
//                            courseList.add(currCourse);
//                            BufferedReader courseReader = new BufferedReader(new FileReader(String.format("%s.txt", courseName)));
//                            String courseLine;
//                            while ((courseLine = courseReader.readLine()) != null) {
//                                //to the current course, add the quiz that's read in by the filename given in the line
//                                currCourse.addQuiz(currCourse.readQuiz(courseLine.substring(0, courseLine.length() - 4)));
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
//                                        System.out.println("Would you like to: \n1. Import a file\n2. Manually create a quiz\n3. Randomize a quiz");
//                                        int createQuiz = scanner.nextInt();
//                                        switch (createQuiz) {
//                                            case 1:
//                                                System.out.println("Enter the file path of the file you would like to import.");
//                                                System.out.println("Please only import files with the correct format:");
//                                                System.out.println("@Question\n~question choice\nIncorrectly formatted files will not be imported");
//                                                String filepath = scanner.nextLine();
//                                                System.out.println("Please enter a name for this quiz");
//                                                String quizName = scanner.nextLine();
//                                                Quiz quiz = new Quiz(quizName);
//                                                quiz.importQuiz(filepath, quizName); //creates a new file with quizName.txt - if not valid format won't create a quiz object
//                                                return;
//                                            case 2:
//                                                System.out.print("Please enter a name for this quiz\n");
//                                                String nameQuiz = scanner.nextLine() + scanner.next();
//                                                System.out.println("Enter the number of questions you would like to add");
//                                                int questionNumber = scanner.nextInt();
//                                                Quiz addQuiz = new Quiz(nameQuiz);
//                                                for (int i = 0; i < questionNumber; i ++) {
//                                                    addQuiz.addQuestion(i);
//                                                }
//                                                if (addQuiz.getQuestionList() != null) {
//                                                    addQuiz.exportQuizToFile(nameQuiz);
//                                                }
//                                                return;
//                                            //manually create a quiz - ask for how many questions- loop through those questions- ask for choices- loops through choices-export it
//                                            case 3:
//                                                //randomize quiz-ask for quiz-read it in-randomize it-overwrite the old file?-export the file
//                                                System.out.println("Enter the quiz that you would like to randomize");
//                                                String randomQuiz = scanner.nextLine() + scanner.next();
//                                                Quiz quizRandom = new Quiz(randomQuiz);
//                                                quizRandom.shuffleQuiz(quizRandom.readQuiz(randomQuiz + ".txt", randomQuiz));
//                                                quizRandom.exportQuizToFile(randomQuiz); //should overwrite the current quiz file to a randomized version
//                                        }
//                                    case 2:
//                                        //add question, delete question, edit question
//                                        System.out.println("Enter the name of the quiz you would like to edit");
//                                        String editQuiz = scanner.nextLine() + scanner.next();
//                                        Quiz quizedit = new Quiz(editQuiz);
//                                        quizedit.setQuizName(editQuiz);
//                                        quizedit.readQuiz(editQuiz + ".txt", editQuiz);
//                                        System.out.println("Would you like to: \n1.Add a question/choice \n2.Delete a question/choice \n3.Modify a question/choice");
//                                        int edit = scanner.nextInt();
//                                        switch (edit) {
//                                            //need some sort of loop structure?
//                                            //might forgo editing choices since it adds another layer of complexity
//                                            case 1:
//                                                //add a question
//                                                System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
//                                                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
//                                                System.out.println("1. Add Question\n2. Add a Choice to a Question");
//                                                int choice = scanner.nextInt();
//                                                switch (choice) {
//                                                    case 1://adds a question
//                                                        System.out.println("Enter the number of questions you would like to add");
//                                                        int addQ = scanner.nextInt();
//                                                        for (int i = 0; i < addQ; i++) {
//                                                            quizedit.addQuestion(i);
//                                                        }
//                                                        System.out.println("The questions have been added!");
//                                                        System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
//                                                        quizedit.exportQuizToFile(editQuiz);
//                                                    case 2: //adds a choice to a question
//                                                        System.out.println("Choose which question you would like to add a choice to");
//                                                        int choiceQuestion = scanner.nextInt();
//                                                        int choiceNumber = quizedit.getQuestionList().get(choiceQuestion - 1).getChoiceList().size();
//                                                        quizedit.getQuestionList().get(choiceQuestion -1).addChoice(choiceNumber);
//                                                        quizedit.exportQuizToFile(editQuiz);
//                                                        return; //TODO: add a way for the user to loop back?
//                                                }
//                                            case 2:
//                                                System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
//                                                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
//                                                System.out.println("1.Delete a Question\n2.Delete a Choice in a Question");
//                                                int deleteChoice = scanner.nextInt();
//                                                switch (deleteChoice) {
//                                                    case 1:
//                                                        System.out.println("Enter the question number you would like to delete");
//                                                        int deleteQ = scanner.nextInt();
//                                                        quizedit.deleteQuestion(deleteQ);
//                                                        quizedit.exportQuizToFile(editQuiz);
//                                                        System.out.println("The question has been deleted!");
//                                                        System.out.println(quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString());
//                                                        return;
//                                                    case 2:
//                                                        System.out.println("Choose which question you would like to delete a choice from");
//                                                        int dltQuestion = scanner.nextInt();
//                                                        System.out.println("Which choice would you like to delete");
//                                                        int choicedlt = scanner.nextInt();
//                                                        quizedit.getQuestionList().get(dltQuestion -1 ).deleteChoice(choicedlt);
//                                                        quizedit.exportQuizToFile(editQuiz);
//                                                        return;
//                                                }
//                                            case 3:
//
//                                            default:
//                                                System.out.println("Invalid choice, exiting program"); //do a while loop to not kick user out?
//                                                return;
//                                        }
//                                    case 3:
//                                        //to view the quiz, enter the quiz file -> create the object using the readQuiz -> tostring
//                                        System.out.println("Enter a quiz name to view the quiz");
//                                        String quizzName = scanner.nextLine() + scanner.next();
//                                        Quiz quiz = new Quiz(quizzName);
//                                        System.out.println(quiz.readQuiz(quizzName + ".txt",quizzName).toString());
//                                        return;
//                                    default:
//                                        return;
//                                }
//                            case 3:
//                                System.out.println("Which student would you like to view?");
//                                String student = scanner.nextLine();
//                                //Depending on student, view their quiz scores
//
//                                return;
//                            case 4:
//                                System.out.println("Would you like to 1. Change your username 2. Change your password 3. Delete your account");
//                                int studentEdit = scanner.nextInt();
//                                scanner.nextLine();
//                                switch (studentEdit) {
//                                    case 1:
//                                        System.out.println("What is your new username?");
//                                        String usernameNew = scanner.nextLine();
//                                        //Update username
//
//                                        return;
//                                    case 2:
//                                        System.out.println("What is your new password");
//                                        String passwordNew = scanner.nextLine();
//                                        //Update password
//
//                                        return;
//                                    case 3:
//                                        System.out.println("Are you sure you want to delete your account?");
//                                        String delete = scanner.nextLine();
//                                        if (delete.equalsIgnoreCase("yes")) {
//                                            //delete account
//
//
//                                        } else if (account.checkUserType(account.getUserName()).equals("student")) {
//                                            repeat = false;
//
//                                            System.out.println("What would you like to do? 1. Edit account 2. Choose course (Answer with 1 or 2)");
//                                            int choiceStudent = scanner.nextInt();
//                                            scanner.nextLine();
//                                            switch (choiceStudent) {
//                                                case 1:
//                                                    account.editAccount(account.getUserName());
//
//                                                case 2:
//                                                    System.out.println("Which course would you like to view?");
//                                                    String course = scanner.nextLine();
//                                                    //Checker to make sure that the student is in the class
//                                                    //Display list of quizzes
//
//                                                    System.out.println("Would you like to 1. See your quiz score 2. Take the quiz");
//                                                    int quizOption = scanner.nextInt();
//                                                    scanner.nextLine();
//                                                    switch (quizOption) {
//                                                        case 1:
//                                                            System.out.println("Which quiz would you like to view?");
//                                                            String quizName = scanner.nextLine();
//                                                            //Method to show quiz score based on user input
//
//                                                            return;
//                                                        case 2:
//                                                            boolean quizBool = false;
//                                                            do {
//                                                                System.out.println("Which quiz would you like to take?");
//                                                                String quizTake = scanner.nextLine();
//                                                                //Method to take quiz based on user input
//                                                                String quizTxt = quizTake + ".txt";
//                                                                File f = new File(quizTxt);
//                                                                if (f.exists()) {
//                                                                    Quiz quiz = new Quiz(); //calls the blank quiz constructor
//                                                                    System.out.println("Please enter your username");
//                                                                    String username = scanner.nextLine();
//                                                                    System.out.println("Please enter your attempt number for this quiz");
//                                                                    int attempt = scanner.nextInt();
//                                                                    System.out.println("Please enter a choice or attach a file for each question");
//                                                                    System.out.println("Incorrect file inputs will not be graded!");
//                                                                    quiz.shuffleQuiz(quiz.readQuiz(quizTxt, quizTake)); //makes a Quiz from file and shuffles the question field
//                                                                    quiz.takeQuiz(username, attempt); //add the answers to a file username_quizName_1.smb.txt
//                                                                    //find a way to append the txt file to student account and the quiz file -> use the name pattern?
//
//                                                                } else {
//                                                                    //throw new quizNotExistException ("quiz does not exist")
//                                                                    quizBool = true;
//                                                                }
//                                                            } while (quizBool);
//                                                            return;
//                                                        default:
//                                                            return;
//                                                    }
//                                                default:
//                                                    return;
//                                            }
//
//                                        }
//                                }
//                        }
//                    }
//            }
//        }
//    }while (repeat);
//}
//}
