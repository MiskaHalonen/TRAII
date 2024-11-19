package Viikko3;

import java.util.*;

/**
 * Test for X3
 */
public class TRAII_24_X3_test {

    public static void main(String[] args) {

        // you can change these
        int N1 = 100;
        if (args.length > 0)
            N1 = Integer.parseInt(args[0]);

        int N2 = 2000000;
        if (args.length > 0)
            N2 = Integer.parseInt(args[1]);

        TRAII_24_X3 myClass = new TRAII_24_X3_miskah();

        // test with a couple of different list implementations
        test(myClass, new ArrayList<>(), N1, N2);

        test(myClass, new Vector<>(), N1, N2);

        test(myClass, new LinkedList<>(), N1, N2);

    }


    /**
     * Test taks X3 with a single list
     * @param myClass myClass measuring class
     * @param A myClass list
     * @param min smallest input size
     * @param max upper bound of input size
     */
    static void test(TRAII_24_X3 myClass, List<Integer> A, int min, int max) {
        System.out.println("Test " + A.getClass().getName() + " " + min + ".." + max);
        List<Integer> B = null; // creat another list
        try {
            B = A.getClass().getConstructor().newInstance();
        } catch (Exception ignored) {
            return;
        }

        // call measurement
        long start = System.nanoTime();
        SortedMap<Integer, Long> tulos = myClass.listSpeed(A, B, min, max);
        long time = System.nanoTime() - start;

        // check if keys (element counts) are correct
        Set<Integer> expectedKeys = expectedKeys(min, max);
        System.out.println("Correct input sizes: " + expectedKeys.equals(tulos.keySet()));

        // print times used
        System.out.println("Measurement took " + (1.0*time / (1000.0*1000*1000*expectedKeys.size())) + " s/elementcount.");

        // print content of map
        System.out.println("n   ns    ns/n");
        for (Map.Entry<Integer, Long> e : tulos.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue() + " " +
                    e.getValue()/e.getKey());
        }
    }

    /**
     * Generates a sset with min, min*2, min*4, min*2^2 ...
     * @param min
     * @param max
     * @return set
     */
    static Set<Integer> expectedKeys(int min, int max) {
        Set<Integer> keys = new TreeSet<>();
        int k = min;
        while (k <= max) {
            keys.add(k);
            k *= 2;
        }
        return keys;
    }

}