package assign04;

import java.util.Comparator;
import java.util.List;

import timing.TimingExperiment;

public class InsertionSortWorstCaseTimingExperiment extends ArraySortTimingExperiment{
    public static void main(String[] args){
        List<Integer> problemSizes = buildProblemSizes(1000, 100, 10);

        int interationCount = 50;

        TimingExperiment experiment = new InsertionSortWorstCaseTimingExperiment(problemSizes, interationCount);

        experiment.warmup(100);
        experiment.run();
        experiment.print();
        experiment.write("InsertionSortWorstCaseTimingExperiment.txt");
    }

    public InsertionSortWorstCaseTimingExperiment(List<Integer> problemSizes, int iterationCount) {
        super(problemSizes, iterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        populateDescendingArray(problemSize);
    }

    @Override
    protected void runComputation() {
        IntegerStringUtility.insertionSort(this.array, Comparator.naturalOrder());
    }

}
