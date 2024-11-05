package Viikko2;


import java.util.*;

public class TRAII_24_X2_test {

    static TRAII_24_X2 myClass = new TRAII_24_X2_miskah();
    //                                              ^^^^ own id here


    public static void main(String[] args) {

        // max number of elements
        int testMax = 1000000;
        if (args.length > 0)
            testMax = Integer.parseInt(args[0]);

        int seeed = 42;
        if (args.length > 1)
            seeed = Integer.parseInt(args[1]);
        Random rnd = new Random(seeed);

        // amount of print
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);


        // call first without prints for 2 seconds
        lammita(new HashSet<>(), testMax, rnd, 2);
        lammita(new TreeSet<>(), testMax, rnd, 2);
        lammita(new LinkedList<>(), testMax, rnd, 2);

        // the the actual test with a couple of collections
        System.out.println("\nTest HashSet, should be constant time (except cache impacts)");
        testX2(new HashSet<>(), testMax, 10, 0, rnd, print);

        System.out.println("\nTest TreeSet, should be logarithmic time (except cache impacts)");
        testX2(new TreeSet<>(), testMax, 10, 0, rnd, print);

        System.out.println("\nTest LinkedList, should be linear time (except cache impacts)");
        testX2(new LinkedList<>(), testMax/200, 2, 0, rnd, print);

    } // main()



    /** Test the task with input size 1..n
     * @param n maximum input size
     * @param multiplier growing factor
     * @param end time moment (System.nanoTime) after which iteration is broken
     * @param rnd random number generator
     * @param print amount of print
     */
    static void testX2(Collection<Double> C, int n, int multiplier, long end, Random rnd, int print) {
        int k = 1;
        while (C.size() < n) {
            while (C.size() < k)
                C.add(rnd.nextDouble());
            testX2(C, print);
            if (end > 0 && System.nanoTime() > end)
                break;
            k *= multiplier;
        }
    }



    /**
     * Calls the test without print until time is up.
     * @param n maximum input size
     * @param rnd random number generator
     * @param seconds duration of calls
     */
    static void lammita(Collection<Double> C, int n, Random rnd, int seconds) {

        System.out.println("Warm (" + C.getClass().toString() + ") up starts " + seconds + "s");
        long loppu = System.nanoTime() + seconds*1_000_000_000L;
        while (System.nanoTime() < loppu)
            testX2(C, n, 2, loppu, rnd, 0);
        System.out.println("Warm up ends");
    }


    /** Test the task for a collection
     * @param print amount of print

     * @param C ready collection to test
     * @param print amount of print
     */
    static void testX2(Collection<Double> C, int print) {

        if (print > 1)
            System.out.println("Test, Collection = " + C.getClass().toString() + " n = " + C.size());

        long start = System.nanoTime(); // measure test time

        // call the measurement
        long result = myClass.containsTime(C);

        long end = System.nanoTime();

        if (print > 0) System.out.println("  result = " + result + " ns");

        if (print > 0 && end-start > 1_000_1000_1000L)
            System.out.println("Warning: test was too slow (over 1s)");

        if (print > 3)  // adjust here if you want to see how long your test lasted
            System.out.println("Test lasted " + ((end-start)/1_000_000.0) + " ms");


    }


}