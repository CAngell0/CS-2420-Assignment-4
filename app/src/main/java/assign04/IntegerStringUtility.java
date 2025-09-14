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

    private static Character[] splitStringToCharacterArray(String str){
        Character[] array = new Character[str.length()];
        for (int i = 0; i < str.length(); i++){
            array[i] = Character.valueOf(str.charAt(i));
        }
        return array;
    }

    public static class StringSimilarityComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            //TODO: test to make sure this all works
            if(o1.length() != o2.length()) return Integer.valueOf(o1.length()).compareTo(Integer.valueOf(o2.length()));
            Character[] charArray1 = splitStringToCharacterArray(o1);
            Character[] charArray2 = splitStringToCharacterArray(o2);
            insertionSort(charArray1, (char1, char2) -> char1.compareTo(char2));
            insertionSort(charArray2, (char1, char2) -> char1.compareTo(char2));

            return charArray1.toString().compareTo(charArray2.toString());
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
