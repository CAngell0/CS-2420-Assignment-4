package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class IntegerStringUtility {
    public static void main(String[] args) {
        String[][] arr = getSimilarityGroups(new String[]{"2341", "123", "2134", "2431", "312", "2143"});
        for (String[] row : arr){
            System.out.println(Arrays.toString(row));
        }
    }

    public static <E> void insertionSort(E[] arr, Comparator<? super E> cmp){
        for (int i = 1; i < arr.length; i++){
            E key = arr[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare(arr[j], key) > 0){
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static <E> E findMax(E[] arr, Comparator<? super E> cmp) throws NoSuchElementException {
        if (arr.length == 0) throw new NoSuchElementException("Array is empty, no such element exists.");

        E[] arrCopy = Arrays.copyOf(arr, arr.length);
        insertionSort(arrCopy, cmp);
        return arrCopy[arrCopy.length - 1];
    }

    public static String stripLeadingZeros(String str){
        StringBuilder builder = new StringBuilder();
        builder.append(str);

        while (builder.indexOf("0") == 0) builder.deleteCharAt(0);
        return builder.toString();
    }

    public static class StringNumericalValueComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            //TODO : test
            String o1Stripped = stripLeadingZeros(o1);
            String o2Stripped = stripLeadingZeros(o2);
            if (o1Stripped.length() != o2Stripped.length()) return Integer.compare(o1Stripped.length(), o2Stripped.length());
            else return o1Stripped.compareTo(o2Stripped);
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

            StringBuilder o1StrBuilder = new StringBuilder();
            for (Character charEl : charArray1) o1StrBuilder.append(charEl.charValue());
            StringBuilder o2StrBuilder = new StringBuilder();
            for (Character charEl : charArray2) o2StrBuilder.append(charEl.charValue());

            return o1StrBuilder.toString().compareTo(o2StrBuilder.toString());
        }
    }

    public static class StringSimilarityGroupComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] o1, String[] o2) {
            //TODO - Test that this method works.
            int lengthComparison = Integer.compare(o1.length, o2.length);
            if (lengthComparison != 0) return lengthComparison;
            else {
                StringNumericalValueComparator cmp = new StringNumericalValueComparator();
                String o1Max = findMax(o1, cmp);
                String o2Max = findMax(o2, cmp);
                return o1Max.compareTo(o2Max);
            }
        }
    }

    public static String[][] getSimilarityGroups(String[] arr){
        //TODO - Test
        String[] arrCopy = Arrays.copyOf(arr, arr.length);

        StringSimilarityComparator cmp = new StringSimilarityComparator();
        insertionSort(arrCopy, cmp);

        ArrayList<String[]> similarityGroups = new ArrayList<>();
        int groupStartIndex = 0;
        for (int i = 0; i <= arrCopy.length; i++){
            if (i != arrCopy.length && cmp.compare(arrCopy[groupStartIndex], arrCopy[i]) == 0) continue;

            String[] group = new String[i - groupStartIndex];
            for (int j = groupStartIndex; j < i; j++){
                group[j - groupStartIndex] = arrCopy[j];
            }

            groupStartIndex = i;
            similarityGroups.add(group);
        }

        return similarityGroups.toArray(new String[0][0]);
    }

    public static String[] findMaximumSimilarityGroup(int[] arr) throws NoSuchElementException {
        return null;
    }
}
