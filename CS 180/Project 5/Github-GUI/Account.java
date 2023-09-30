import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Account class which contains methods for the creation, login, deletion and editing of accounts.
 *
 * @author Jebran Syed, lab sec L-10
 *
 * @version April 11, 2022
 *
 */

public class Account {

    public ArrayList<String> userNames = new ArrayList<String>();
    public String passwordCA;
    public String userName;
    public String password;
    public String globalUsername;
    public List<String> usernames = new ArrayList<String>();
    public List<String> userLines = new ArrayList<String>();

    public void writeFile(String fileName, String text) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName), true))) {
            pw.println(text);
        }
    }

    public void createAccount() throws IOException {
        Menu menu = new Menu();

        JFrame frame = new JFrame("Welcome!");
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
                String passwordText = String.valueOf(passwordField.getPassword());
                if (usernameField.getText().contains(" ") || passwordText.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "No spaces allowed for the username"
                                    + "and password!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try (BufferedReader br = new BufferedReader(new FileReader("usernames.txt"))) {
                        String line = br.readLine();
                        while (line != null) {
                            usernames.add(line);
                            line = br.readLine();
                        }
                        if (usernames.contains(usernameField.getText())) {
                            JOptionPane.showMessageDialog(null, "This username already exists" +
                                            "please try a different one!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            writeFile("usernames.txt", usernameField.getText());

                            try {
                                String passwordConverted = String.valueOf(passwordField.getPassword());
                                if (buttonGroupTS.getSelection().getActionCommand().equals("Teacher")) {
                                    writeFile("@" + usernameField.getText() + ".txt",
                                            usernameField.getText() + " " +
                                                    passwordConverted + " " + "Teacher");
                                    writeFile("main.txt", "@" + userName + ".txt");
                                } else if (buttonGroupTS.getSelection().getActionCommand().equals("Student")) {
                                    writeFile("#" + usernameField.getText() + ".txt",
                                            usernameField.getText() + " " +
                                                    passwordConverted + " " + "Student");
                                }
                            } catch (IOException er) {
                                System.out.println(er);
                            }

                            frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Your account was created" +
                                            " successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                            menu.initialMenu();
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        content.add(panel2);

        frame.setVisible(true);
    }


    public void login() throws IOException {
        Menu menu = new Menu();

        JFrame frame = new JFrame("Welcome!");
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
                try (BufferedReader br = new BufferedReader(new FileReader("usernames.txt"))) {
                    String line = br.readLine();
                    while (line != null) {
                        usernames.add(line);
                        line = br.readLine();
                    }

                    globalUsername = usernameField.getText();

                    if (!usernames.contains(usernameField.getText())) {
                        JOptionPane.showMessageDialog(null, "This username does not exist",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            File f;
                            if (buttonGroupTS.getSelection().getActionCommand().equals("Teacher")) {
                                f = new File("@" + usernameField.getText() + ".txt");
                            } else {
                                f = new File("#" + usernameField.getText() + ".txt");
                            }
                            try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
                                String passwordConverted = String.valueOf(passwordField.getPassword());
                                String line1 = bfr.readLine();
                                String[] credentialsSplit = line1.split(" ");
                                if (credentialsSplit[0].equals(usernameField.getText()) &&
                                        credentialsSplit[1].equals(passwordConverted)) {
                                    if (buttonGroupTS.getSelection().getActionCommand().equals("Teacher")) {
                                       // menu.teacherFlow(usernameField.getText());
                                        menu.teacherMenu();
                                    } else {
                                        //menu.studentFlow(usernameField.getText());
                                        menu.studentMenu();
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Login Unsuccessful. Try Again!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }

                            }

                        } catch (FileNotFoundException err) {
                            JOptionPane.showMessageDialog(null,
                                    "Login Unsuccessful. Try Again!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException err) {
                            err.printStackTrace();
                        }

                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        content.add(panel2);

        frame.setVisible(true);
    }


    public void deleteAccount(String username, int type) throws IOException {
        File userFile = null;
        if (type == 1)
            userFile = new File("@" + username + ".txt");
        else if (type == 2)
            userFile = new File("#" + username + ".txt");
        userFile.delete();
        usernames.clear();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("usernames.txt")));
        String line = br.readLine();
        while (line != null) {
            if (!line.equalsIgnoreCase(username))
                usernames.add(line);
            line = br.readLine();
        }
        FileWriter writer = new FileWriter("usernames.txt", false);
        for (String str : usernames) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }


    public void editAccount(String username, int type) throws IOException {
        File userFile = null;
        if (type == 1)
            userFile = new File("@" + username + ".txt");
        else if (type == 2)
            userFile = new File("#" + username + ".txt.");
        try (BufferedReader bfr = new BufferedReader(new FileReader(userFile))) {
            String line = bfr.readLine();
            String[] credentialsSplit = line.split(" ");


            JFrame frame = new JFrame("Edit Account");
            Container content = frame.getContentPane();
            content.setLayout(new BorderLayout());

            frame.setSize(370, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            JLabel initialQ = new JLabel("What would you like to do?");
            panel.add(initialQ);
            content.add(panel, BorderLayout.NORTH);
            frame.setVisible(true);

            JPanel panel2 = new JPanel();
            JButton changePasswordButton = new JButton("Change Password");
            changePasswordButton.setBounds(10, 80, 80, 25);
            JButton changeUserButton = new JButton("Change Username");
            changePasswordButton.setBounds(90, 80, 80, 25);
            panel2.add(changeUserButton);

            changePasswordButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame("Change Password");
                    Container content = frame.getContentPane();
                    content.setLayout(new BorderLayout());

                    frame.setSize(370, 200);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    JPanel panel = new JPanel();
                    JLabel initialQ = new JLabel("Enter a new password");
                    JTextField newPasswordField = new JTextField(20);
                    panel.add(initialQ);
                    panel.add(newPasswordField);
                    content.add(panel, BorderLayout.CENTER);
                    frame.setVisible(true);

                    File userFileNew1 = null;
                    if (type == 1)
                        userFileNew1 = new File("@" + username + ".txt");
                    else if (type == 2)
                        userFileNew1 = new File("#" + username + ".txt");

                    try {
                        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(userFileNew1)));
                        String line2 = r.readLine();
                        while (line2 != null) {
                            userLines.add(line2);
                            line2 = r.readLine();
                        }
                        FileWriter w = new FileWriter(userFileNew1);
                        userLines.remove(0);
                        userLines.add(0, username + " " + newPasswordField.getText() + credentialsSplit[2]);
                        for (String str : userLines) {
                            w.write(str + System.lineSeparator());
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            changeUserButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame("Change Username");
                    Container content = frame.getContentPane();
                    content.setLayout(new BorderLayout());

                    frame.setSize(370, 200);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    JPanel panel = new JPanel();
                    JLabel initialQ2 = new JLabel("Enter a new username");
                    JTextField newUserField = new JTextField(20);
                    panel.add(initialQ2);
                    panel.add(newUserField);
                    content.add(panel, BorderLayout.CENTER);
                    frame.setVisible(true);

                    if (type == 1)
                        new File("@" + username + ".txt").renameTo(new File("@" + newUserField.getText() + ".txt"));
                    else if (type == 2)
                        new File("#" + username + ".txt").renameTo(new File("#" + newUserField.getText() + ".txt"));
                    File userFileNew = null;
                    if (type == 1)
                        userFileNew = new File("@" + newUserField + ".txt");
                    else if (type == 2)
                        userFileNew = new File("#" + newUserField + ".txt");

                    try {

                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(userFileNew)));
                        String line1 = br.readLine();
                        while (line1 != null) {
                            userLines.add(line1);
                            line1 = br.readLine();
                        }
                        FileWriter writer = new FileWriter(userFileNew);
                        userLines.remove(0);
                        userLines.add(0, newUserField.getText() + " " + credentialsSplit[1] + credentialsSplit[2]);
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
                        usernames.add(newUserField.getText());

                        FileWriter writer1 = new FileWriter("usernames.txt");
                        for (String str : usernames) {
                            writer1.write(str + System.lineSeparator());
                        }


                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean hasUserNames() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("usernames.txt"));
            return br.readLine() != null;
        } catch (IOException e) {
            return false;
        }
    }

}

/*
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

 */


