import javax.swing.*;
import java.util.Scanner;

public class Project4Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JOptionPane.showMessageDialog(null, "Hello and welcome to our interface", "Welcome",
                JOptionPane.INFORMATION_MESSAGE);
        Integer[] choices = {1,2};
        int userChoice;
        userChoice = (int) JOptionPane.showInputDialog(null, "Would you like to create an account of sign in?",
                "Choice", JOptionPane.QUESTION_MESSAGE,
                null, choices, choices[0]);
        String position = " ";
        String username = " ";
        String password = " ";
        if (userChoice == 1) {
            do {
                System.out.println("Are you a student or a teacher?");
                position = scanner.nextLine();
                //Store if account is teacher or student

                System.out.println("What username would you like?");
                username = scanner.nextLine();
                //Store username

                System.out.println("What password would you like to use?");
                password = scanner.nextLine();
                //Store password

                //Store credentials

                System.out.println("Hello and welcome to our interface. Would you like to create an account or sign in");
                answerStart = scanner.nextLine();
            } while (answerStart.equalsIgnoreCase("create an account"));
            System.out.println("What is your username?");
            username = scanner.nextLine();
            System.out.println("What is your password?");
            password = scanner.nextLine();
            //Exception to make sure account exists


            if (answerStart.equalsIgnoreCase(("sign in"))) {
                if (position.equalsIgnoreCase("student")) {
                    System.out.println("Would you like to edit your account?");
                    String edit = scanner.nextLine();
                    if (edit.equalsIgnoreCase("yes")) {
                        System.out.println("Would you like to change your username?");
                        String changeUsername = scanner.nextLine();
                        if (changeUsername.equalsIgnoreCase("yes")) {
                            System.out.println("What would you like your new username to be?");
                            username = scanner.nextLine();
                            //Update username
                        } else {
                            System.out.println("Would you like to change your password?");
                            String changePassword = scanner.nextLine();
                            if (changePassword.equalsIgnoreCase("yes")) {
                                System.out.println("What would you like your new password to be?");
                                password = scanner.nextLine();
                                //Update password
                            } else {
                                System.out.println("Would you like to delete your account?");
                                String delete = scanner.nextLine();
                                if (delete.equalsIgnoreCase("yes")) {
                                    //Method to delete account
                                } else {
                                    return;
                                }
                            }
                        }
                    } else {
                        System.out.println("Which course would you like to view?");
                        String course = scanner.nextLine();
                        //Checker to make sure that the student is in the class
                        //Display list of quizzes
                        System.out.println("Would you like to see your quiz score?");
                        String score = scanner.nextLine();
                        if (score.equalsIgnoreCase("yes")) {
                            //Method to show score
                            //Exception if student hasn't taken quiz
                        } else {
                            System.out.println("Would you like to take the quiz");
                            String take = scanner.nextLine();
                            if (take.equalsIgnoreCase("yes")) {
                                //Method to take quiz
                                //Exception if student has taken quiz
                            } else {
                                return;
                            }
                        }
                    }
                } else {
                    System.out.println("Would you like to edit your account?");
                    String edit = scanner.nextLine();
                    if (edit.equalsIgnoreCase("yes")) {
                        System.out.println("Would you like to change your username?");
                        String changeUsername = scanner.nextLine();
                        if (changeUsername.equalsIgnoreCase("yes")) {
                            System.out.println("What would you like your new username to be?");
                            username = scanner.nextLine();
                            //Update username
                        } else {
                            System.out.println("Would you like to change your password?");
                            String changePassword = scanner.nextLine();
                            if (changePassword.equalsIgnoreCase("yes")) {
                                System.out.println("What would you like your new password to be?");
                                password = scanner.nextLine();
                                //Update password
                            } else {
                                System.out.println("Would you like to delete your account?");
                                String delete = scanner.nextLine();
                                if (delete.equalsIgnoreCase("yes")) {
                                    //Method to delete account
                                } else {
                                    return;
                                }
                            }
                        }
                    } else {
                        System.out.println("Would you like to view your students?");
                        String viewStudent = scanner.nextLine();
                        if (viewStudent.equalsIgnoreCase("yes")) {
                            System.out.println("Which student would you like to view?");
                            String student = scanner.nextLine();
                            //Exception to make sure student is in class
                            //Method to view quiz scores
                        } else {
                            System.out.println("Would you like to see your course class?");
                            String courseClass = scanner.nextLine();
                            if (courseClass.equalsIgnoreCase("yes")) {
                                //Show list of quizzes
                                //return to teacher menu
                            } else {
                                System.out.println("Would you like to edit a quiz?");
                                String quiz = scanner.nextLine();
                                if (quiz.equalsIgnoreCase("yes")) {
                                    System.out.println("Would you like to create a quiz");
                                    String create = scanner.nextLine();
                                    if (create.equalsIgnoreCase("yes")) {
                                        System.out.println("Do you have a file for the quiz?");
                                        String file = scanner.nextLine();
                                        if (file.equals("yes")) {
                                            //method to read file name and create quiz
                                        } else {
                                            System.out.println("Would you like to write questions?");
                                            String write = scanner.nextLine();
                                            if (write.equalsIgnoreCase("yes")) {
                                                //Terminal path in flow chart??
                                            } else {
                                                return;
                                            }
                                        }
                                    } else {
                                        System.out.println("Which quiz would you like to choose?");
                                        String choose = scanner.nextLine();
                                        //Make sure quiz exists (probably throw an exception)
                                        System.out.println("Would you like to view the quiz scores?");
                                        String view = scanner.nextLine();
                                        if (view.equalsIgnoreCase("yes")) {
                                            //method to view quiz scores
                                        } else {
                                            System.out.println("Would you like to edit the quiz?");
                                            String change = scanner.nextLine();
                                            if (change.equalsIgnoreCase("yes")) {
                                                System.out.println("Would you like to delete the quiz?");
                                                String delete = scanner.nextLine();
                                                if (delete.equalsIgnoreCase("yes")) {
                                                    //method to delete quiz
                                                } else {
                                                    System.out.println("Would you like to add questions?");
                                                    String add = scanner.nextLine();
                                                    if (add.equalsIgnoreCase("yes")) {
                                                        //method to add questions
                                                    } else {
                                                        System.out.println("Would you like to modify questions?");
                                                        String modify = scanner.nextLine();
                                                        if (modify.equalsIgnoreCase("yes")) {
                                                            //method to modify questions
                                                        } else {
                                                            System.out.println("Would you like to change the name of the quiz?");
                                                            String name = scanner.nextLine();
                                                            if (name.equalsIgnoreCase("yes")) {
                                                                //method to change quiz name
                                                            } else {
                                                                return;
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }
            } else {
                return;
            }
        }
    }
}
