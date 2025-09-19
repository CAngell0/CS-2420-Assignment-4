package timing.expirements;

import java.util.List;

import assign04.IntegerStringUtility;
import timing.ArraySortTimingExperiment;

public class InsertionSortBestCaseTimingExperiment extends ArraySortTimingExperiment {

    public static void main(String[] args) {
        int iterationCount = 50;
        List<Integer> problemSizes = buildProblemSizes(1000, 2000, 20);

        InsertionSortBestCaseTimingExperiment experiment = new InsertionSortBestCaseTimingExperiment(problemSizes, iterationCount);

        experiment.warmup(50);
        experiment.run();
        experiment.print();
        experiment.writeToCSV("timing.csv");
    }

    public InsertionSortBestCaseTimingExperiment(List<Integer> problemSizes, int iterationCount){
        super(problemSizes, iterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        populateNearlyAscendingArray(problemSize);
    }

    @Override
    protected void runComputation() {
        IntegerStringUtility.insertionSort(array, (n1, n2) -> Integer.compare(n1, n2));
    }
    
}
