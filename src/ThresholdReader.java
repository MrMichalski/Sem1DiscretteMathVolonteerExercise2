import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ThresholdReader {
    List<Integer> readThresholds(String filepath) throws IOException {//this method reads thresholds from input file
        LinkedList<Integer> thresholds = new LinkedList<>();
        BufferedReader br = new BufferedReader(new java.io.FileReader(filepath));

        String line;
        br.readLine(); // first line is redundant
        while ((line = br.readLine()) != null) {
            thresholds.add(Integer.parseInt(line));
        }
        br.close();
        return thresholds;
    }
}
