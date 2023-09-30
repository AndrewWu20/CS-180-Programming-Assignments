import java.util.Scanner;

/**
 * A simple SnowAccumulation class
 *
 * @author Andrew Wu Purdue CS L10
 * @version February 19, 2022
 */
public class SnowAccumulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Enter Resort One Name:");
        String resortOne = scanner.nextLine();
        System.out.println("Enter Resort Two Name:");
        String resortTwo = scanner.nextLine();
        System.out.println("Enter Snow Accumulations:");
        String accumulations = scanner.nextLine();
        System.out.println("Enter Report type:" +
                "\n1. Full" +
                "\n2. Summary");
        int reportType = scanner.nextInt();

        scanner.close();

        // The values of each of the accumulations are defined below,
        // you should use these double variables to make your calculations.
        // Each string has 7 paired values so the format of the string is resortOneDayOne
        // - resortTwoDayOne, resortOneDayTwo - resortTwoDayTwo, ...
        int currentStringIndex = 0;
        double resortOneDayOne = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayOne = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayTwo = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayTwo = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayThree = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayThree = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayFour = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayFour = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayFive = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayFive = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDaySix = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDaySix = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDaySeven = Double.parseDouble(accumulations.substring(currentStringIndex,
                accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDaySeven = Double.parseDouble(accumulations.substring(currentStringIndex));

        // Do not modify above

        // ToDo: Implement your solution below.
        double boilTotal = (resortOneDayOne + resortOneDayTwo + resortOneDayThree +
                resortOneDayFour + resortOneDayFive + resortOneDaySix + resortOneDaySeven);
        double lawsTotal = (resortTwoDayOne + resortTwoDayTwo +
                resortTwoDayThree + resortTwoDayFour + resortTwoDayFive + resortTwoDaySix + resortTwoDaySeven);
        double totalAccum = (boilTotal + lawsTotal);
        double boilTotalAvg = (boilTotal / 7);
        double lawsTotalAvg = (lawsTotal / 7);
        String totalAccumS = String.format("%.2f", totalAccum);
        String boilTotalS = String.format("%.2f", boilTotal);
        String lawsTotalS = String.format("%.2f", lawsTotal);
        String boilTotalAvgS = String.format("%.2f", boilTotalAvg);
        String lawsTotalAvgS = String.format("%.2f", lawsTotalAvg);
        String resortOneDayOneS = String.format("%.2f", resortOneDayOne);
        String resortOneDayTwoS = String.format("%.2f", resortOneDayTwo);
        String resortOneDayThreeS = String.format("%.2f", resortOneDayThree);
        String resortOneDayFourS = String.format("%.2f", resortOneDayFour);
        String resortOneDayFiveS = String.format("%.2f", resortOneDayFive);
        String resortOneDaySixS = String.format("%.2f", resortOneDaySix);
        String resortOneDaySevenS = String.format("%.2f", resortOneDaySeven);
        String resortTwoDayOneS = String.format("%.2f", resortTwoDayOne);
        String resortTwoDayTwoS = String.format("%.2f", resortTwoDayTwo);
        String resortTwoDayThreeS = String.format("%.2f", resortTwoDayThree);
        String resortTwoDayFourS = String.format("%.2f", resortTwoDayFour);
        String resortTwoDayFiveS = String.format("%.2f", resortTwoDayFive);
        String resortTwoDaySixS = String.format("%.2f", resortTwoDaySix);
        String resortTwoDaySevenS = String.format("%.2f", resortTwoDaySeven);
        int resort1total = 0;
        int resort2total = 0;
        if (resortOneDayOne > resortTwoDayOne) {
            resort1total++;
        } else if (resortOneDayOne < resortTwoDayOne) {
            resort2total++;
        }
        if (resortOneDayTwo > resortTwoDayTwo) {
            resort1total++;
        } else if (resortOneDayTwo < resortTwoDayTwo) {
            resort2total++;
        }
        if (resortOneDayThree > resortTwoDayThree) {
            resort1total++;
        } else if (resortOneDayThree < resortTwoDayThree) {
            resort2total++;
        }
        if (resortOneDayFour > resortTwoDayFour) {
            resort1total++;
        } else if (resortOneDayFour < resortTwoDayFour) {
            resort2total++;
        }
        if (resortOneDayFive > resortTwoDayFive) {
            resort1total++;
        } else if (resortOneDayFive < resortTwoDayFive) {
            resort2total++;
        }
        if (resortOneDaySix > resortTwoDaySix) {
            resort1total++;
        } else if (resortOneDaySix < resortTwoDaySix) {
            resort2total++;
        }
        if (resortOneDaySeven > resortTwoDaySeven) {
            resort1total++;
        } else if (resortOneDaySeven < resortTwoDaySeven) {
            resort2total++;
        }

        if (reportType == 1) {
            System.out.println(resortOne + " Full Report:");
            System.out.println("Monday: " + resortOneDayOneS);
            System.out.println("Tuesday: " + resortOneDayTwoS);
            System.out.println("Wednesday: " + resortOneDayThreeS);
            System.out.println("Thursday: " + resortOneDayFourS);
            System.out.println("Friday: " + resortOneDayFiveS);
            System.out.println("Saturday: " + resortOneDaySixS);
            System.out.println("Sunday: " + resortOneDaySevenS);
            System.out.println(resortTwo + " Full Report:");
            System.out.println("Monday: " + resortTwoDayOneS);
            System.out.println("Tuesday: " + resortTwoDayTwoS);
            System.out.println("Wednesday: " + resortTwoDayThreeS);
            System.out.println("Thursday: " + resortTwoDayFourS);
            System.out.println("Friday: " + resortTwoDayFiveS);
            System.out.println("Saturday: " + resortTwoDaySixS);
            System.out.println("Sunday: " + resortTwoDaySevenS);
            System.out.println("Total Accumulation: " + totalAccumS);
            System.out.println(resortOne + " Total Accumulation: " + boilTotalS);
            System.out.println(resortTwo + " Total Accumulation: " + lawsTotalS);
            System.out.println(resortOne + " Average Accumulation: " + boilTotalAvgS);
            System.out.println(resortTwo + " Average Accumulation: " + lawsTotalAvgS);

            if (resort1total > resort2total) {
                System.out.println(resortOne + " had greater snowfall on more days than " + resortTwo + "!");
            } else if (resort1total < resort2total) {
                System.out.println(resortTwo + " had greater snowfall on more days than " + resortOne + "!");
            }
            if (resort1total == 7) {
                System.out.println(resortOne + " is the undisputed winner!");
            } else if (resort2total == 7) {
                System.out.println(resortTwo + " is the undisputed winner!");
            }
        } else {
            System.out.println("Total Accumulation: " + totalAccumS);
            System.out.println(resortOne + " Total Accumulation: " + boilTotalS);
            System.out.println(resortTwo + " Total Accumulation: " + lawsTotalS);
            System.out.println(resortOne + " Average Accumulation: " + boilTotalAvgS);
            System.out.println(resortTwo + " Average Accumulation: " + lawsTotalAvgS);
            if (resort1total > resort2total) {
                System.out.println(resortOne + " had greater snowfall on more days than " + resortTwo + "!");
            } else if (resort1total < resort2total) {
                System.out.println(resortTwo + " had greater snowfall on more days than " + resortOne + "!");
            }
            if (resort1total == 7) {
                System.out.println(resortOne + " is the undisputed winner!");
            } else if (resort2total == 7) {
                System.out.println(resortTwo + " is the undisputed winner!");
            }
        }
    }
}


