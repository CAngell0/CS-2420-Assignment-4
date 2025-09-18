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
    void prepareArrays(){
        integerArray = new Integer[]{5, 8, 2, 4, 9, 10 ,14, 89, 100, 34};
        emptyIntegerArray = new Integer[0];

        stringArray = new String[]{"banana", "apple", "orange", "zebra", "carrot", "tangerine"};
        emptyStringArray = new String[0];
    }
    
    @Test
    void testInsertionSortMethodWithIntegers(){
        Integer[] testSortedArr = new Integer[]{2, 4, 5, 8, 9, 10, 14, 34, 89, 100};

        IntegerStringUtility.insertionSort(integerArray, (n1, n2) -> Integer.compare(n1, n2));
        
        assertNotNull(integerArray);
        for (int i = 0; i < integerArray.length; i++){
            assertTrue(testSortedArr[i].equals(integerArray[i]));
        }
    }

    @Test
    void testInsertionSortMethodWithStrings(){
        String[] testSortedArr = new String[]{"apple", "banana", "carrot", "orange", "tangerine", "zebra"};

        IntegerStringUtility.insertionSort(stringArray, (n1, n2) -> n1.compareTo(n2));
        
        assertNotNull(stringArray);
        for (int i = 0; i < stringArray.length; i++){
            assertTrue(testSortedArr[i].equals(stringArray[i]));
        }
    }

    @Test
    void testFindMaxWithIntegers(){
        int result = IntegerStringUtility.findMax(integerArray, (n1, n2) -> Integer.compare(n1, n2));
        assertEquals(100, result);
    }

    @Test
    void testFindMaxWithEmptyIntegerArray(){
        assertThrows(NoSuchElementException.class, () -> IntegerStringUtility.findMax(emptyIntegerArray, (n1, n2) -> Integer.compare(n1, n2)));
    }

    @Test
    void testFindMaxWithStrings(){
        String result = IntegerStringUtility.findMax(stringArray, (s1, s2) -> s1.compareTo(s2));
        assertNotNull(result);
        assertEquals("zebra", result);
    }

    @Test
    void testFindMaxWithEmptyStringArray(){
        assertThrows(NoSuchElementException.class, () -> IntegerStringUtility.findMax(emptyStringArray, (s1, s2) -> s1.compareTo(s2)));
    }

    @Test
    void testStringNumericValueComparator(){
        //TODO : Find out if these tests should account for negative numbers. 
        //       I remember the assignment saying something about it but I don't know where to find it.
        IntegerStringUtility.StringNumericalValueComparator cmp = new IntegerStringUtility.StringNumericalValueComparator();

        String num1 = "84542837655628";
        String num2 = "487356219485643921821654623";
        String num3 = "84542837655628";
        String num4 = "6437651276543781264";

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
    void testStringSimilarityComparator(){
        IntegerStringUtility.StringSimilarityComparator cmp = new IntegerStringUtility.StringSimilarityComparator();

        //Similar
        String num1 = "57245";
        String num2 = "55274";
        String num3 = "74525";
    }

    @Test
    void testTrimLeadingZeros(){
        assertTrue("84684".equals(IntegerStringUtility.stripLeadingZeros("0084684")));
        assertTrue("8468400".equals(IntegerStringUtility.stripLeadingZeros("8468400")));
        assertTrue("8468400000".equals(IntegerStringUtility.stripLeadingZeros("000008468400000")));
        assertTrue("0".equals(IntegerStringUtility.stripLeadingZeros("00000")));
    }
}
