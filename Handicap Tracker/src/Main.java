import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The main body of this program. It implements the ui and the calculation elements of the program.
 *
 * Jacob Childs 5/16/2023       Version 1.0
 */
public class Main {
    /**
     * adderHelper automates the searching of the lowest scores and adds them up.
     *
     * @param array A sorted array that contains all the scores.
     * @param d1 The starting index of the array (usually 0).
     * @param d2 The end point index of the array.
     * @return Returns a double of the total scores.
     */
    public static double adderHelper(ArrayList<String> array, int d1, int d2) {
        double total = 0;
        for (int i =0; i <= d2; i++){
            total += (Double.parseDouble(array.get(d1)));
            d1++;

        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        FileDataTracker l = new FileDataTracker("HandicapData.txt");
        ArrayList<String> m = l.getRecentScores();
        Collections.sort(m);
        double sr;  // Slope rating: Difficulty of course based on a number of factors in the terrain.
        double cr; // Course rating: What a scratch golfer would normally shoot on this course.
        double score; // What you shot on the course adjusted to USGA standards.
        double handicap; // Your estimated score when playing your best round.
        double current = 0; // Calculation of current handicap based on # of entered scores

        // Collection of if statements that test how many scores have entered the file. Based on the number, a handicap
        // can be created

        if (m.size()>=3 && m.size()<6) {
            current = Double.parseDouble(m.get(0));
            current = current - 2.0;
        } else if (m.size()>=6 && m.size()<=8) {
            current = adderHelper(m, 0, 1) / 2.0;
            current = current - 1.0;
        } else if (m.size()>=9 && m.size()<=11) {
            current = adderHelper(m, 0, 2) / 3.0;
        } else if (m.size()>=12 && m.size()<=14) {
            current = adderHelper(m, 0, 3) / 4.0;
            current = current - 1.0;
        } else if (m.size()>=15 && m.size()<=16) {
            current = adderHelper(m, 0, 4) / 5.0;
        } else if (m.size()>=17 && m.size()<=18) {
            current = adderHelper(m, 0, 5) / 6.0;
        } else if (m.size()==19) {
            current = adderHelper(m, 0, 6) / 7.0;
        } else if (m.size()>=20) {
            current = adderHelper(m, 0, 7) / 8.0;
        }
            System.out.println("/////////////////////////////////////////////////////////////////////////");
            System.out.println("/                                                                       /");
            System.out.println("/                       Handicap Calculator 1.0                         /");
            System.out.println("/                                                                       /");
            System.out.println("/////////////////////////////////////////////////////////////////////////");
            if (m.size() > 2) {
                System.out.println("Your current handicap is: " + String.format("%.2f", current));
                System.out.println("Enter handicap data below");

                // A series of scanners will ask the user for various statistics needed to measure the handicap.
                Scanner srInput = new Scanner(System.in);
                System.out.print("Enter slope rating: ");
                String srString = srInput.nextLine();
                sr = Double.parseDouble(srString);

                Scanner crInput = new Scanner(System.in);
                System.out.print("Enter course rating: ");
                String crString = crInput.nextLine();
                cr = Double.parseDouble(crString);

                Scanner scoreInput = new Scanner(System.in);
                System.out.print("Enter score: ");
                String scoreString = scoreInput.nextLine();
                score = Double.parseDouble(scoreString);

                handicap = ((score - cr) * 113) / sr;

                DecimalFormat df = new DecimalFormat("#.#"); // Rounding and formatting
                l.addScore(df.format(handicap));

            } else {
                // Discovers if there are not 3 scores in the file.
                System.out.println("You must record at least 3 18 hole scores. " +
                        "Combine two 9 hole scores if necessary.");
                for (int i = m.size(); i < 3; i++) {                   // Records amount needed.
                    System.out.println("Total scores: " + m.size());

                    Scanner srInput = new Scanner(System.in);
                    System.out.print("Enter slope rating: ");
                    String srString = srInput.nextLine();
                    sr = Double.parseDouble(srString);

                    Scanner crInput = new Scanner(System.in);
                    System.out.print("Enter course rating: ");
                    String crString = crInput.nextLine();
                    cr = Double.parseDouble(crString);

                    Scanner scoreInput = new Scanner(System.in);
                    System.out.print("Enter score: ");
                    String scoreString = scoreInput.nextLine();
                    score = Double.parseDouble(scoreString);

                    handicap = ((score - cr) * 113) / sr;

                    DecimalFormat df = new DecimalFormat("#.#");
                    l.addScore(df.format(handicap)); // Adds score to file.
                }
            }



    }
}
