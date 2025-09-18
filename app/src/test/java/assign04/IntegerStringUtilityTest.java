package assign04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerStringUtilityTest {
    Integer[] integerArray;
    Integer[] emptyIntegerArray;
    String[] stringArray;
    String[] emptyStringArray;

    @BeforeEach
    void prepareArrays() {
        integerArray = new Integer[] { 5, 8, 2, 4, 9, 10, 14, 89, 100, 34 };
        emptyIntegerArray = new Integer[0];

        stringArray = new String[] { "banana", "apple", "orange", "zebra", "carrot", "tangerine" };
        emptyStringArray = new String[0];
    }

    @Test
    void testInsertionSortMethodWithIntegers() {
        Integer[] testSortedArr = new Integer[] { 2, 4, 5, 8, 9, 10, 14, 34, 89, 100 };

        IntegerStringUtility.insertionSort(integerArray, (n1, n2) -> Integer.compare(n1, n2));

        assertNotNull(integerArray);
        for (int i = 0; i < integerArray.length; i++) {
            assertTrue(testSortedArr[i].equals(integerArray[i]));
        }
    }

    @Test
    void testInsertionSortMethodWithStrings() {
        String[] testSortedArr = new String[] { "apple", "banana", "carrot", "orange", "tangerine", "zebra" };

        IntegerStringUtility.insertionSort(stringArray, (n1, n2) -> n1.compareTo(n2));

        assertNotNull(stringArray);
        for (int i = 0; i < stringArray.length; i++) {
            assertTrue(testSortedArr[i].equals(stringArray[i]));
        }
    }

    @Test
    void testFindMaxWithIntegers() {
        int result = IntegerStringUtility.findMax(integerArray, (n1, n2) -> Integer.compare(n1, n2));
        assertEquals(100, result);
    }

    @Test
    void testFindMaxWithEmptyIntegerArray() {
        assertThrows(NoSuchElementException.class,
                () -> IntegerStringUtility.findMax(emptyIntegerArray, (n1, n2) -> Integer.compare(n1, n2)));
    }

    @Test
    void testFindMaxWithStrings() {
        String result = IntegerStringUtility.findMax(stringArray, (s1, s2) -> s1.compareTo(s2));
        assertNotNull(result);
        assertEquals("zebra", result);
    }

    @Test
    void testFindMaxWithEmptyStringArray() {
        assertThrows(NoSuchElementException.class,
                () -> IntegerStringUtility.findMax(emptyStringArray, (s1, s2) -> s1.compareTo(s2)));
    }

    @Test
    void testStringNumericValueComparator() {
        // TODO : Find out if these tests should account for negative numbers.
        // I remember the assignment saying something about it but I don't know where to
        // find it.
        IntegerStringUtility.StringNumericalValueComparator cmp = new IntegerStringUtility.StringNumericalValueComparator();

        String num1 = "84542837655628";
        String num2 = "487356219485643921821654623";
        String num3 = "84542837655628";
        String num4 = "6437651276543781264";

        assertEquals(0, cmp.compare("", ""));
        assertEquals(0, cmp.compare(num1, num1));
        assertEquals(0, cmp.compare(num1, num3));
        assertEquals(0, cmp.compare(num3, num3));
        assertTrue(cmp.compare(num1, num2) < 0);
        assertTrue(cmp.compare(num2, num1) > 0);
        assertTrue(cmp.compare(num4, num2) < 0);
        assertTrue(cmp.compare(num2, num4) > 0);
        assertTrue(cmp.compare(num3, num4) < 0 && cmp.compare(num4, num2) < 0);
    }

    @Test
    void testStringSimilarityComparator() {
        IntegerStringUtility.StringSimilarityComparator cmp = new IntegerStringUtility.StringSimilarityComparator();

        // Similar
        String num1 = "57245";
        String num2 = "55274";
        String num3 = "74525";

        //Similar
        String num4 = "9435645292736546437623";
        String num5 = "3465392447562923643756";

        assertEquals(0, cmp.compare("", ""));
        assertEquals(0, cmp.compare(num1, num2));
        assertEquals(0, cmp.compare(num2, num3));
        assertEquals(0, cmp.compare(num3, num1));
        assertEquals(0, cmp.compare(num4, num5));

        assertTrue(cmp.compare(num4, num1) > 0);
        assertTrue(cmp.compare(num1, num4) < 0);
        assertTrue(cmp.compare(num5, num3) > 0);
        assertTrue(cmp.compare(num3, num5) < 0);
    }

    @Test
    void testGetSimilarityGroups(){
        String[] strArr = new String[]{"2341", "123", "2134", "2431", "312", "2143"};
        String[][] expectedGroupedArray = new String[2][];
            expectedGroupedArray[0] = new String[]{"123", "312"};
            expectedGroupedArray[1] = new String[]{"2341", "2134", "2431", "2143"};
        
        String[][] result = IntegerStringUtility.getSimilarityGroups(strArr);

        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
        assertEquals(4, result[1].length);
        for (int row = 0; row < expectedGroupedArray.length; row++){
            for (int col = 0; col < expectedGroupedArray[row].length; col++){
                assertEquals(result[row][col], expectedGroupedArray[row][col]);
            }
        }
    }

    @Test
    void testGetSimilarityGroupsWithEmptyArray(){
        String[] strArr = new String[0];
        String[][] result = IntegerStringUtility.getSimilarityGroups(strArr);

        assertNotNull(result);
        assertEquals(0, result.length);
        assertThrows(IndexOutOfBoundsException.class,() -> {
            @SuppressWarnings("unused")
            String[] test = result[0];
        });
    }

    @Test
    void testGetSimilarityGroupsWithEmptyStringInArray(){
        //Testing with just an empty string
        String[] strArr = new String[]{""};
        String[][] result = IntegerStringUtility.getSimilarityGroups(strArr);

        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals(1, result[0].length);
        assertEquals("", result[0][0]);

        //Testing with numbers and empty strings
        String[] strArr2 = new String[]{"2341", "123", "", "2431", "312", "", "", "2143", "2134"};
        String[][] expectedResult = new String[3][];
            expectedResult[0] = new String[]{"", "", ""};
            expectedResult[1] = new String[]{"123", "312"};
            expectedResult[2] = new String[]{"2341", "2431", "2143", "2134"};
        String[][] result2 = IntegerStringUtility.getSimilarityGroups(strArr2);
        
        assertNotNull(result2);
        assertEquals(3, result2.length);
        assertEquals(3, result2[0].length);
        assertEquals(2, result2[1].length);
        assertEquals(4, result2[2].length);
        for (int row = 0; row < expectedResult.length; row++){
            for (int col = 0; col < expectedResult[row].length; col++){
                assertEquals(result2[row][col], expectedResult[row][col]);
            }
        }
    }

    @Test
    void testTrimLeadingZeros() {
        assertTrue("84684".equals(IntegerStringUtility.stripLeadingZeros("0084684")));
        assertTrue("8468400".equals(IntegerStringUtility.stripLeadingZeros("8468400")));
        assertTrue("8468400000".equals(IntegerStringUtility.stripLeadingZeros("000008468400000")));
        assertTrue("0".equals(IntegerStringUtility.stripLeadingZeros("00000")));
    }

    @Test
    void testStringSimilarityGroupComparatorDiffLengths() {
        String[] small = { "1", "2" };
        String[] large = { "3", "4", "5" };
        IntegerStringUtility.StringSimilarityGroupComparator cmp = new IntegerStringUtility.StringSimilarityGroupComparator();
        assertTrue(cmp.compare(small, large) < 0);
    }

    @Test
    void testStringSimilarityGroupComparatorSameLengths() {
        String[] groupA = { "1", "2", "10" };
        String[] groupB = { "3", "4", "5" };
        IntegerStringUtility.StringSimilarityGroupComparator cmp = new IntegerStringUtility.StringSimilarityGroupComparator();
        assertTrue(cmp.compare(groupA, groupB) > 0);
    }

    @Test
    void testEmptyArrays() {
        String[] empty1 = {};
        String[] empty2 = {};
        String[] notEmpty = { "5" };
        
        IntegerStringUtility.StringSimilarityGroupComparator cmp = new IntegerStringUtility.StringSimilarityGroupComparator();
        assertEquals(0, cmp.compare(empty1, empty2));
        assertTrue(cmp.compare(empty1, notEmpty) < 0);
    }
}
