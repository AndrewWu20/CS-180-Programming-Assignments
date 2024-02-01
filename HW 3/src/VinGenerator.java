import java.util.Scanner;

public class VinGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make:"); // String input
        String make = scanner.nextLine();
        System.out.println("Model:"); // String input
        String model = scanner.nextLine();
        System.out.println("Model Year:"); // Integer input
        String year = scanner.nextLine();
        System.out.println("New Car?:"); // Boolean input
        String quality = scanner.nextLine();
        System.out.println("Avg. Rating:"); // Double input
        String rating = scanner.nextLine();
        System.out.println("Price:"); // Integer input
        String price = scanner.nextLine();
        System.out.println("Dealership:"); // String input
        String dealership = scanner.nextLine();
        System.out.println("Phone Number:"); // String input
        String phone = scanner.nextLine();
        System.out.println("Serial Number:"); // Long input
        String serial = scanner.nextLine();

        String UC_make = make.toUpperCase();
        String UC_model = model.toUpperCase();
        String LC_make = make.toLowerCase();
        String LC_model = model.toLowerCase();
        Float rating_f = Float.valueOf(rating);
        String rating_rnd = String.format("%.1f", rating_f);
        String Phone_one = '(' + phone.substring(0, 3) + ')';
        String Phone_two = phone.substring(3, 6);
        String Phone_three = '-' + phone.substring(phone.length() - 4, phone.length());

        String vin_year = year.substring(0, 2);
        String serial_last = serial.substring(serial.length()-4, serial.length());
        Character Ufmkfs_char = (char)(UC_make.charAt(0) + Integer.parseInt(serial.substring(0, 1)));
        Character Ulmkfs_char = (char)(UC_make.charAt(make.length() - 1) + Integer.parseInt(serial.substring(0, 1)));
        Character Lfmkss_char = (char)(LC_make.charAt(0) + Integer.parseInt(serial.substring(1, 2)));
        Character Llmkss_char = (char)(LC_make.charAt(make.length() - 1) + Integer.parseInt(serial.substring(1, 2)));
        Character Ufmdfs_char = (char)(UC_model.charAt(0) + Integer.parseInt(serial.substring(0, 1)));
        Character Ulmdfs_char = (char)(UC_model.charAt(model.length() - 1) + Integer.parseInt(serial.substring(0, 1)));
        Character Lfmdss_char = (char)(LC_model.charAt(0) + Integer.parseInt(serial.substring(1, 2)));
        Character Llmdss_char = (char)(LC_model.charAt(model.length() - 1) + Integer.parseInt(serial.substring(1, 2)));

        String Ufmkfs = Character.toString(Ufmkfs_char);
        String Ulmkfs = Character.toString(Ulmkfs_char);
        String Lfmkss = Character.toString(Lfmkss_char);
        String Llmkss = Character.toString(Llmkss_char);
        String Ufmdfs = Character.toString(Ufmdfs_char);
        String Ulmdfs = Character.toString(Ulmdfs_char);
        String Lfmdss = Character.toString(Lfmdss_char);
        String Llmdss = Character.toString(Llmdss_char);

        String VIN = vin_year + Ufmkfs + Ulmkfs + Lfmkss + Llmkss + Ufmdfs + Ulmdfs + Lfmdss + Llmdss + serial_last;


        System.out.println(year + ' ' + make + ' ' + model);
        System.out.println("New Car?: "+ quality);
        System.out.println("Avg. Rating: "+ rating_rnd);
        System.out.println("Price: $"+ price);
        System.out.println("Dealership: "+ dealership);
        System.out.println("Phone Number: "+ Phone_one+ Phone_two+ Phone_three);
        System.out.println("Serial Number: "+ serial);
        System.out.println("VIN: "+ VIN);


    }

}
