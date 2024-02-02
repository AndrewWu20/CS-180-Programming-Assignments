import javax.swing.*;

public class OrderFormGUI {
    public static void main(String[] args) {
        /** DO NOT CHANGE VALUES BELOW **/
        boolean hoodieInStock = true;
        boolean tshirtInStock = false;
        boolean longsleeveInStock = true;
        String item = "";
        int quantity = 0;
        String name = "";
        /** DO NOT CHANGE VALUES ABOVE **/


        //TODO: display error GUI if item selected is out of stock
        int order = 0;
        do {
            int check = 0;
            do {
                check = 0;
                String[] options = {"Hoodie", "T-shirt", "Long sleeve"};
                item = (String) JOptionPane.showInputDialog(null, "Select item style ", "Order Form",
                        JOptionPane.PLAIN_MESSAGE, null, options, null);
                if (item.equals("T-shirt")) {
                    JOptionPane.showMessageDialog(null, "Out of Stock", "Out of Stock",
                            JOptionPane.ERROR_MESSAGE);
                    check = 1;
                }
            } while (check == 1);

            //TODO: display error GUI if input is not an int or if input is less than 1

            do {
                check = 0;
                String quantityString = (JOptionPane.showInputDialog(null, "Enter quantity", "Order Form",
                        JOptionPane.QUESTION_MESSAGE));
                try {
                    Integer.parseInt(quantityString);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Enter an Integer", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    check = 1;
                    continue;
                }
                if (Integer.parseInt(quantityString) <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter a number greater than 0", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    check = 1;
                }
                quantity = Integer.parseInt(quantityString);
            } while (check == 1);

            //TODO: display error GUI if input does not include a space
            do {
                check = 0;
                name = JOptionPane.showInputDialog(null, "Enter your Name", "Order Form",
                        JOptionPane.QUESTION_MESSAGE);
                if (!(name.contains(" "))) {
                    JOptionPane.showMessageDialog(null, "Enter first and last name", "Name Error",
                            JOptionPane.ERROR_MESSAGE);
                    check = 1;
                }
            } while (check == 1);


            /** Order Confirmation Message **/
            String resultMessage = "Name: " + name + "\nItem: " + item + "\nQuantity: " + quantity;
            JOptionPane.showMessageDialog(null, resultMessage, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

            //TODO: loop through order form again if YES
            int ret = JOptionPane.showConfirmDialog(null, "Would you like to place another order?", "Order Form", JOptionPane.YES_NO_OPTION);
            if (ret == JOptionPane.YES_OPTION) {
                order = 1;
            } else {
                order = 0;
            }
        } while (order == 1);
    }
}
