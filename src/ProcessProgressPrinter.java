public class ProcessProgressPrinter {
    private boolean initialized;
    final int totalElements;

    public ProcessProgressPrinter(int totalElements) {
        this.totalElements = totalElements;
    }

    void printProgress(int elementsCalculated) {
        if (initialized) {

            System.out.print("\rCurrent Progress: " + (double)Math.round((float) elementsCalculated / totalElements * 10000)/100  + "%");
        }
        else {
            System.out.print("Current Progress: " + (double) elementsCalculated / totalElements * 100 + "%");
            initialized = true;
        }
    }
}
