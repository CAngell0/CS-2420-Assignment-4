package assign04;

import java.util.List;

import timing.TimingExperiment;

public class InsertionSortWorstCaseTimingExperiment extends ArraySortTimingExperiment{

    public static void main(String[] args){
        List<Integer> problemSizes = buildProblemSizes(100000, 20000, 50);

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setupExperiment'");
    }

    @Override
    protected void runComputation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runComputation'");
    }

}
