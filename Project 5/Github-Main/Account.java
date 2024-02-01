import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Account class which contains methods for the creation, login, deletion and editing of accounts.
 *
 * @author Jebran Syed, lab sec L-10
 * @version April 11, 2022
 */

public class Account {

    public ArrayList<String> userNames = new ArrayList<String>();
    public String passwordCA;
    public String globalUsername;
    public List<String> usernames = new ArrayList<String>();
    public List<String> userLines = new ArrayList<String>();
    public List<String> mainLines = new ArrayList<String>();

    public void writeFile(String fileName, String text) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName), true))) {
            pw.println(text);
        }
    }

    public boolean createAccount(String username, String password, String type) throws IOException {
        if (username.contains(" ") || password.contains(" ")) {
            JOptionPane.showMessageDialog(null, "No spaces allowed for the username "
                    + "and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader("usernames.txt"))) {
                String line = br.readLine();
                while (line != null) {
                    usernames.add(line);
                    line = br.readLine();
                }
                if (usernames.contains(username)) {
                    JOptionPane.showMessageDialog(null, "This username already exists " +
                            "please try a different one!", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    writeFile("usernames.txt", username);
                    try {
                        if (type.equals("Teacher")) {
                            writeFile("@" + username + ".txt", username + " " +
                                    password + " " + "Teacher");
                            writeFile("main.txt", "@" + username + ".txt");
                        } else if (type.equals("Student")) {
                            writeFile("#" + username + ".txt", username + " " +
                                    password + " " + "Student");
                            writeFile("main.txt", "#" + username + ".txt");
                        }
                    } catch (IOException er) {
                        System.out.println(er);
                    }
                    JFrame frame = new JFrame();
                    frame.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(frame, "Your account was created" +
                            " successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

    public boolean login(String username, String password, String type) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("usernames.txt"))) {
            String line = br.readLine();
            while (line != null) {
                usernames.add(line);
                line = br.readLine();
            }

            if (!usernames.contains(username)) {
                JOptionPane.showMessageDialog(null, "This username does not exist",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                try {
                    File f;
                    if (type.equals("Teacher")) {
                        f = new File("@" + username + ".txt");
                    } else {
                        f = new File("#" + username + ".txt");
                    }
                    try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
                        String line1 = bfr.readLine();
                        String[] credentialsSplit = line1.split(" ");
                        if (credentialsSplit[0].equals(username) &&
                                credentialsSplit[1].equals(password)) {
                            globalUsername = username;
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Login Unsuccessful. Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    }
                } catch (FileNotFoundException err) {
                    JOptionPane.showMessageDialog(null,
                            "Login Unsuccessful. Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                } catch (IOException err) {
                    err.printStackTrace();
                    return false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void deleteAccount(int type) throws IOException {
        System.out.println("Reached Delete Accunt");
        File userFile = null;
        String identifier = "";
        if (type == 1) {
            userFile = new File("@" + globalUsername + ".txt");
            identifier = "@";
        }
        else if (type == 2) {
            userFile = new File("#" + globalUsername + ".txt");
            identifier = "#";
        }
        userFile.delete();
        usernames.clear();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("usernames.txt")));
        String line = br.readLine();
        while (line != null) {
            if (!line.equalsIgnoreCase(globalUsername))
                usernames.add(line);
            line = br.readLine();
        }
        FileWriter writer = new FileWriter("usernames.txt", false);
        for (String str : usernames) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        br.close();

        BufferedReader mainReader = new BufferedReader(new InputStreamReader(new FileInputStream("main.txt")));
        String mainLine = mainReader.readLine();
        while (mainLine != null) {
            if (!mainLine.equalsIgnoreCase(identifier + globalUsername + ".txt"))
                mainLines.add(mainLine);
            mainLine = mainReader.readLine();
        }
        FileWriter mainWriter = new FileWriter("main.txt", false);
        for (String str: mainLines) {
            mainWriter.write(str + System.lineSeparator());
        }
        mainWriter.close();
        mainReader.close();

    }

    public boolean changePassword(int type, String newPassword) {
        File userFileNew1 = null;
        if (type == 1)
            userFileNew1 = new File("@" + globalUsername + ".txt");
        else if (type == 2)
            userFileNew1 = new File("#" + globalUsername + ".txt");

        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(userFileNew1)));
            String line2 = r.readLine();
            while (line2 != null) {
                userLines.add(line2);
                line2 = r.readLine();
            }
            FileWriter w = new FileWriter(userFileNew1);
            userLines.remove(0);
            if (type == 1)
                userLines.add(0, globalUsername + " " + newPassword + " Teacher");
            else if (type == 2)
                userLines.add(0, globalUsername + " " + newPassword + " Student");
            for (String str : userLines) {
                w.write(str + System.lineSeparator());
            }
            JOptionPane.showMessageDialog(null, "Your password was changed" +
                    " successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            r.close();
            w.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Your password could not be changed" +
                    " at this time", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public boolean changeUserName(int type, String newUserName) {
        File userFile = null;
        if (type == 1)
            userFile = new File("@" + globalUsername + ".txt");
        else if (type == 2)
            userFile = new File("#" + globalUsername + ".txt");
        try (BufferedReader bfr = new BufferedReader(new FileReader(userFile))) {
            String line = bfr.readLine();
            String[] credentialsSplit = line.split(" ");

            if (type == 1)
                new File("@" + globalUsername + ".txt").renameTo(new File("@" + newUserName + ".txt"));
            else if (type == 2)
                new File("#" + globalUsername + ".txt").renameTo(new File("#" + newUserName + ".txt"));
            File userFileNew = null;
            if (type == 1)
                userFileNew = new File("@" + newUserName + ".txt");
            else if (type == 2)
                userFileNew = new File("#" + newUserName + ".txt");

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(userFileNew)));
                String line1 = br.readLine();
                while (line1 != null) {
                    userLines.add(line1);
                    line1 = br.readLine();
                }
                FileWriter writer = new FileWriter(userFileNew);
                userLines.remove(0);
                userLines.add(0, newUserName + " " + credentialsSplit[1] + " " + credentialsSplit[2]);
                for (String str : userLines) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
                usernames.clear();
                BufferedReader rb = new BufferedReader(new InputStreamReader(new FileInputStream("usernames.txt")));
                String line3 = rb.readLine();
                while (line3 != null) {
                    if (!line3.equals(globalUsername))
                        usernames.add(line3);
                    line3 = rb.readLine();
                }
                usernames.add(newUserName);

                FileWriter writer1 = new FileWriter("usernames.txt");
                for (String str : usernames) {
                    writer1.write(str + System.lineSeparator());
                }
                writer1.close();

                String identifier = "";
                if (type == 1)
                    identifier = "@";
                else
                    identifier = "#";

                BufferedReader mainReader = new BufferedReader(new InputStreamReader(new FileInputStream("main.txt")));
                String mainLine = mainReader.readLine();
                while (mainLine != null) {
                    if (!mainLine.equals(identifier + globalUsername + ".txt"))
                        mainLines.add(mainLine);
                    mainLine = mainReader.readLine();
                }
                mainLines.add(identifier + newUserName + ".txt");

                FileWriter mainWriter = new FileWriter("main.txt");
                for (String str : mainLines) {
                    mainWriter.write(str + System.lineSeparator());
                }
                mainWriter.close();
                globalUsername = newUserName;
                JOptionPane.showMessageDialog(null, "Your username was changed" +
                        " successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Your username could not be changed" +
                        " at this time", "Error", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Your username could not be changed" +
                    " at this time", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
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

    public String getUserName() {
        return globalUsername;
    }
}
