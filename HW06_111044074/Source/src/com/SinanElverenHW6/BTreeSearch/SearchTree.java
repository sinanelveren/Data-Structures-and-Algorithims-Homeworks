package com.SinanElverenHW6.BTreeSearch;

public interface SearchTree<E> {
    boolean add(E item);
    E find(E target);
    boolean contains(E target);
    boolean remove(E o);
    E delete(E o);

}
