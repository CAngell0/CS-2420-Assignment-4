package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class IntegerStringUtility {
    /**
     * A helper method that's used to split a string into an array of Characters.
     * @param str The string to split up
     * @return An array of Character wrapper objects for each character in the string
     */
    private static Character[] splitStringToCharacterArray(String str) {
        Character[] array = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            array[i] = Character.valueOf(str.charAt(i));
        }
        return array;
    }

    /**
     * Sorts a given array using the insertion sort method. This method is generic and will accept any class, assuming a comparator is given along with the array.
     * The method will modify the provided array; it does not return a sorted array.
     * @param <E> Any class you intend to sort.
     * @param arr The array you want sorted.
     * @param cmp The comparator that the algorithm will use to sort.
     */
    public static <E> void insertionSort(E[] arr, Comparator<? super E> cmp) {
        for (int i = 1; i < arr.length; i++) {
            E key = arr[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    /**
     * Finds the largest element in an array and returns it. This method is generic and will take any class type so long as a comparator is provided along with it.
     * @param <E> Any class that you can match a comparator to.
     * @param arr The array to search through.
     * @param cmp The comparator that will be used to compare elements and find the biggest one
     * @return The largest element in the array
     * @throws NoSuchElementException If the array is empty, a NoSuchElementException is thrown.
     */
    public static <E> E findMax(E[] arr, Comparator<? super E> cmp) throws NoSuchElementException {
        if (arr.length == 0)
            throw new NoSuchElementException("Array is empty, no such element exists.");

        //Create a copy of the array and sort the copy from smallest to biggest. Then return the last (biggest) element.
        E[] arrCopy = Arrays.copyOf(arr, arr.length);
        insertionSort(arrCopy, cmp);
        return arrCopy[arrCopy.length - 1];
    }

    /**
     * Takes any leading zeroes off of the front of a stringified integer.
     * Example: "00022835000" -> "2283500"
     * @param str The string to remove leading zeros from
     * @return The stringified integer with no leading zeros
     */
    public static String stripLeadingZeros(String str) {
        if (str == null || str.isEmpty()) return str;

        //Puts the string into a StringBuilder to make the process more efficient
        StringBuilder builder = new StringBuilder();
        builder.append(str);

        //While there is a zero at the beginning of the string, remove it.
        while (builder.indexOf("0") == 0)
            builder.deleteCharAt(0);

        if (builder.length() == 0) return "0";
        return builder.toString();
    }

    /**
     * Compares two stringified numbers. it first removes any leading zeros then compares them.
     *   -> If the strings have different lengths (after leading zero removal), return the comparison of lenghts using natural integer comparison.
     *   -> If the string are the same length, return the comparison of numbers using lexicographic string comparing. This is the same as through you compared integers.
     */
    public static class StringNumericalValueComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            //Remove leading zeroes
            String o1Stripped = stripLeadingZeros(o1);
            String o2Stripped = stripLeadingZeros(o2);

            //If the lengths are different, return a natural integer comparison of lengths
            //Else return a natural string comparison of the numbers
            if (o1Stripped.length() != o2Stripped.length())
                return Integer.compare(o1Stripped.length(), o2Stripped.length());
            else
                return o1Stripped.compareTo(o2Stripped);
        }
    }

    /**
     *  Compares two stringified numbers to see if they are similar. Meaning if they contain the same digits and the same amounts of them.
     *  It first trims the two numbers of any trailing zeros, the compares them.
     *   -> If the lengths of the strings are the same (after leading zeros removed), then return the natural comparison for the string lengths
     *   -> If the lengths are the same, compare based on number similarity
     * 
     *  Examples:
     *    -> cmp.compare("3412", "2134") -> 0  |  because they each have these digits, 1234
     *    -> cmp.compare("9455213", "5239541") ->  |  because they each have these digits, 1234559
     */
    public static class StringSimilarityComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            //Trim the numbers of leading zeroes
            String o1Trimmed = stripLeadingZeros(o1);
            String o2Trimmed = stripLeadingZeros(o2);

            //If lengths are different, return length comparison
            if (o1Trimmed.length() != o2Trimmed.length())
                return Integer.compare(o1Trimmed.length(), o2Trimmed.length());

            //Split the strings into character arrays and sort each array by smallest to largest digits
            Character[] charArray1 = splitStringToCharacterArray(o1Trimmed);
            Character[] charArray2 = splitStringToCharacterArray(o2Trimmed);
            insertionSort(charArray1, (char1, char2) -> char1.compareTo(char2));
            insertionSort(charArray2, (char1, char2) -> char1.compareTo(char2));

            //Reassemble the character arrays into strings (where the digits are now sorted)
            StringBuilder o1StrBuilder = new StringBuilder();
            for (Character charEl : charArray1)
                o1StrBuilder.append(charEl.charValue());
            StringBuilder o2StrBuilder = new StringBuilder();
            for (Character charEl : charArray2)
                o2StrBuilder.append(charEl.charValue());

            //Return a natural string comparison of the newly assembled strings
            return o1StrBuilder.toString().compareTo(o2StrBuilder.toString());
        }
    }

    public static class StringSimilarityGroupComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] o1, String[] o2) {
            int lengthComparison = Integer.compare(o1.length, o2.length);
            if (lengthComparison != 0)
                return lengthComparison;
            else {

                if (o1.length == 0) {
                    return 0;
                }

                StringNumericalValueComparator cmp = new StringNumericalValueComparator();
                String o1Max = findMax(o1, cmp);
                String o2Max = findMax(o2, cmp);
                return cmp.compare(o1Max, o2Max);
            }
        }
    }

    public static String[][] getSimilarityGroups(String[] arr) {
        if (arr.length == 0) return new String[0][];

        String[] arrCopy = Arrays.copyOf(arr, arr.length);

        StringSimilarityComparator cmp = new StringSimilarityComparator();
        insertionSort(arrCopy, cmp);

        ArrayList<String[]> similarityGroups = new ArrayList<>();
        int groupStartIndex = 0;
        for (int i = 0; i <= arrCopy.length; i++) {
            if (i != arrCopy.length && cmp.compare(arrCopy[groupStartIndex], arrCopy[i]) == 0)
                continue;

            String[] group = new String[i - groupStartIndex];
            for (int j = groupStartIndex; j < i; j++) {
                group[j - groupStartIndex] = arrCopy[j];
            }

            groupStartIndex = i;
            similarityGroups.add(group);
        }

        return similarityGroups.toArray(new String[0][0]);
    }

    public static String[] findMaximumSimilarityGroup(int[] arr) throws NoSuchElementException {
        if (arr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        String[] strArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            strArr[i] = arr[i] + "";
        }
        String[][] simGroups = getSimilarityGroups(strArr);
        StringSimilarityGroupComparator cmp = new StringSimilarityGroupComparator();
        return findMax(simGroups, cmp);
    }
}
