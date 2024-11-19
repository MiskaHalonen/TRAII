package Viikko4;
import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Vertex;

import java.util.List;


public class TRAII_24_X4_miskah implements TRAII_24_X4 {

    /**
     * SELF-EVALUATION HERE
     *
     *
     *
     */

    /**
     * Finds and returns a cycle in graph G which contains vertex v.
     * Cycle is returned as a list of vertices so that the first and the last
     * element of list is vertex v, and between are all those vertices that form the cycle
     * in the order of the cycle. I.e., successive vertices are neighbors.
     * If not such cycle exists, returns null.
     *
     * @param G input graph
     * @param v0 the vertex in cycle
     * @return vertices of the cycle as a list, or null if no such cycle.
     */
    @Override
    public List<Vertex> cycleWithVertex(DiGraph G, Vertex v0) {

        // base colouring of vertices, use if you want
        // but do not assume some base colouring
        GraphMaker.color(G, DiGraph.WHITE);

        // TODO

        // other methods are allowed and recommened

        return null;

    }

}