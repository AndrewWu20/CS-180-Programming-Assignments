import javax.swing.*;
import java.io.*;
import java.util.*;

public class Question implements Serializable {
    private String questionMessage;
    private ArrayList<String> choiceList;
    private double points;
    private int truePosition;

//    public Question(String questionMessage) {
//        this.questionMessage = questionMessage;
//        this.choiceList = new ArrayList<String>();
//        this.points = 0;
//    }

    public Question(String questionMessage, double points) {
        this.questionMessage = questionMessage;
        this.choiceList = new ArrayList<String>();
        this.points = points;
    }

    public void addChoice(String choice) {
        choiceList.add(choice);
    }

    public void addChoice(int i) {
        String choiceMessage = JOptionPane.showInputDialog(null,
                "Enter new Quiz Name", "Edit Quiz Name",
                JOptionPane.QUESTION_MESSAGE);
        choiceList.add(choiceMessage);
    }

    public void editChoice() {

        int choiceToEdit = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter new Quiz Name", "Edit Quiz Name",
                JOptionPane.QUESTION_MESSAGE));
        int updatedMessage = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Please enter the updated message for choice number", "Edit Quiz Name",
                JOptionPane.QUESTION_MESSAGE));
        try {
            choiceList.set(choiceToEdit - 1, String.valueOf(updatedMessage));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Enter a valid choice number!");
        }
    }

    public void deleteChoice(int choiceToDelete) {
        try {
            choiceList.remove(choiceToDelete - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid choice number!"); //TODO:
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
        sb.append(questionMessage + "\t" + points + "\n");
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

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
