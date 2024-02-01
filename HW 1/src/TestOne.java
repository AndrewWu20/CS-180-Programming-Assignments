import javax.swing.JOptionPane;

public class TestOne {
    public static void main(String[] args) {
        String[] options = {"Lets go!", "Not right now."};
        int result = JOptionPane.showOptionDialog(null, "Are you ready to continue?", "Ready?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
    }
}
