package assign04;

import java.util.*;
import timing.TimingExperiment;

/**
 * Abstract class to perform timing experiments for sorting arrays with varying
 * degrees of "sortedness".
 *
 * @author CS 2420 Course Staff
 * @version 2025-09-15
 */
public abstract class ArraySortTimingExperiment extends TimingExperiment {

  protected Integer[] array;
  protected Random rng = new Random();

  /**
   * Constructor to build a sort timing experiment.
   *
   * @param problemSizes   - the list of problem sizes on which to run the
   *                       experiment
   * @param iterationCount - the number of times to run the experiment for each
   *                       problem size
   */
  public ArraySortTimingExperiment(
      List<Integer> problemSizes,
      int iterationCount) {
    super("arrayLength", problemSizes, iterationCount);
  }

  /**
   * Helper method to populate the array with random integers in ascending order.
   *
   * @implNote integers are bounded between 0 and 20 + 10 * problemSize
   * @param problemSize - size of the array
   */
  protected void populateAscendingArray(int problemSize) {
    array = new Integer[problemSize];
    int currentElement = rng.nextInt(20);
    for (int i = 0; i < problemSize; i++) {
      array[i] = currentElement;
      currentElement += rng.nextInt(10);
    }
  }

  /**
   * Helper method to populate the array with random integers in random order.
   *
   * @implNote method must call populateAscendingArray and then shuffle the
   *           contents of the array
   * @param problemSize - size of the array
   */
  protected void populateRandomArray(int problemSize) {
    populateAscendingArray(problemSize);
    Collections.shuffle(Arrays.asList(array), rng);
  }

  /**
   * Helper method to populate the array with random integers in descending order.
   *
   * @implNote method must call populateAscendingArray and then reverse the
   *           contents of the array
   * @param problemSize - size of the array
   */
  protected void populateDescendingArray(int problemSize) {
    populateAscendingArray(problemSize);
    Collections.reverse(Arrays.asList(array));
  }

  /**
   * Helper method to populate the array with random integers in nearly ascending
   * order.
   *
   * @implNote method must call populateAscending array and then swap a small
   *           number of random
   *           pairs of nearby elements
   */
  protected void populateNearlyAscendingArray(int problemSize) {
    populateAscendingArray(problemSize);
    // Choose a random number of pairs to swap, between 5 and 19
    int swapCount = rng.nextInt(5, 20);
    for (int i = 0; i < swapCount; i++) {
      // Choose a random index, excluding the final 11 indices
      int idx1 = rng.nextInt(problemSize - 11);
      // Choose an index between 1 and 10 to the right of idx1
      int idx2 = idx1 + rng.nextInt(1, 11);
      // Swap the entries at those two indices
      swapEntries(idx1, idx2);
    }
  }

  /**
   * Helper method to swap two entries of the array.
   *
   * @param idx1 - first index to swap
   * @param idx2 - second index to swap
   * @throws IndexOutOfBoundsException if either index is out of bounds
   */
  private void swapEntries(int idx1, int idx2) throws IndexOutOfBoundsException {
    if (idx1 < 0 || idx1 >= array.length || idx2 < 0 || idx2 >= array.length) {
      throw new IndexOutOfBoundsException();
    }
    int temp = array[idx1];
    array[idx1] = array[idx2];
    array[idx2] = temp;
  }
}
