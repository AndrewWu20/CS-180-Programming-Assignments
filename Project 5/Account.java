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
            System.out.println("Are you a\n1. Teacher \n2. Student?");
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
                writeFile(userName + ".txt", userName + " " + passwordCA + " " + userType);
            } catch (IOException e) {
                System.out.println(e);
            }

            System.out.println("You have been registered successfully!");
        } while (repeat2);
    }


    public void login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = false;
        boolean repeat2 = false;

        do {
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
                            File f = new File(userName + ".txt");
                            try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
                                String line1 = bfr.readLine();
                                String[] credentialsSplit = line1.split(" ");
                                if (credentialsSplit[0].equals(userName) && credentialsSplit[1].equals(password)) {
                                    System.out.println("Login Successful\nWelcome to the Application!");
                                    repeat = false;
                                    repeat2 = false;

                                } else {
                                    System.out.println("Login Unsuccessful\nTry Again");
                                    repeat2 = true;
                                }

                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } while (repeat2);

                }

            }
        } while (repeat);
    }


    public void deleteAccount(String username) throws IOException {
        File userFile = new File(username + ".txt");
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


    public void editAccount(String username) {
        Scanner scanner = new Scanner(System.in);
        File userFile = new File(username + ".txt");
        try (BufferedReader bfr = new BufferedReader(new FileReader(userFile))) {
            String line = bfr.readLine();
            String[] credentialsSplit = line.split(" ");


            System.out.println("What would you like to change? (Enter the corresponding number)\n1. Username" +
                    "\n2. Password");
            int userResponse = scanner.nextInt();
            scanner.nextLine();

            switch (userResponse) {

                case 1:
                    System.out.println("Enter your new username:");
                    String newUsername = scanner.nextLine();
                    new File(username + ".txt").renameTo(new File(newUsername + ".txt"));
                    File userFileNew = new File(newUsername + ".txt");

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
                    File userFileNew1 = new File(username + ".txt");

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

    public String checkUserType(String userName) throws FileNotFoundException {
        String userRole = null;
        File f = new File(userName + ".txt");
        try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
            String line = bfr.readLine();
            String[] credentialsSplit = line.split(" ");
            if (credentialsSplit[2].equals("teacher")) {
                userRole = "teacher";
            } else if (credentialsSplit[2].equals("student")) {
                userRole = "student";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userRole;
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
