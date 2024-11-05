package Viikko1;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;
import java.util.Set;

public interface TRAII_24_X1 {

    /**
     * Braching nodes of a binary tree.
     * Returns a set all the nodes of tree T that have at least one child.
     * @param T input tree
     * @param <E> element type
     * @return set of braching nodes
     */
    public <E> Set<BTreeNode<E>> branchingNodes(BTree<E> T);

}