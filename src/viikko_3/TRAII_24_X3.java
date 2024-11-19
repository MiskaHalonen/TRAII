package viikko_3;

import java.util.List;
import java.util.SortedMap;

public interface TRAII_24_X3 {

    /**
     * Test the time and remove elements from lists A and B.
     * Measures the time for each input size n= { min, min*2, min*4, min*8, ... <=max}.
     * For each input size n, measure the time to execute the following operation sequence:
     * 1. add n elements to list A
     * 2. iterate list A and add each element to list B
     * 3. remove n times last element of A and last element of B
     * (both lists will be emptied)
     * Last input size to measure is the largest min*2^k which is larger or equal than max.
     * As a reusult, return a map where the key id each input size and as the value
     * the measured time for that input size in nanoseconds.
     * @param A list under test
     * @param B list under test
     * @param min minumum number of elements
     * @param max maximum number of elements
     * @return map containing all the test results
     */
    public SortedMap<Integer, Long> listSpeed(List<Integer> A, List<Integer> B, int min, int max);


}