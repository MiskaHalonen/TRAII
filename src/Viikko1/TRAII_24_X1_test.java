package Viikko1;

// TRAII_24_X1_test.java SJ


// Testiluokka TRAII 2020 tehtÃ¤vÃ¤Ã¤n X1


import fi.uef.cs.tra.*;
import java.util.*;

public class TRAII_24_X1_test {

    static TRAII_24_X1 mySolution = new TRAII_24_X1_miskah();
    //                                              ^^^^ oma tunnus tÃ¤hÃ¤n

    public static void main(String[] args) {

        // tree size
        int N = 8;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // random seed
        int seed = 42;
        if (args.length > 1)
            seed = Integer.parseInt(args[1]);
        Random rnd = new Random(seed);

        // amount of print
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);


        boolean ok = true;

        // N test with different input sizes
        for (int i = 0; i < N; i++)
            ok &= testX1(i, rnd, print);

        // max 1000 random tests
        rnd.setSeed(System.currentTimeMillis());
        int nTest = 1000;
        int test = 0;
        int errors = 0;
        while (test < nTest) {
            test++;

            if (!testX1(rnd.nextInt(test+1), rnd, 0))
                errors++;
            if (errors >= 30)
                break;
        }

        if (errors > 0)
            ok = false;

        System.out.println("\nOut of " + test + " random tests, " + (test-errors) + " were correct.");

        if (ok)
            System.out.println("\nAll tests gave correct answer.\n\nRemeber self-evaluation.");
        else
            System.out.println("\nSome tests had errors.");

    } // main()

    /**
     * Test X1 size n tree
     * @param n number of tree nodes
     * @param rnd random number generator
     * @param print amount of print
     * @return true if test ok, false otherwise
     */
    static boolean testX1(int n, Random rnd, int print) {
        return testX1(randomList(n, rnd), print);
    }

    /**
     * Test X1 with a tree of given elements
     * @param L elements to put in tree
     * @param print amount of print
     * @return true if test ok, false otherwise
     */
    static <E extends Comparable<? super E>> boolean testX1(List<E> L, int print) {

        if (print > 0)
            System.out.println("\nTest, n = " + L.size());

        BTree<E> puu = new BTree<>();
        Set<BTreeNode<E>> BNexpected = makeTree(L, puu);

        if (print > 1) {
            System.out.println("Elements to tree: " + L);
            System.out.println("Branching nodes (expected): " + BNexpected);
        }
        Set<BTreeNode<E>> BN;
        try {
            BN = mySolution.branchingNodes(puu);
        } catch (Exception e) {
            if (print > 0) System.out.println("Exception from test: " + e);
            return false;
        }

        if (print > 1) {
            System.out.println("Branching nodes (result):   " + BN);
        }

        if (BNexpected.equals(BN)) {
            if (print > 0) System.out.println("Correct result");
            return true;
        } else {
            if (print > 0) System.out.println("Incorrect result");
            return false;
        }

    }


    /**
     * Generate tree from a list of elements.
     * Returns braching nodes
     * @param elements to be added
     * @param tree input tree (must be empty)
     * @param <E> solmujen alkiotyyppi
     * @return luodun puun haaratutumissolmut
     */
    public static <E extends Comparable<? super E>> Set<BTreeNode<E>> makeTree(List<E> elements, BTree<E> tree) {
        Set<BTreeNode<E>> bNodes = new HashSet<>();
        if (tree.getRoot() != null) System.out.println("WARNING: tree not empty");
        for (E x : elements) {
            BTreeNode<E> hs = addToTree(tree, x);
            if (hs != null)
                bNodes.add(hs);
        }
        return bNodes;
    }

    /**
     * Create a list of random unique integers
     * @param n number of elements
     * @param rnd random number generator
     * @return random list
     */
    public static ArrayList<Integer> randomList(int n, Random rnd) {
        HashSet<Integer> HS = new HashSet<>(n*2);
        ArrayList<Integer> AL = new ArrayList<>(n);
        int i = 0;
        while (i < n) {
            int x = rnd.nextInt(n*2+1);
            if (HS.add(x)) {
                AL.add(x);
                i++;
            }
        }
        return AL;
    }


    /**
     * Insert to tree to about in-order
     * Adds even it it is threre already
     *
     * @param T   input tree
     * @param x   element to add
     * @param <E> elelemtn type
     * @return new braching node, or null if no new braching nodes were created
     */
    public static <E extends Comparable<? super E>> BTreeNode<E> addToTree(BTree<E> T, E x) {
        BTreeNode<E> n = T.getRoot();
        if (n == null) {
            T.setRoot(new BTreeNode<>(x));
            return null;
        }

        while (true) {
            if (x.compareTo(n.getElement()) < 0) {
                if (n.getLeftChild() == null) {
                    n.setLeftChild(new BTreeNode<>(x));
                    return (n.getRightChild() == null) ? n : null;
                } else
                    n = n.getLeftChild();
            } else {
                if (n.getRightChild() == null) {
                    n.setRightChild(new BTreeNode<>(x));
                    return (n.getLeftChild() == null) ? n : null;
                } else
                    n = n.getRightChild();
            }
        } // while

    } // addToTree()

}