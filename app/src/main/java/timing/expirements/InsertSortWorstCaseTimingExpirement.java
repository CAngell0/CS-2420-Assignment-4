package timing.expirements;

import java.util.List;

import assign04.IntegerStringUtility;
import timing.ArraySortTimingExperiment;

public class InsertSortWorstCaseTimingExpirement extends ArraySortTimingExperiment {
    public static void main(String[] args) {
        int iterationCount = 50;
        List<Integer> problemSizes = buildProblemSizes(1000, 2000, 200);

        InsertSortWorstCaseTimingExpirement expirement = new InsertSortWorstCaseTimingExpirement(problemSizes, iterationCount);

        expirement.warmup(50);
        expirement.run();
        expirement.print();
        expirement.writeToCSV("timing2.csv");
    }

    public InsertSortWorstCaseTimingExpirement(List<Integer> problemSizes, int iterationCount){
        super(problemSizes, iterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        populateDescendingArray(problemSize);
    }

    @Override
    protected void runComputation() {
        IntegerStringUtility.insertionSort(array, (n1, n2) -> Integer.compare(n1, n2));
    }
    
}
