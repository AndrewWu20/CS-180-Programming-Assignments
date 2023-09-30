import java.io.*;
import java.util.Scanner;

public class Append {

    public void appendText(String path, String toAppend) throws PathException {

        File input = new File(path);

        try {

            BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw = new BufferedWriter(new FileWriter(input, true));
            bw.write(toAppend);

            br.close();
            bw.close();

        } catch (Exception e) {
            throw new PathException();
        }
    }

    public static void main(String[] args) throws PathException {
        
        Scanner scan = new Scanner(System.in); 
        
        System.out.println("Enter the path to the file:");
        String path = scan.nextLine();
        
        System.out.println("Enter the line to append:");
        String toAppend = scan.nextLine(); 
        
        Append a = new Append();
        try {
            a.appendText(path, toAppend);
        } catch (PathException e) {
            throw new PathException();
        }

    }
}