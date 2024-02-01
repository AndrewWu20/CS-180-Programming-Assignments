//import java.io.*;
//import java.util.*;
////import java.util.ArrayList;
////import java.util.Scanner;
//
//public class ColorMixer { // File IO & Exception Handling
//
//    private static final String inputFilePrompt = "Enter the filename of the color map.";
//    private static final String outputFilePrompt = "Enter the filename to output the colors to.";
//    private static final String readError = "Either the file doesn't exist or the file is in the wrong format!";
//    private static final String finishedString = "The file was written to!";
//    private static final String writeError = "There was an error writing to the file.";
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(inputFilePrompt);
//        Color[] colors = readFile(scanner.nextLine());
//        if (colors == null) {
//            System.out.println(readError);
//        } else {
//            System.out.println(outputFilePrompt);
//            if (!writeFile(colors, scanner.nextLine())) {
//                System.out.println(writeError);
//            } else {
//                System.out.println(finishedString);
//            }
//        }
//    }
//
//
//    /**
//     * Read the contents of the file from the filename that is passed in. If the file results in a
//     * FileNotFoundException or any other type of IOException, return null. Else, return Color[]
//     * containing the lines of the file.
//     *
//     * @param filename The filename to read from.
//     * @return A Color[] containing the lines of the file, or null if an exception is thrown.
//     */
//    public static Color[] readFile(String filename) {
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
//            ArrayList<String> fileContents = new ArrayList<>();
//            String s;
//            while ((s = bufferedReader.readLine()) != null) {
//                fileContents.add(s);
//            }
//
//            if (fileContents.size() % 3 != 0) {
//                return null;
//            }
//
//            Color[] colors = new Color[fileContents.size() / 3];
//
//            int pos = 0;
//            for (int i = 0; i < fileContents.size(); i += 3) {
//                colors[pos++] = new Color(Integer.parseInt(fileContents.get(i)),
//                        Integer.parseInt(fileContents.get(i + 1)),
//                        Integer.parseInt(fileContents.get(i + 2)));
//            }
//            return colors;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        //Have the General exception even if it is not asked
//        catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * Write to the file given by the filename. You should write the toString representation of the color
//     * to the file, each on it's own line.
//     *
//     * @param colors   The Colors to print to the file
//     * @param filename the filename to print to
//     * @return true if succeeded in writing to the file, false otherwise.
//     */
//    public static boolean writeFile(Color[] colors, String filename) {
//        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
//            for (Color c : colors) {
//                pw.println(c);
//            }
//            pw.flush();
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
