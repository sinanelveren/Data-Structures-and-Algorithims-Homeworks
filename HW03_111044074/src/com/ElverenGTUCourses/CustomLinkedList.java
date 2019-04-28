package com.ElverenGTUCourses;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * CustomLinkedList class that a generic linked list which have ability disable / eanble
 * For part 2 of HW3
 * @author Sinan Elveren
 */
public class CustomLinkedList<T> extends LinkedList<T> {

    /**
     * Arraylist holds indexes of disabeled elements
     */
    private ArrayList<Integer> indexesOfDisabels = new ArrayList<Integer>();
    private int size;


    /**
     * No paramater costructor that calling super constructor
     */
    public CustomLinkedList() {
        super();
        this.size = super.size();

    }

    /**
     * Getter for getIndexesOfDisabels
     * @return ArrayList indexesOfDisabels
     */
    public ArrayList<Integer> getIndexesOfDisabels() {
        return indexesOfDisabels;
    }

    /**
     * Add new index which have disabled element into indexesOfDisabels
     * @param index index which element will disable
     */
    private void addIndexesOfDisabels(int index) {
        this.indexesOfDisabels.add(index);
    }

    /**
     *  Remove element which you will enable from indexesOfDisabels
     * @param index index which you will enable
     */
    private void removeIndexesOfDisabels(int index) {
        this.indexesOfDisabels.remove((Integer) index);
    }

    /**
     * Disable an element of linked list
     * @param index index info which you want to disable an element
     * @return true if the element has been disabled succesfuly else false
     */
    public boolean disable(int index) {
        if (super.size() <= index || 0 > index) {
            return false;
        } else {
            for (int i = 0; i < this.indexesOfDisabels.size(); i++) {
                if(index == this.indexesOfDisabels.get(i))
                    return false;
            }

            this.addIndexesOfDisabels(index);
            size--;
            return true;
        }
    }

    /**
     * Enable an element in disabeled elements
     * @param index index info which you want to enable an element
     * @return true if the element has been enable succesfuly else false
     */
    public boolean enable(int index) {
        if (super.size() <= index || 0 > index) {
            return false;
        }

        for (int i = 0; i < this.indexesOfDisabels.size(); i++) {
            //matched via disabled index
            if (index == this.indexesOfDisabels.get(i)) {
                removeIndexesOfDisabels(index);
                size++;
                return true;
            }
        }

        return false;
    }


    /**
     * Print elements info which is disabled
     */
    public void showDisabled() {
        System.out.println("\nDisabeled list, size :" + this.indexesOfDisabels.size());
        for (int i = 0; i < this.indexesOfDisabels.size(); i++) {
            System.out.println((this.indexesOfDisabels.get(i)) +".  " + super.get(this.indexesOfDisabels.get(i)).toString());
        }
    }


    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        this.size = super.size() - this.indexesOfDisabels.size();
        return this.size;
    }


    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws NoSuchElementException  exception not found element
     */
    @Override
    public boolean remove(Object o)  {

        if (this.indexOf(o) == -1)
            throw new NoSuchElementException(o.toString());

        for (int i = 0; i < this.indexesOfDisabels.size(); i++) {
            if (this.indexesOfDisabels.get(i) > super.indexOf(o)) {
                this.indexesOfDisabels.add(i, this.indexesOfDisabels.get(i) - 1);
                this.indexesOfDisabels.remove(i+1);
            }
        }
        size--;
        return super.remove(o);
    }


    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T remove(int index) {
        if (this.size() <= index || 0 > index)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        index = super.indexOf(this.get(index));
        T element = super.remove(index);

        for (int i = 0; i < this.indexesOfDisabels.size(); i++) {
            if (this.indexesOfDisabels.get(i) > index) {
                this.indexesOfDisabels.add(i, this.indexesOfDisabels.get(i) - 1);
                this.indexesOfDisabels.remove(i+1);
            }
        }

        size--;
        return element;
    }


    /**
     * Returns the element at the specified position in this list
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T get(int index) {
        if (index >= this.size() || index < 0)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        for (int i = 0; i < this.indexesOfDisabels.size() ; i++) {
            if (this.indexesOfDisabels.get(i) == index)
                index++;
            else if (this.indexesOfDisabels.get(i) < index)
                index++;
        }

        return super.get(index);
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T set(int index, T element) {
        if (index >= this.size() || index < 0)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        for (int i = 0; i < this.indexesOfDisabels.size() ; i++)
            if (this.indexesOfDisabels.get(i) == index || this.indexesOfDisabels.get(i) < index)
                index++;

        return super.set(index, element);
    }



    /**
     * Returns a list-iterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * Obeys the general contract of {@code List.listIterator(int)}.
     * The list-iterator is <i>fail-fast</i>: if the list is structurally
     * modified at any time after the Iterator is created, in any way except
     * through the list-iterator's own {@code remove} or {@code add}
     * methods, the list-iterator will throw a
     * {@code ConcurrentModificationException}.  Thus, in the face of
     * concurrent modification, the iterator fails quickly and cleanly, rather
     * than risking arbitrary, non-deterministic behavior at an undetermined
     * time in the future.
     *
     * @param index index of the first element to be returned from the
     *              list-iterator (by a call to {@code next})
     * @return a ListIterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException {@inheritDoc} if the index out of size or etc
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        if (index >= this.size() || index < 0)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        for (int i = 0; i < this.indexesOfDisabels.size() ; i++)
            if (this.indexesOfDisabels.get(i) == super.indexOf(this.get(index)) ||
                    this.indexesOfDisabels.get(i) < super.indexOf(this.get(index)))
                index++;

        return super.listIterator(index);
    }


}
