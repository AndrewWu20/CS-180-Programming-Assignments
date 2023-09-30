import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Menu {

    private PrintWriter pw;
    private BufferedReader br;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private JFrame frame;
    private JButton back;
    private ArrayList<String> answers;
    private Date date;

    /**
     * Creates menu class with pw and br from client
     * @param pw Writes to server
     * @param br Reads from server
     */
    public Menu(PrintWriter pw, BufferedReader br, ObjectInputStream ois, ObjectOutputStream oos) {

        this.pw = pw;
        this.br = br;
        this.ois = ois;
        this.oos = oos;
        frame = new JFrame();
        back = new JButton("Back");
        answers = new ArrayList<String>();

    }

    public void run() {

        JOptionPane.showMessageDialog(null, "Welcome to the QUIZ39 Platform!",
                "QUIZ39", JOptionPane.INFORMATION_MESSAGE);
        initialMenu();

    }

    private void initialMenu() {
        resetFrame();
        frame = new JFrame("Welcome!");
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
        JButton caButton = new JButton("Create Account");
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");
        panel2.add(caButton);
        panel2.add(loginButton);
        panel2.add(exitButton);
        content.add(panel2);

        caButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createAccountMenu();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                logInMenu();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                pw.println("exit");
            }
        });

        frame.setVisible(true);
    }

    private void teacherMenu() {
        resetFrame();

        frame = new JFrame("Menu");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("What would you like to do today?");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new GridLayout(3, 3));
        JButton createCourseButton = new JButton("Create Course");
        JButton viewCourseQuizzesButton = new JButton("View Course Quizzes");
        JButton editCourseButton = new JButton("Edit Course");
        JButton gradeQuizButton = new JButton("Grade Quiz");
        JButton viewStudentScoresButton = new JButton("View Student Scores");
        JButton editAccountButton = new JButton("Edit Account");
        JButton deleteAccountButton = new JButton("Delete Account");
        JButton exitButton = new JButton("Exit");
        panel2.add(createCourseButton);
        panel2.add(viewCourseQuizzesButton);
        panel2.add(editCourseButton);
        panel2.add(gradeQuizButton);
        panel2.add(viewStudentScoresButton);
        panel2.add(editAccountButton);
        panel2.add(deleteAccountButton);
        panel2.add(exitButton);

        createCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createCourseMenu();
            }
        });

        editCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    selectCourseMenu("editCourse\t.");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        editAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editAccountMenu(1);
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAccountMenu(1);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pw.println("exit");
                frame.dispose();
            }
        });
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                initialMenu();
            }
        });
        content.add(back, BorderLayout.SOUTH);

        /*viewCourseQuizzesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    chooseCourse().printQuizList();
                } catch (NullPointerException err) {
                    System.out.println(err);
                }

            }
        });

        editCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course c = chooseCourse();

                if (c == null) {
                    System.out.println("Not a valid course.");
                } else {
                    JFrame frame = new JFrame("Edit Course");
                    Container content = frame.getContentPane();
                    content.setLayout(new BorderLayout());

                    frame.setSize(500, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                    JPanel panel = new JPanel();
                    JLabel initialQ = new JLabel("What would you like to do today?");
                    panel.add(initialQ);
                    content.add(panel, BorderLayout.NORTH);

                    JPanel panel2 = new JPanel(new GridLayout(3, 3));
                    JButton createQuizButton = new JButton("Create Quiz");
                    JButton editQuizButton = new JButton("Edit/Delete Quiz");
                    JButton viewQuizButton = new JButton("View Quiz");
                    panel2.add(createQuizButton);
                    panel2.add(editQuizButton);
                    panel2.add(viewQuizButton);
                    content.add(panel2);

                    frame.setVisible(true);

                    createCourseButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFrame frame = new JFrame("Edit Course");
                            Container content = frame.getContentPane();
                            content.setLayout(new BorderLayout());

                            frame.setSize(500, 500);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                            JPanel panel = new JPanel();
                            JLabel initialQ = new JLabel("What would you like to do today?");
                            panel.add(initialQ);
                            content.add(panel, BorderLayout.NORTH);

                            JPanel panel2 = new JPanel(new GridLayout(3, 3));
                            JButton importQuizButton = new JButton("Import Quiz");
                            JButton createManualQuizButton = new JButton("Create Quiz");
                            JButton randomizeQuizButton = new JButton("Randomize Quiz");
                            panel2.add(importQuizButton);
                            panel2.add(createManualQuizButton);
                            panel2.add(randomizeQuizButton);
                            content.add(panel2);

                            frame.setVisible(true);

                            importQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String importFileName = "Enter the file path of the file you would like to import.\n" +
                                            "Please only import files with the correct format:\n" +
                                            "@Question\n~question choice\nIncorrectly formatted files will not be imported";
                                    String filepath = JOptionPane.showInputDialog(null,
                                            importFileName, "Import Quiz",
                                            JOptionPane.QUESTION_MESSAGE);
                                    String quizName = JOptionPane.showInputDialog(null,
                                            "Please enter a name for this quiz", "Import Quiz",
                                            JOptionPane.QUESTION_MESSAGE);
                                    Quiz quiz = new Quiz(quizName);
                                    quiz.importQuiz(filepath, quizName);
                                    c.addQuiz(quiz);

                                }
                            });

                            createManualQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String nameQuiz = JOptionPane.showInputDialog(null,
                                            "Please enter a name for this quiz", "Create Quiz",
                                            JOptionPane.QUESTION_MESSAGE);
                                    String questionNumber = JOptionPane.showInputDialog(null,
                                            "Please enter the number of questions you would like for this quiz",
                                            "Create Quiz",
                                            JOptionPane.QUESTION_MESSAGE);
                                    Quiz addQuiz = new Quiz(nameQuiz);
                                    try {
                                        for (int i = 0; i < Integer.parseInt(questionNumber); i++) {
                                            addQuiz.addQuestion(i);
                                        }
                                        if (addQuiz.getQuestionList() != null) {
                                            addQuiz.exportQuizToFile(nameQuiz);
                                        }
                                        c.addQuiz(addQuiz);
                                    } catch (NumberFormatException err) {
                                        JOptionPane.showMessageDialog(null, "Please enter a valid number of questions.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    } catch (InputMismatchException err) {
                                        JOptionPane.showMessageDialog(null, "Please enter valid input",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }
                            });

                            randomizeQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String randomQuiz = JOptionPane.showInputDialog(null,
                                            "Please enter the quiz you would like to randomize", "Create Quiz",
                                            JOptionPane.QUESTION_MESSAGE);
                                    Quiz quizRandom = new Quiz(randomQuiz);
                                    quizRandom.shuffleQuiz(quizRandom.readQuiz(randomQuiz + ".txt", randomQuiz));
                                    quizRandom.exportQuizToFile(randomQuiz);
                                }
                            });
                        }
                    });

                    editQuizButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFrame frame = new JFrame("Edit Quiz");
                            Container content = frame.getContentPane();
                            content.setLayout(new BorderLayout());

                            frame.setSize(500, 500);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                            JPanel panel = new JPanel();
                            JLabel initialQ = new JLabel("Are you editing this quiz or deleting it?");
                            panel.add(initialQ);
                            content.add(panel, BorderLayout.NORTH);

                            JPanel panel2 = new JPanel(new GridLayout(2, 1));
                            JButton editQuizButton = new JButton("Edit Quiz");
                            JButton deleteQuizButton = new JButton("Delete Quiz");
                            panel2.add(editQuizButton);
                            panel2.add(deleteQuizButton);
                            content.add(panel2);

                            frame.setVisible(true);

                            editQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String editQuiz = JOptionPane.showInputDialog(null,
                                            "Enter the name of the quiz you would like to edit", "Edit Quiz",
                                            JOptionPane.QUESTION_MESSAGE);

                                    while (c.getQuiz(editQuiz) == null) {
                                        JOptionPane.showMessageDialog(null, "Please enter a valid quiz name.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                    Quiz quizedit = new Quiz(editQuiz);
                                    quizedit.setQuizName(editQuiz);
                                    quizedit.readQuiz(editQuiz + ".txt", editQuiz);

                                    JFrame frame = new JFrame("Edit Quiz");
                                    Container content = frame.getContentPane();
                                    content.setLayout(new BorderLayout());

                                    frame.setSize(500, 500);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                                    JPanel panel = new JPanel();
                                    JLabel initialQ = new JLabel("Would you like to:");
                                    panel.add(initialQ);
                                    content.add(panel, BorderLayout.NORTH);

                                    JPanel panel2 = new JPanel(new GridLayout(3, 3));
                                    JButton addQButton = new JButton("Add Question/Choice");
                                    JButton deleteQButton = new JButton("Delete Question/Choice");
                                    JButton modifyQButton = new JButton("Modify Question/Choice");
                                    panel2.add(addQButton);
                                    panel2.add(deleteQButton);
                                    panel2.add(modifyQButton);
                                    content.add(panel2);

                                    frame.setVisible(true);

                                    addQButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            JOptionPane.showMessageDialog(null,
                                                    quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString(),
                                                    "Success", JOptionPane.INFORMATION_MESSAGE);

                                            JFrame frame = new JFrame("Edit Quiz");
                                            Container content = frame.getContentPane();
                                            content.setLayout(new BorderLayout());

                                            frame.setSize(500, 500);
                                            frame.setLocationRelativeTo(null);
                                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                                            JPanel panel = new JPanel();
                                            JLabel initialQ = new JLabel("Would you like to:");
                                            panel.add(initialQ);
                                            content.add(panel, BorderLayout.NORTH);

                                            JPanel panel2 = new JPanel(new GridLayout(3, 3));
                                            JButton addQ2Button = new JButton("Add Question");
                                            JButton addChoice2Button = new JButton("Add Choice");
                                            panel2.add(addQ2Button);
                                            panel2.add(addChoice2Button);
                                            content.add(panel2);

                                            frame.setVisible(true);

                                            addQ2Button.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    int addQ = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                            "Enter the number of questions you would like to add", "Edit Quiz",
                                                            JOptionPane.QUESTION_MESSAGE));

                                                    for (int i = 0; i < addQ; i++) {
                                                        quizedit.addQuestion(i);
                                                    }
                                                    quizedit.exportQuizToFile(editQuiz);
                                                    JOptionPane.showMessageDialog(null,
                                                            "The questions have been added!",
                                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                                    JOptionPane.showMessageDialog(null,
                                                            quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString(),
                                                            "Success", JOptionPane.INFORMATION_MESSAGE);

                                                }
                                            });
                                            addChoice2Button.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    int choiceQuestion = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                            "Choose which question you would like to add a choice to", "Edit Quiz",
                                                            JOptionPane.QUESTION_MESSAGE));

                                                    int choiceNumber = quizedit.getQuestionList().get(choiceQuestion - 1).getChoiceList().size();
                                                    quizedit.getQuestionList().get(choiceQuestion - 1).addChoice(choiceNumber);
                                                    quizedit.exportQuizToFile(editQuiz);
                                                }
                                            });

                                        }
                                    });

                                    deleteQButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {

                                            JOptionPane.showMessageDialog(null,
                                                    quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString(),
                                                    "Success", JOptionPane.INFORMATION_MESSAGE);

                                            JFrame frame = new JFrame("Edit Quiz");
                                            Container content = frame.getContentPane();
                                            content.setLayout(new BorderLayout());

                                            frame.setSize(500, 500);
                                            frame.setLocationRelativeTo(null);
                                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                                            JPanel panel = new JPanel();
                                            JLabel initialQ = new JLabel("Would you like to:");
                                            panel.add(initialQ);
                                            content.add(panel, BorderLayout.NORTH);

                                            JPanel panel2 = new JPanel(new GridLayout(3, 3));
                                            JButton deleteQ2Button = new JButton("Delete Question");
                                            JButton deleteChoice2Button = new JButton("Delete Choice");
                                            panel2.add(deleteQ2Button);
                                            panel2.add(deleteChoice2Button);
                                            content.add(panel2);

                                            frame.setVisible(true);

                                            deleteQ2Button.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    int deleteQ = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                            "Enter the question number you would like to delete", "Edit Quiz",
                                                            JOptionPane.QUESTION_MESSAGE));

                                                    quizedit.deleteQuestion(deleteQ);
                                                    quizedit.exportQuizToFile(editQuiz);
                                                    JOptionPane.showMessageDialog(null,
                                                            "The question has been deleted!",
                                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                                    JOptionPane.showMessageDialog(null,
                                                            quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString(),
                                                            "Success", JOptionPane.INFORMATION_MESSAGE);

                                                }
                                            });

                                            deleteChoice2Button.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    int dltQuestion = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                            "Choose which question you would like to delete a choice from", "Edit Quiz",
                                                            JOptionPane.QUESTION_MESSAGE));
                                                    int choicedlt = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                            "Which choice would you like to delete", "Edit Quiz",
                                                            JOptionPane.QUESTION_MESSAGE));
                                                    quizedit.getQuestionList().get(dltQuestion - 1).deleteChoice(choicedlt);
                                                    quizedit.exportQuizToFile(editQuiz);

                                                }
                                            });
                                        }
                                    });

                                    modifyQButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            JOptionPane.showMessageDialog(null,
                                                    quizedit.readQuiz(editQuiz + ".txt", editQuiz).toString(),
                                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                                            quizedit.editQuestion();
                                            quizedit.exportQuizToFile(editQuiz);
                                        }
                                    });

                                }
                            });

                            deleteQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String nameOfQuizToDelete = JOptionPane.showInputDialog(null,
                                            "Enter the name of the quiz you wish to delete", "Edit Quiz",
                                            JOptionPane.QUESTION_MESSAGE);
                                    c.printQuizList();
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
                                    JOptionPane.showMessageDialog(null,
                                            nameOfQuizToDelete + " has been deleted",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                }
                            });

                            viewQuizButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String quizzName = JOptionPane.showInputDialog(null,
                                            "Enter a quiz name to view the quiz", "Edit Quiz",
                                            JOptionPane.QUESTION_MESSAGE);

                                    quizzName = quizzName + s.next();
                                    Quiz quiz = new Quiz(quizzName);
                                    JOptionPane.showMessageDialog(null,
                                            quiz.readQuiz(quizzName + ".txt", quizzName).toString(),
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                }
                            });

                        }
                    });
                }
            }
        });

        gradeQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course c = chooseCourse();

                if (c == null) {
                    JOptionPane.showMessageDialog(null,
                            "Not a valid course.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String quizName = JOptionPane.showInputDialog(null,
                            "Which quiz would you like to grade?", "Edit Quiz",
                            JOptionPane.QUESTION_MESSAGE);
                    c.printQuizList();

                    while (courseList.get(checkCourseExistence(c.getCourseName())).getQuiz(quizName) == null) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid quiz.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        quizName = JOptionPane.showInputDialog(null,
                                "Which quiz would you like to grade?", "Edit Quiz",
                                JOptionPane.QUESTION_MESSAGE);
                    }

                    ArrayList<String> submissions = t.parseSubmissions(checkCourseExistence(c.getCourseName()), quizName);

                    String studentName = JOptionPane.showInputDialog(null,
                            "Which student would you like to grade?", "Edit Quiz",
                            JOptionPane.QUESTION_MESSAGE);

                    while (t.checkStudentSubmission(submissions, studentName) == -1) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid student name who has taken the quiz",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        studentName = JOptionPane.showInputDialog(null,
                                "Which student would you like to grade?", "Edit Quiz",
                                JOptionPane.QUESTION_MESSAGE);
                    }

                    int totalAttempts = t.printSubmissions(submissions, t.checkStudentSubmission(submissions, studentName), studentName);

                    String attemptNumber = JOptionPane.showInputDialog(null,
                            "Which submission would you like to grade?\nTotal Submissions: " + totalAttempts, "Edit Quiz",
                            JOptionPane.QUESTION_MESSAGE);

                    int attempt = 0;
                    boolean cont2 = true;
                    do {
                        try {
                            attempt = Integer.parseInt(attemptNumber);
                            cont2 = false;
                        } catch (NumberFormatException err) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter a valid attempt number",
                                    "Error", JOptionPane.ERROR_MESSAGE);
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
                            JOptionPane.showMessageDialog(null,
                                    "Question: " + line, "Success", JOptionPane.INFORMATION_MESSAGE);
                            pw.print(line + "\t");
                            line = br.readLine();
                            JOptionPane.showMessageDialog(null,
                                    "Points: " + line, "Success", JOptionPane.INFORMATION_MESSAGE);
                            pw.println(line + " points");
                            line = br.readLine();
                            JOptionPane.showMessageDialog(null,
                                    "Answer: " + line, "Success", JOptionPane.INFORMATION_MESSAGE);
                            boolean isValid = true;
                            String scoreStr = JOptionPane.showInputDialog(null,
                                    "Enter the student's score for this question below." + totalAttempts, "Edit Quiz",
                                    JOptionPane.QUESTION_MESSAGE);
                            double score = 0;
                            do {
                                try {
                                    score = Double.parseDouble(scoreStr);
                                } catch (NumberFormatException err) {
                                    isValid = false;
                                    JOptionPane.showMessageDialog(null,
                                            "Please enter a valid score",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (!isValid || score < 0);
                            pw.println(score);
                            line = br.readLine();
                        }
                        br.close();
                        pw.close();
                    } catch (IOException err) {
                        JOptionPane.showMessageDialog(null,
                                studentName + "\n" + quizName + "\n" + attempt + "\n", "Success", JOptionPane.INFORMATION_MESSAGE);
                        err.printStackTrace();
                    }

                }
            }
        });

        viewStudentScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course c = chooseCourse();
                String quizName = JOptionPane.showInputDialog(null,
                        "Which quiz would you like to view?", "Edit Quiz",
                        JOptionPane.QUESTION_MESSAGE);
                c.printQuizList();
                String studentName = JOptionPane.showInputDialog(null,
                        "Which student's scores would you like to view?", "Edit Quiz",
                        JOptionPane.QUESTION_MESSAGE);
                String submissionNumber = JOptionPane.showInputDialog(null,
                        "Which submission would you like to see?", "Edit Quiz",
                        JOptionPane.QUESTION_MESSAGE);
                parseScores(c, studentName, quizName, submissionNumber);
            }
        });

        editAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    a.editAccount(1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    a.deleteAccount(a.globalUsername, 1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });*/

        content.add(panel2);
        frame.setVisible(true);
    }

    private void selectCourseMenu(String request) throws IOException, ClassNotFoundException {
        resetFrame();

        frame = new JFrame("Which course would you like to edit?");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pw.println("getCourseNames");
        pw.flush();

        ArrayList<String> courseNames = (ArrayList<String>) ois.readObject();

        JList courseList = new JList(courseNames.toArray());
        courseList.setPreferredSize(new Dimension(450, 300));
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel coursesLabel = new JLabel("Courses:");
        JButton enter = new JButton("View Course");
        content.add(new JScrollPane(courseList), BorderLayout.CENTER);
        content.add(coursesLabel, BorderLayout.NORTH);
        content.add(enter, BorderLayout.SOUTH);

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (courseList.getSelectedIndex() != -1) {
                    pw.println(request + courseNames.get(courseList.getSelectedIndex()));
                    pw.flush();
                    try {
                        editCourseMenu(courseNames.get(courseList.getSelectedIndex()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Please select a Course.",
                            "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private void createCourseMenu() {
        resetFrame();

        frame = new JFrame("Teacher");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(370, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("What name would you like to give this Course");
        JTextField courseName = new JTextField(20);
        JButton enter = new JButton("Enter");
        panel.add(initialQ);
        panel.add(courseName);
        panel.add(enter);

        content.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String course = courseName.getText();
                pw.println("createCourse\t." + course);
                pw.flush();
                try{
                    String path = br.readLine();
                    System.out.println(path);
                    if (path.equals("true")) {
                        teacherMenu();
                    } else if (path.equals("false")) {
                        createCourseMenu();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void studentMenu() {
        resetFrame();

        frame = new JFrame("Student Menu");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("What would you like to do today?");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new GridLayout(3, 1));
        JButton viewCourseButton = new JButton("View a course");
        JButton editAccountButton = new JButton("Edit Account");
        JButton deleteAccountButton = new JButton("Delete Account");
        panel2.add(viewCourseButton);
        panel2.add(editAccountButton);
        panel2.add(deleteAccountButton);
        content.add(panel2);

        viewCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    listCoursesMenu();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        editAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editAccountMenu(2);
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deleteAccountMenu(2);
            }
        });
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                initialMenu();
            }
        });
        content.add(back, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void logInMenu() {

        resetFrame();

        frame = new JFrame("Welcome!");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(370, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        ButtonGroup buttonGroupTS = new ButtonGroup();
        JLabel initialQ = new JLabel("Are you a teacher or a student?");
        JRadioButton teacherButton = new JRadioButton("Teacher");
        teacherButton.setSelected(true);
        teacherButton.setActionCommand(teacherButton.getText());
        JRadioButton studentButton = new JRadioButton("Student");
        studentButton.setActionCommand(studentButton.getText());
        buttonGroupTS.add(teacherButton);
        buttonGroupTS.add(studentButton);
        panel.add(initialQ);
        panel.add(teacherButton);
        panel.add(studentButton);
        content.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);

        JPanel panel2 = new JPanel();
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel2.add(userLabel);

        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel2.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel2.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel2.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel2.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String usernameText = usernameField.getText();
                String passwordText = String.valueOf(passwordField.getPassword());
                String buttonGroupText = buttonGroupTS.getSelection().getActionCommand();
                pw.println("logIn\t." + usernameText + "\t." + passwordText + "\t." + buttonGroupText);
                pw.flush();
                try {
                    String path = br.readLine();
                    System.out.println(path);
                    if (path.equals("true")) {
                        if (buttonGroupText.equals("Teacher")) {
                            teacherMenu();
                        } else if (buttonGroupText.equals("Student")) {
                            studentMenu();
                        }
                    } else if (path.equals("false")){
                        logInMenu();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                initialMenu();
            }
        });
        content.add(back, BorderLayout.SOUTH);

        content.add(panel2);

        frame.setVisible(true);

    }

    private void createAccountMenu() {

        resetFrame();

        frame = new JFrame("Welcome!");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(370, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        ButtonGroup buttonGroupTS = new ButtonGroup();
        JLabel initialQ = new JLabel("Are you a teacher or a student?");
        JRadioButton teacherButton = new JRadioButton("Teacher");
        teacherButton.setActionCommand(teacherButton.getText());
        teacherButton.setSelected(true);
        JRadioButton studentButton = new JRadioButton("Student");
        studentButton.setActionCommand(studentButton.getText());
        buttonGroupTS.add(teacherButton);
        buttonGroupTS.add(studentButton);
        panel.add(initialQ);
        panel.add(teacherButton);
        panel.add(studentButton);
        content.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);

        JPanel panel2 = new JPanel();
        JLabel userLabel = new JLabel("Set a Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel2.add(userLabel);

        JTextField usernameField = new JTextField(15);
        usernameField.setBounds(100, 20, 165, 25);
        panel2.add(usernameField);

        JLabel passwordLabel = new JLabel("Set a Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel2.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(100, 50, 165, 25);
        panel2.add(passwordField);

        JButton caButton = new JButton("Create Account");
        caButton.setBounds(10, 80, 80, 25);
        panel2.add(caButton);

        caButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String usernameText = usernameField.getText();
                String passwordText = String.valueOf(passwordField.getPassword());
                String buttonGroupText = buttonGroupTS.getSelection().getActionCommand();
                pw.println("createAccount\t." + usernameText + "\t." + passwordText + "\t." + buttonGroupText);
                pw.flush();
                try {
                    String path = br.readLine();
                    System.out.println(path);
                    if (path.equals("true")) {
                        initialMenu();
                    } else {
                        createAccountMenu();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                initialMenu();
            }
        });
        content.add(back, BorderLayout.SOUTH);
        content.add(panel2);


        frame.setVisible(true);

    }

    private void resetFrame() {
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    private void listCoursesMenu() throws IOException, ClassNotFoundException {
        resetFrame();

        frame = new JFrame("Courses");
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pw.println("getCourseNames");
        pw.flush();

        ArrayList<String> courseNames = (ArrayList<String>) ois.readObject();

        JList courseList = new JList(courseNames.toArray());
        //courseList.setPreferredSize(new Dimension(450, 300));
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel coursesLabel = new JLabel("Courses:");
        JButton enter = new JButton("View Course");
        container.add(new JScrollPane(courseList), BorderLayout.CENTER);
        container.add(coursesLabel, BorderLayout.NORTH);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(enter, BorderLayout.CENTER);

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (courseList.getSelectedIndex() != -1) {
                    String courseName = courseNames.get(courseList.getSelectedIndex());
                    frame.dispose();
                    try {
                        listQuizzesMenu(courseName);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                studentMenu();
            }
        });
        panel.add(back, BorderLayout.SOUTH);
        container.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    private void listQuizzesMenu(String courseName) throws IOException, ClassNotFoundException {
        resetFrame();

        frame = new JFrame(courseName);
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pw.println("getQuizNames\t." + courseName);
        pw.flush();

        ArrayList<String> quizNamesList = (ArrayList<String>) ois.readObject();

        pw.println("getQuizList\t." + courseName);
        pw.flush();

        ArrayList<Quiz> quizList = (ArrayList<Quiz>) ois.readObject();

        JList quizJList = new JList(quizNamesList.toArray());
        quizJList.setPreferredSize(new Dimension(450, 300));
        quizJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel quizLabel = new JLabel("Quizzes:");
        JButton takeQuiz = new JButton("Take Quiz");
        JButton seeScore = new JButton("View Submission Scores");
        content.add(new JScrollPane(quizJList), BorderLayout.CENTER);
        content.add(quizLabel, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.add(takeQuiz, BorderLayout.CENTER);
        panel.add(seeScore, BorderLayout.EAST);
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    listCoursesMenu();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(back, BorderLayout.WEST);

        content.add(panel, BorderLayout.SOUTH);

        takeQuiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                date = new Date();
                takeQuizMenu(quizList.get(quizJList.getSelectedIndex()), 0);
            }
        });

        seeScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        frame.setVisible(true);
    }

    private void takeQuizMenu(Quiz q, int questionIndex) {
        resetFrame();
        if (questionIndex >= q.getQuestionList().size()) {
            JOptionPane.showMessageDialog(null, "YOU ARE DONE CONGORATS!!!1",
                    "END", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();

            studentMenu();
        } else {
            frame = new JFrame(q.getQuizName());
            Container content = frame.getContentPane();
            content.setLayout(new BorderLayout());

            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel question = new JLabel();

            frame.setVisible(true);
            System.out.println(q.getQuestionList().get(questionIndex).getQuestionMessage());
            question = new JLabel(q.getQuestionList().get(questionIndex).getQuestionMessage());
            Question currQuestion = q.getQuestionList().get(questionIndex);
            question.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
            Box box = Box.createVerticalBox();
            JButton enter = new JButton("Enter");
            JButton back = new JButton("Back");
            ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
            ButtonGroup group = new ButtonGroup();

            int answerIndex = -1;

            for (int i = 0; i < currQuestion.getChoiceList().size(); i++) {
                buttons.add(new JRadioButton(currQuestion.getChoiceList().get(i)));
                box.add(buttons.get(i));
                try {
                    if (answers.get(questionIndex).equals(currQuestion.getChoiceList().get(i))) {
                        buttons.get(i).setSelected(true);
                        answerIndex = i;
                    }
                } catch (IndexOutOfBoundsException e) {
                    answerIndex = -1;
                }
                group.add(buttons.get(i));
            }

            if (answerIndex == -1)
                buttons.get(0).setSelected(true);

            content.add(box, BorderLayout.CENTER);
            content.add(question, BorderLayout.NORTH);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(enter, BorderLayout.CENTER);
            if (questionIndex != 0)
                panel.add(back, BorderLayout.WEST);

            content.add(panel, BorderLayout.SOUTH);

            enter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean selected = false;
                    for (JRadioButton b : buttons) {
                        if (b.isSelected())
                            selected = true;
                    }
                    if (!selected) {
                        JOptionPane.showMessageDialog(null, "Please choose an answer.",
                                "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        frame.dispose();
                        String answerText = "";
                        for (JRadioButton b:buttons) {
                            if (b.isSelected())
                                answerText = b.getText();
                        }
                        answers.add(questionIndex, answerText);
                        pw.println();
                        quizIterator(q, questionIndex);
                    }
                }
            });

            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (questionIndex != 0) {
                        frame.dispose();
                        quizDecrementor(q, questionIndex);
                    }
                }
            });
        }

    }

    public void quizIterator(Quiz q, int index) {
        takeQuizMenu(q, ++index);
    }

    public void quizDecrementor(Quiz q, int index) {
        takeQuizMenu(q, --index);
    }

    private void editAccountMenu(int type) {
        frame.dispose();
        resetFrame();

        frame = new JFrame("Edit Account");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(370, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("What would you like to do?");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(10, 80, 80, 25);
        panel2.add(changePasswordButton);
        JButton changeUserButton = new JButton("Change Username");
        changePasswordButton.setBounds(90, 80, 80, 25);
        panel2.add(changeUserButton);
        content.add(panel2, BorderLayout.SOUTH);


        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePasswordMenu(type);
            }
        });

        changeUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeUserNameMenu(type);
            }
        });
        frame.setVisible(true);
    }

    private void changePasswordMenu(int type) {
        frame.dispose();
        resetFrame();
        frame = new JFrame("Change Password");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(370, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("Enter a new password");
        JPasswordField newPasswordField = new JPasswordField(20);
        JButton enter = new JButton("Enter");
        panel.add(initialQ);
        panel.add(newPasswordField);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                pw.println("changePassword\t." + type + "\t." + newPasswordField.getText());
                pw.flush();

                try {
                    String path = br.readLine();
                    if (path.equals("true")) {
                        if (type == 1)
                            teacherMenu();
                        else
                            studentMenu();
                    } else {
                        changePasswordMenu(type);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void changeUserNameMenu(int type) {
        frame.dispose();
        resetFrame();
        frame = new JFrame("Change Username");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(370, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel initialQ2 = new JLabel("Enter a new username");
        JTextField newUserField = new JTextField(20);
        JButton enter = new JButton("Enter");
        panel.add(initialQ2);
        panel.add(newUserField);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                pw.println("changeUserName\t." + type + "\t." + newUserField.getText());
                pw.flush();
                try {
                    String path = br.readLine();
                    if (path.equals("true")) {
                        if (type == 1)
                            teacherMenu();
                        else
                            studentMenu();
                    } else {
                        changeUserNameMenu(type);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.setVisible(true);
    }

    private void deleteAccountMenu(int type) {
        resetFrame();
        pw.println("deleteAccount\t." + type);
        frame.dispose();
        initialMenu();
    }

    private void editCourseMenu(String courseName) throws IOException, ClassNotFoundException {
        JFrame frame = new JFrame("Edit " + courseName);
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel(courseName);
        //add stuff for creating a course drop down using an updated version of courses
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        JButton editCourseName = new JButton("Edit Course Name");
        panel2.add(editCourseName);
        content.add(panel2, BorderLayout.NORTH);

        JPanel panel3 = new JPanel();
        pw.println("getQuizNames");
        pw.flush();

        //ArrayList<String> quizNames = (ArrayList<String>) ois.readObject();
        //JList quizList = new JList(quizNames.toArray());
        //quizList.setPreferredSize(new Dimension(450, 300));
        //quizList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel coursesLabel = new JLabel("Quizzes:");
        panel3.add(coursesLabel);
        //panel3.add(quizList);
        content.add(panel3, BorderLayout.CENTER);


        JPanel panel4 = new JPanel(new GridLayout(3, 3));
        JButton createQuizButton = new JButton("Create Quiz");
        JButton editQuizButton = new JButton("Edit/Delete Quiz");
        JButton viewQuizButton = new JButton("View Quiz");
        panel4.add(createQuizButton);
        panel4.add(editQuizButton);
        panel4.add(viewQuizButton);
        content.add(panel4, BorderLayout.SOUTH);

        frame.setVisible(true);

        editCourseName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editCourseNameMenu();
            }
        });

        createQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createQuizMenu("");
            }
        });


    }

    private void importQuizMenu() {
        resetFrame();

        JFrame frame = new JFrame("Edit Course");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("Import a Quiz File");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new GridLayout(7, 0));
        JLabel formatA = new JLabel("Enter the file path of the file you would like to import");
        JLabel formatB = new JLabel("Please only import files with the correct format:");
        JLabel formatC = new JLabel("@Question");
        JLabel formatD = new JLabel("~question choice");
        JLabel formatE = new JLabel("Incorrectly formatted files will not be imported");

        JTextField importFilePath = new JTextField(8);
        JButton enterImport = new JButton("Import");
        panel2.add(formatA);
        panel2.add(formatB);
        panel2.add(formatC);
        panel2.add(formatD);
        panel2.add(formatE);
        panel2.add(importFilePath);
        panel2.add(enterImport);
        content.add(panel2, BorderLayout.CENTER);

        frame.setVisible(true);


        enterImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                enterQuizMenu(importFilePath.getText());
            }
        });

    }

    private void enterQuizMenu(String filePath) {
        resetFrame();

        JFrame frame = new JFrame("Edit Course");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("Enter a Quiz Name for the Imported file");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new GridLayout(3, 3));
        JLabel nameQuiz = new JLabel("Please enter a name for the quiz");
        JTextField newQuizname = new JTextField(20);
        JButton enter = new JButton("Enter");
        panel2.add(nameQuiz);
        panel2.add(newQuizname);
        panel2.add(enter);
        content.add(panel2, BorderLayout.NORTH);

        frame.setVisible(true);

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Success! Quiz Imported!!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                try {
                    editCourseMenu("");
                } catch (IOException |ClassNotFoundException cfe) {
                    cfe.printStackTrace();
                }
            }
        });

    }

    private void createQuizMenu(String quizName) {
        JFrame frame = new JFrame("Edit Course");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        JLabel initialQ = new JLabel("What would you like to do today?");
        panel.add(initialQ);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new GridLayout(3, 3));
        JButton importQuizButton = new JButton("Import Quiz");
        JButton createManualQuizButton = new JButton("Create Quiz Manually");
        JButton randomizeQuizButton = new JButton("Randomize Quiz");
        panel2.add(importQuizButton);
        panel2.add(createManualQuizButton);
        panel2.add(randomizeQuizButton);
        content.add(panel2);

        frame.setVisible(true);

        importQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                importQuizMenu();
            }
        });

        randomizeQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    randomizeQuizMenu(quizName);
                }catch (IOException ioe) {
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No quizzes available, create a quiz first!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    createQuizMenu(quizName);
                } catch (ClassNotFoundException cfe) {
                    cfe.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No quizzes available, create a quiz first!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    createQuizMenu(quizName);
                }
            }
        });

        createManualQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createManualQuizMenu();
            }
        });

    }

    private void randomizeQuizMenu(String quizName) throws IOException, ClassNotFoundException {
        resetFrame();

        frame = new JFrame();
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton randomizeQuiz = new JButton("Randomize Quiz:" + quizName);
        panel.add(randomizeQuiz, BorderLayout.NORTH);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createQuizMenu("");
            }
        });
        panel2.add(back, BorderLayout.WEST);
        content.add(panel2, BorderLayout.SOUTH);

        frame.setVisible(true);

        randomizeQuiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //select the quiz name -> quiz.txt and pass to the server
                //server gets the quiz name and passes the parameters into the randomize function which should print
                //out the randomized quiz and overwrite the same name file
                //display this file to the user
                //readQUiz (takes in filename, and quiz name ) -> shuffle Quiz (Quiz object from read quiz) -> shuffle quiz
                //display the new randomized file into the gui
                Quiz randomQuiz = new Quiz();
                randomQuiz.shuffleQuiz(randomQuiz.readQuiz(quizName + ".txt", quizName));
                randomQuiz.exportQuizToFile(quizName);
                displayRandomQuizMenu(quizName);
                JOptionPane.showMessageDialog(null, "Success! Quiz Randomized!!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    private void displayRandomQuizMenu(String quizName) {
        resetFrame();

        frame = new JFrame();
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel randomizeQuiz = new JLabel("Randomized Quiz: " + quizName);
        panel.add(randomizeQuiz);
        content.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    randomizeQuizMenu(quizName);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Success! Quiz Randomized!!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        randomizeQuizMenu(quizName);
                    } catch (IOException | ClassNotFoundException cfe) {
                        cfe.printStackTrace();
                    }
                }
            }
        });
        panel2.add(back, BorderLayout.WEST);

        content.add(panel2, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void createManualQuizMenu() {

        resetFrame();

        frame = new JFrame("Create Quiz ");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(370, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextField courseName = new JTextField("", 20);

        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel questionNum = new JLabel("Enter the number of questions you plan on adding");
        JTextField numQuestionsAsText = new JTextField();
        JButton makeQuestions = new JButton("Submit");




        panel2.add(makeQuestions, BorderLayout.SOUTH);
        panel2.add(numQuestionsAsText, BorderLayout.CENTER);
        panel2.add(questionNum, BorderLayout.NORTH);
        content.add(panel2, BorderLayout.CENTER);

        frame.setVisible(true);
        Quiz quiz = new Quiz();
        makeQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                addQuestions(quiz, Integer.parseInt(numQuestionsAsText.getText()), 0);

            }
        });

    }

    private void addQuestions(Quiz quiz, int numQuestions, int index) {

        resetFrame();

        if (index >= numQuestions) {

            frame.dispose();

            studentMenu();

        } else {

            frame = new JFrame("Question " + (index + 1));
            Container content = frame.getContentPane();
            content.setLayout(new BorderLayout());

            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel question = new JLabel();

            frame.setVisible(true);



            JTextField questionMessageAsBox = new JTextField("Enter the message for this question");

            JTextField pointsAsBox = new JTextField("Enter the number of points this question is worth");
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(questionMessageAsBox, BorderLayout.CENTER);
            panel.add(pointsAsBox, BorderLayout.EAST);
            JButton submit = new JButton("Submit");
            content.add(panel, BorderLayout.NORTH);
            content.add(submit, BorderLayout.SOUTH);


            JTextField tf1 = new JTextField("Choice a:");
            JTextField tf2 = new JTextField("Choice b:");
            JTextField tf3 = new JTextField("Choice c:");
            JTextField tf4 = new JTextField("Choice d:");

            Box box = Box.createVerticalBox();
            box.add(tf1);
            box.add(tf2);
            box.add(tf3);
            box.add(tf4);

            content.add(box, BorderLayout.CENTER);


            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
  /*                  String questionMessage = questionMessageAsBox.getText();
                    double points = Double.parseDouble(pointsAsBox.getText());
                    ArrayList<String> choices = new ArrayList<String>();
                    choices.add(tf1.getText());
                    choices.add(tf2.getText());
                    choices.add(tf3.getText());
                    choices.add(tf4.getText());
                    Question thisQuestion = new Question(questionMessage, points);
                    thisQuestion.setChoiceList(choices);
                    pw.println("addQuestion");
                    try {
                        oos.writeObject(thisQuestion);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
*/
                    try {
                        editCourseMenu("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (index != 0) {
                        frame.dispose();
                    }
                }
            });
        }

    }

    private void questionIterator(Quiz quiz, int numQuestions, int index) {
        addQuestions(quiz, numQuestions, ++index);
    }

    private void questionDecrementor(Quiz quiz, int numQuestions, int index) {
        addQuestions(quiz, numQuestions, --index);
    }

    private void editCourseNameMenu() {
        JFrame editName = new JFrame("Edit Course Name");
        Container content = editName.getContentPane();
        JLabel namePrompt = new JLabel("Enter the new title");
        JTextField nameAsBox = new JTextField(20);
        JButton submit = new JButton("Submit");
        JPanel namePanel = new JPanel();
        namePanel.add(namePrompt);
        namePanel.add(nameAsBox);
        namePanel.add(submit);
        content.add(namePanel, BorderLayout.CENTER);
        editName.setVisible(true);


        editName.setSize(500, 100);
        editName.setLocationRelativeTo(null);
        editName.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newCourseName = nameAsBox.getText();
                pw.println("editCourseName" + "\t." + newCourseName);
                pw.flush();
                editName.dispose();
                try {
                    editCourseMenu(newCourseName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }




}
