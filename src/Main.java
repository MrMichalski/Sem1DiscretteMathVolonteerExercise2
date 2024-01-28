public class Main {
    public static void main(String[] args) {

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.startMeasurement();

        MethodManager methodManager = new MethodManager();
        //methodManager.linearMethod("resources/testFile2.txt");
        methodManager.hybridMethod("resources/testFile.txt", 90);

        stopwatch.stopMeasurement();
        stopwatch.printTimeMeasured();

    }
}
