import java.util.Scanner;
public class CarInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car's make:");
        // String input
        String name = scanner.nextLine();
        System.out.println("Enter car's model:");
        // String input
        String type = scanner.nextLine();
        System.out.println("Enter car's highway and city MPG:");
        // int input
        String MPG = scanner.nextLine();// 22 31
        int id = MPG.indexOf(' ');// 2
        String highway = MPG.substring(0, id);
        String city  = MPG.substring(id+1, MPG.length());
        System.out.println("Enter car's ratings of performance and comfort:");
        // double input
        String rating = scanner.nextLine();
        int id2 = rating.indexOf(' ');
        String performance = rating.substring(0, id2);
        String comfort = rating.substring(id2+1, rating.length());
        System.out.printf("Make: %s\n", name);
        System.out.printf("Model: %s\n", type);
        System.out.printf("Highway MPG: %s\n", highway);
        System.out.printf("City MPG: %s\n", city);
        System.out.printf("Performance Rating: %.1f\n", Double.parseDouble(performance));
        System.out.printf("Comfort Rating: %.1f\n", Double.parseDouble(comfort));
    }
}