package Viikko1;

import fi.uef.cs.tra.*;

import java.util.HashSet;
import java.util.Set;

public class TRAII_24_X1_miskah implements TRAII_24_X1 {
    /**
     * SELF-EVALUATION HERE:
     *
     * nyt kun sai aineiston äärellä taas katsoa miten nuo puiden komennot meni
     * niin siedettävä tehtävä. aika vaativuus O(n)
     *
     *
     */
    /**
     * Braching nodes of a binary tree.
     * Returns a set all the nodes of tree T that have at least one child.
     * @param T input tree
     * @param <E> element type
     * @return set of braching nodes
     */
    @Override
    public <E> Set<BTreeNode<E>> branchingNodes(BTree<E> T) {
        Set<BTreeNode<E>> hsj = new HashSet<>();
        if (T.getRoot() != null) {
            collectBranchingNodes(T.getRoot(), hsj);
        }
        return hsj;
    }


    private <E> void collectBranchingNodes(BTreeNode<E> node, Set<BTreeNode<E>> branchingNodes) {
        if (node == null) return;

        // Check if the node is a branching node (has at least one child)
        if (node.getLeftChild() != null || node.getRightChild() != null) {
            branchingNodes.add(node);
        }

        // Recurse to the left and right children
        collectBranchingNodes(node.getLeftChild(), branchingNodes);
        collectBranchingNodes(node.getRightChild(), branchingNodes);
        }






}