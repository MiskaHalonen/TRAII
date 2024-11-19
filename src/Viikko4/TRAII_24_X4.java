package Viikko4;


import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Vertex;

import java.util.List;

public interface TRAII_24_X4 {

    /**
     * Finds and returns a cycle in graph G which contains vertex v0.
     * Cycle is returned as a list of vertices so that the first and the last
     * element of list is vertex v0, and between are all those vertices that form the cycle
     * in the order of the cycle. I.e., successive vertices are neighbors.
     * If not such cycle exists, returns null.
     * @param G input graph
     * @param v0 the vertex in cycle
     * @return vertices of the cycle as a list, or null if no such cycle.
     */
    public List<Vertex> cycleWithVertex(DiGraph G, Vertex v0);

}