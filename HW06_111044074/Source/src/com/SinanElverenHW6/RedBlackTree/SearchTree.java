package com.SinanElverenHW6.RedBlackTree;

/**
 *
 5
 down vote
 accepted
 In contrast to AVL trees where rotations for deletions may propagate up to the root (although having
 at most one (double-)rotation for insert), RB trees require a constant (at most 2 for insert,
 at most 3 for deletion) number of rotations. What can take logarithmically much time during deletion in an
 RB tree is the recoloring which may propagate up to the root, which means insert and delete
 have the same asymptotics for both AVL and RB trees.
 */

public interface SearchTree<E> {
    boolean add(E item);
    E find(E target);
    boolean contains(E target);
    boolean remove(E o);
    E delete(E o);

}
