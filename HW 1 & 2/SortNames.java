import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * A program that reads names from a text file, sorts them, then writes them to another text file.
 *
 * <p>Purdue University -- CS18000 -- Spring 2022</p>
 *
 * @author Andrew Wu Purdue CS
 * @version January 10, 2022
 */
public class SortNames {
    public static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();

        try {
            BufferedReader bfr = null;
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeFile(String fileName, ArrayList<String> names) throws FileNotFoundException {
        try {
            FileOutputStream fos = new FileOutputStream(fileName,true);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < names.size(); i++){
                pw.println(names.get(i));
            }
            pw.close();

        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> names;
        System.out.println("Enter filename with unsorted names");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        try {
            names = readFile(filename);
            Collections.sort(names);
            writeFile("sorted_names.txt", names);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }
        System.out.println("Sorted names written to sorted_names.txt");
    }
}
