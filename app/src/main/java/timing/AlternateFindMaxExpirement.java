package timing;

import java.util.List;

import assign04.IntegerStringUtility;

public class AlternateFindMaxExpirement extends TimingExperiment {
    private Integer[] numbers;

    public static void main(String[] args) {
        int iterationCount = 50;
        String problemSizeName = "Alternate Find Max Method";
        List<Integer> problemSizes = buildProblemSizes(1000, 1000, 20);

        TimingExperiment experiment = new AlternateFindMaxExpirement(problemSizeName, problemSizes, iterationCount);

        experiment.warmup(50);
        experiment.run();
        experiment.print();
        experiment.writeToCSV("timing.csv");
    }

    public AlternateFindMaxExpirement(String problemSizeName, List<Integer> problemSizes, int iterationCount){
        super(problemSizeName, problemSizes, iterationCount);
        
    }

    @Override
    protected void setupExperiment(int problemSize) {
        numbers = new Integer[problemSize];
        for (int index = 0; index < problemSize; index++){
            numbers[index] = Integer.valueOf((int) Math.random() * 1000);
        }
    }

    @Override
    protected void runComputation() {
        IntegerStringUtility.findMaxAlternate(numbers, (n1, n2) -> Integer.compare(n1, n2));
    }
}
