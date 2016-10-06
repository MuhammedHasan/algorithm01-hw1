import java.io.*;
import java.util.*;

public class Reverser {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("CsvToReverse.txt"));
        FileWriter out = new FileWriter("ReverseCsv.txt");

        while (in.hasNextLine()) {
            List<String> nums = Arrays.asList((in.nextLine()).split(",", -1));
            Collections.reverse(nums);
            out.write(String.join(",", nums) + "\n");
        }

        out.close();
    }
}
    
