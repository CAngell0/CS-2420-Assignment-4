package assign04;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class IntegerStringUtility {
    public static <E> void insert(E[] arr, E element,  Comparator<? super E> cmp, int upperIndex){
        //TODO - Check for invalid upperIndex values
        for (int i = upperIndex - 1; i >= 1; i--){
            if (cmp.compare(arr[i - 1], element) > 0){
                E temp = arr[i - 1];
                arr[i - 1] = element;
                arr[i] = temp;
            }
        }
    }

    public static <E> void insertionSort(E[] arr, Comparator<? super E> cmp){
        //TODO - Test this method
        for (int i = 0; i < arr.length; i++){
            for (int j = i; j < arr.length; j++){
                insert(arr, arr[j], cmp, i);
            }
        }
    }

    public static <E> E findMax(E[] arr, Comparator<? super E> cmp) throws NoSuchElementException {
        //TODO - Needs testing
        E maxElement = arr[0];
        for (E element : arr){
            if (cmp.compare(element, maxElement) > 0) maxElement = element;
        }
        return maxElement;
    }

    public static class StringNumericalValueComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            //TODO : test edge cases carefully
            //TODO : see if we actually need to implement these testings
            // try {
            //     Integer.valueOf(o1); Integer.valueOf(o2);
            // }
            // catch (NumberFormatException error){
            //     return null;
            // }
            
            // if(o1.contains('-') == '-' || o2.contains('-')){
            //     return null; 
            // }

            return o1.compareTo(o2);
        }
    }

    

    public static class StringSimilarityComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            //TODO: check o1 o2 order
            if(o1.length() != o2.length()) return Integer.valueOf(o1.length()).compareTo(Integer.valueOf(o2.length()));
            char[] o1Char = o1.toCharArray();
            char[] o2Char = o2.toCharArray();

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
