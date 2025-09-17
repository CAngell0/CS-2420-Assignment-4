package assign04;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IntegerStringUtilityTest {
    
    @Test
    void testInsertMethodWithIntegers(){
        Integer[] testArr = new Integer[]{5, 8, 2, 4, 9, 10 ,14, 89, 100, 34};
        Integer[] testSortedArr = new Integer[]{2, 4, 5, 8, 9, 10, 14, 34, 89, 100};

        IntegerStringUtility.insertionSort(testArr, (n1, n2) -> Integer.compare(n1, n2));
        
        assertNotNull(testArr);
        for (int i = 0; i < testArr.length; i++){
            assertTrue(testSortedArr[i].equals(testArr[i]));
        }
    }

    @Test
    void testInsertMethodWithStrings(){
        String[] testArr = new String[]{"banana", "apple", "orange", "zebra", "carrot", "tangerine"};
        String[] testSortedArr = new String[]{"apple", "banana", "carrot", "orange", "tangerine", "zebra"};

        IntegerStringUtility.insertionSort(testArr, (n1, n2) -> n1.compareTo(n2));
        
        assertNotNull(testArr);
        for (int i = 0; i < testArr.length; i++){
            assertTrue(testSortedArr[i].equals(testArr[i]));
        }
    }

    @Test
    void testTrimLeadingZeros(){
        assertTrue("84684".equals(IntegerStringUtility.stripLeadingZeros("0084684")));
        assertTrue("8468400".equals(IntegerStringUtility.stripLeadingZeros("8468400")));
        assertTrue("8468400000".equals(IntegerStringUtility.stripLeadingZeros("000008468400000")));
    }
}
