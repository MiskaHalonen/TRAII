package Viikko4;

import fi.uef.cs.tra.*;
import fi.uef.cs.tra.Vertex;

import java.util.*;

public class TRAII_24_X4_test {

    private static Random rnd = new Random();

    private static TRAII_24_X4 myClass = new TRAII_24_X4_miskah(); /* <-- Own id here */

    public static void main(String[] args) {

        int nv = 7;
        int ne = 9;

        if (args.length > 0)
            nv = Integer.parseInt(args[0]);

        if (args.length > 1)
            ne = Integer.parseInt(args[1]);

        int seed = nv + ne;

        if (args.length > 2)
            seed = Integer.parseInt(args[2]);

        rnd.setSeed(seed);

        // randon acyclic graph
        DiGraph graph1 = GraphMaker.createDAG(nv, ne, seed);
        System.out.println("\nGraph1 (DAG):");
        System.out.println(GraphMaker.toString(graph1, 0));


        boolean ok = true;
        System.out.println("\nFind a cycle from each vertex:");
        for (Vertex v0 : graph1.vertices()) {
            List<Vertex> result = myClass.cycleWithVertex(graph1, v0);
            System.out.println("From vertex " + v0 + ", cycle: : " + result);
            if (result != null) {
                ok = false;
                System.out.println("Wrong: DAG has no cycle!");
                boolean okCycle = checkCycle(result, v0, true);
                ok &= okCycle;
            }
        }

        // Another DAG
        seed = (int) System.currentTimeMillis();
        DiGraph graph2 = GraphMaker.createDAG(nv + 1, ne + 4, seed);
        System.out.println("\nDAG2:");
        System.out.println(GraphMaker.toString(graph2, 0));

        System.out.println("\nFind a cycle from each vertex:");
        for (Vertex v0 : graph2.vertices()) {
            List<Vertex> result = myClass.cycleWithVertex(graph2, v0);
            System.out.println("From vertex " + v0 + ", cycle: : " + result);
            if (result != null) {
                ok = false;
                System.out.println("Wrong: DAG has no cycle!");
                boolean okKeha = checkCycle(result, v0, true);
                ok &= okKeha;
            }
        }

        if (ok)
            System.out.println("\nNo cycles from acyclic graphs, good.");

        // add a cycle
        Vertex vk = GraphMaker.addRandomCycle(graph1, nv / 2 + 2, false);
        System.out.println("\nGraph 1_1:");
        System.out.println(GraphMaker.toString(graph1, 0));
        List<Vertex> result = myClass.cycleWithVertex(graph1, vk);
        System.out.println("From vertex " + vk + ", cycle : " + result);
        if (result != null) {
            ok &= checkCycle(result, vk, true);
        } else {
            System.out.println("Should have found a cycle.");
            ok = false;
        }

        // now there should be a cycle from all vertices of the result
        if (result != null) {
            for (Vertex v0 : result) {
                List<Vertex> result2 = myClass.cycleWithVertex(graph1, v0);
                System.out.println("From vertex " + v0 + ", cycle : " + result2);
                if (result != null) {
                    ok &= checkCycle(result2, v0, true);
                } else {
                    System.out.println("Should have found a cycle.");
                    ok = false;
                }
            }
        }


        // add a cycle to the other graph
        vk = GraphMaker.addRandomCycle(graph2, nv, false);
        System.out.println("\nGraph2_2:");
        System.out.println(GraphMaker.toString(graph2, 0));
        result = myClass.cycleWithVertex(graph2, vk);
        System.out.println("From vertex " + vk + ", cycle : " + result);
        if (result != null) {
            ok &= checkCycle(result, vk, true);
        } else {
            System.out.println("Should have found a cycle.");
            ok = false;
        }


        if (ok)
            System.out.println("\nFirst tests ok.");
        else
            System.out.println("\nSome errors first tests.");

        // max 1000 random tests
        rnd.setSeed(System.currentTimeMillis());
        int nTest = 1000;
        int test = 0;
        int errors = 0;
        while (test < nTest) {
            test++;

            int v = rnd.nextInt(test+1) + 2;
            int e = v+rnd.nextInt(v);

            // randomly create DAG or cyclic graph
            if (!test1(rnd, v, e, rnd.nextBoolean()))
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



    }

    static boolean test1(Random rnd, int n, int e, boolean stronglyConnected) {
        DiGraph G = stronglyConnected ?
                GraphMaker.createCyclicDiGraph(n, e)
                : GraphMaker.createDAG(n, e, rnd.nextInt());

        return test(G, GraphMaker.randomVertex(G), stronglyConnected);
    }



    static boolean test(DiGraph G, Vertex v0, boolean expected) {
        boolean ok = true;
        List<Vertex> result = myClass.cycleWithVertex(G, v0);

        // correctly no cycle
        if (!expected && result == null)
            return true;

        // not found cycle
        if (expected && result == null)
            return false;

        boolean okCycle = checkCycle(result, v0, false);

        // correct expected cycle
        return okCycle && expected;
    }

    static boolean checkCycle(List<Vertex> cycle, Vertex v0, boolean print) {
        boolean ok = true;
        if (cycle == null)
            return false; // this checks only cycles
        if (cycle.size() < 2) {
            if (print) System.out.println("A cycle has to have at least two vertices (at least {v0, v0})");
            return false;
        }

        if (cycle.get(0) != v0) {
            if (print) System.out.println("First of the cycle should have been " + v0);
            ok = false;
        }

        if (cycle.get(cycle.size() - 1) != v0) {
            if (print) System.out.println("Last of the cycle should have been " + v0);
            ok = false;
        }

        Iterator<Vertex> it = cycle.listIterator();
        Vertex prev = it.next();
        while (it.hasNext()) {
            Vertex n = it.next();
            if (!prev.isAdjacent(n)) {
                if (print) System.out.println("There is no edge (" + prev + " -> " + n + ") in the graph!");
                ok = false;
            }
            prev = n;
        }

        if (ok)
            if (print) System.out.println("ok");

        return ok;
    }


} // class()