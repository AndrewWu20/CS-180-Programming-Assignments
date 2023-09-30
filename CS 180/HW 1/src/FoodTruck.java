import java.util.Scanner;
public class FoodTruck {
    public static void main(String []args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to place an order? (yes or no)");
        String orderStatus = scanner.nextLine();

        String category = "";
        String cookMethod = "";
        String dish = "";
        String err  = "Input Error! Valid options are yes or no.";
        boolean check = false;
        if (orderStatus.equals("no")) {
            System.out.println("Okay, have a nice day!");
        } else if (orderStatus.equals("yes")) {
            System.out.println("What would you like to order?");

            System.out.println("1. Lamb");
            System.out.println("2. Pork");
            System.out.println("3. Chicken");
            System.out.println("4. Vegetables");

            int selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 1:
                    category = "Lamb";
                    break;
                case 2:
                    category = "Pork";
                    break;
                case 3:
                    category = "Chicken";
                    break;
                case 4:
                    category = "Vegetables";
                    break;
                default:
                    System.out.println("Input Error! Valid menu options are from 1 - 4.");
                    System.out.println("We couldn't complete the order, sorry!");
                    check = true;
                    break;
            }
            if (check == false) {
                System.out.println("How would you like it cooked?");
                System.out.println("1. Fried");
                System.out.println("2. Boiled");

                int cook = scanner.nextInt();
                scanner.nextLine();

                switch (cook) {
                    case 1:
                        cookMethod = "Fried";
                        break;
                    case 2:
                        cookMethod = "Boiled";
                        break;
                    default:
                        System.out.println("Input Error! Valid cooking options are 1 or 2.");
                        System.out.println("We couldn't complete the order, sorry!");
                        check = true;
                        break;
                }


            if (check == false)
            {
                dish = cookMethod + " " + category;
                System.out.println("You have ordered " + dish + "!");
            }
        }

        }


        else
                System.out.println(err);


}
}