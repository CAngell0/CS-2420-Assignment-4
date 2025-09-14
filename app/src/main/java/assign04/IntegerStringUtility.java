package assign04;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class IntegerStringUtility {
    public static <E> void insertionSort(E[] arr, Comparator<? super E> cmp){

    }

    public static <E> E findMax(E[] arr, Comparator<? super E> cmp) throws NoSuchElementException {
        return null;
    }

    public static class StringNumericalValueComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    }

    public static class StringSimilarityComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    }

    public static class StringSimilarityGroupComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] o1, String[] o2) {
            return 0;
        }
    }

    public static String[][] getSimilarityGroups(String[] arr){
        return null;
    }

    public static String[] findMaximumSimilarityGroup(int[] arr) throws NoSuchElementException {
        return null;
    }
}
