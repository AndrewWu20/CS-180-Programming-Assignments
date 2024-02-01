import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * ButtonClient
 *
 * Client side class of the button clicking game
 *
 * @author Andrew Wu, L10
 *
 * @version date of completion
 *
 */

public class ButtonClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game");
        System.out.println("Please enter host name");
        String host = scanner.nextLine();
        System.out.println("Please enter a port number");
        String port = scanner.nextLine();
        Socket socket = new Socket("host", 6432);
        //Try catch block to handle exception
        System.out.println("Connection has been established");
        System.out.println("Are you ready to play?   ");
        //Have a variable that is from the server that gets sent to the client that is a counter variable
        //for the number of players there were
        String ready = scanner.nextLine();
        if (ready.equalsIgnoreCase("no")) {
            System.out.println("You have exited the game");
            return;
            }
        int count;
        JOptionPane.
        //Code gui to display a button where if user clicks the button, count goes up by one
    }
}
//Create GUIs for all prompts and answers