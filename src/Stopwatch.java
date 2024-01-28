import java.util.Date;
import java.util.Objects;

public class Stopwatch {
    private Date measurementBeginning;
    private Date measurementEnd;

    void startMeasurement() {
        if(measurementBeginning == null) {
            measurementBeginning = new Date();
        } else {
            System.out.println("Stopwatch - Messurement already started");
        }
    }

    void stopMeasurement() {
        if(measurementEnd == null) {
            measurementEnd = new Date();
        } else {
            System.out.println("Stopwatch - Messurement already stopped");
        }
    }

    long getTimeMeasured() {
        return measurementEnd.getTime() - measurementBeginning.getTime();
    }

    void printTimeMeasured() {
        long timeMeasured = getTimeMeasured();
        System.out.println("Time in milliseconds: " + timeMeasured);
        System.out.println("Time in seconds: " + timeMeasured/1000);
        System.out.println("Time in minutes: " + timeMeasured/1000/60);
    }
}
