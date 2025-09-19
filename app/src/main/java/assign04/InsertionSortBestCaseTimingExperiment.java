package assign04;

import java.util.Comparator;
import java.util.List;

import timing.TimingExperiment;

public class InsertionSortBestCaseTimingExperiment extends ArraySortTimingExperiment {

    public static void main(String[] args) {
        List<Integer> problemSizes = buildProblemSizes(1000, 1000, 20);

        int interationCount = 50;

        TimingExperiment experiment = new InsertionSortBestCaseTimingExperiment(problemSizes, interationCount);

        experiment.warmup(100);
        experiment.run();
        experiment.print();
        experiment.write("InsertionSortBestCaseTimingExperiment.txt");
    }

    public InsertionSortBestCaseTimingExperiment(List<Integer> problemSizes, int iterationCount) {
        super(problemSizes, iterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        populateNearlyAscendingArray(problemSize);
    }

    @Override
    protected void runComputation() {
        IntegerStringUtility.insertionSort(this.array, Comparator.naturalOrder());
    }

}