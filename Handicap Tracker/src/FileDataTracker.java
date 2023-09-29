import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that writes data about scores to a file. The data is saved and that data is made into an array. The
 * array is used to sort through the scores to find the lowest ones.
 */
public class FileDataTracker {
    private String file;
    public FileDataTracker(String file){
        this.file = file;
    }

    /**
     * addScore takes a string that is the name of a file and adds the score to it. It does not overwrite data.
     *
     * @param text          A string that is the file name used to store data.
     * @throws IOException  In case there is no file found.
     */
    public void addScore(String text) throws IOException {
        File file1 = new File(file);
        FileWriter f1 = new FileWriter(file1, true);
        PrintWriter pw = new PrintWriter(f1);

        pw.println(text);
        pw.close();
    }

    /**
     * getRecentScores will create an array of the 20 lowest scores, which are all that are needed.
     *
     * @return                           Returns an array of the scores.
     * @throws FileNotFoundException     In case no file is found.
     */
    public ArrayList<String> getRecentScores() throws FileNotFoundException {
        ArrayList<String> array = new ArrayList<>();
        int counter = 0;
        File fileName = new File(file);

        try (Scanner scan = new Scanner(fileName)) {
            while (scan.hasNextLine() && counter < 20) {
                array.add(scan.nextLine());
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return array;
    }

}
