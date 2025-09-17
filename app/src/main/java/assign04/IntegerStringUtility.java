package assign04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class IntegerStringUtility {
    public static void main(String[] args) {
        getSimilarityGroups(new String[]{"2341", "123", "2134", "2431", "312", "2143"});
    }
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
        // for (int i = 1; i < arr.length; i++){
        //     E key = arr[i];
        //     int j = i - 1;
        //     while (j < 0 && cmp.compare(arr[j], key) > 0){
        //         arr[j + 1] = arr[j];
        //         j--;
        //     }

        //     arr[j + 1] = key;
        // }
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
            if(o1.length() != o2.length()) return Integer.compare(o1.length(), o2.length());
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
            //TODO - Test that this method works.
            int lengthComparison = Integer.compare(o1.length, o2.length);

            if (lengthComparison != 0) return lengthComparison;
            else {
                String o1Max = findMax(o1, (s1, s2) -> s1.compareTo(s2));
                String o2Max = findMax(o2, (s1, s2) -> s1.compareTo(s2));
                return o1Max.compareTo(o2Max);
            }
        }
    }

    public static String[][] getSimilarityGroups(String[] arr){
        String[] arrCopy = Arrays.copyOf(arr, arr.length);

        Comparator<String> similarityComparator = new StringSimilarityComparator();
        insertionSort(arrCopy, similarityComparator);

        System.out.println(Arrays.toString(arrCopy));
        //[ 01234, 01234, 01234, 5679, 5679]
        return null;
    }

    public static String[] findMaximumSimilarityGroup(int[] arr) throws NoSuchElementException {
        return null;
    }
}
