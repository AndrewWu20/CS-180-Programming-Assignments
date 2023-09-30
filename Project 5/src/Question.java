import java.io.*;
import java.util.*;

public class Question {
    private String questionMessage;
    private ArrayList<String> choiceList;

    public Question(String questionMessage) {
        this.questionMessage = questionMessage;
        this.choiceList = new ArrayList<String>();
    }

    public void addChoice(String choice) {
        choiceList.add(choice);
    }

    public void addChoice(int i) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the message for choice number " + (i + 1));
        choiceList.add(scan.nextLine());
    }

    public void editChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which choice do you want to edit? 1, 2, etc.");
        int choiceToEdit = scan.nextInt();
        scan.nextLine();
        System.out.println("Please enter the updated message for choice number");
        try {
            choiceList.set(choiceToEdit - 1, scan.nextLine());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    public void deleteChoice(int choiceToDelete) {
        try {
            choiceList.remove(choiceToDelete - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    public String getQuestionMessage() {
        return questionMessage;
    }

    public void setQuestionMessage(String message) {
        this.questionMessage = message;
    }

    //toString() uses StringBuilder to return a question as a string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionMessage + "\n");
        for (int i = 0; i < choiceList.size(); i++) {
            sb.append((i + 1) + ". " + choiceList.get(i) + "\n");
        }
        return sb.toString();
    }

    public ArrayList<String> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(ArrayList<String> choiceList) {
        this.choiceList = choiceList;
    }
}
