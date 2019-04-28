/*
 * HW 5 Part 2 implementation
 * {@link com.SinanElverenHW4.part2HW4.RecursiveHashingSet}
 */
package com.SinanElverenHW5.part2HW5;




import java.util.*;


/**
 * Recursive Hashing Set,by using a chaining hash table.
 * @param <E> Generic type value
 * @author Sinan Elveren - Gebze Technical University
 * @since <pre>May 2, 2018</pre>
 */
public class RecursiveHashingSet<E> implements Set {



    private Node<E>[] table;


    //private final Node<E> DELETED = new Node<E>(table);
    private static final int START_CAPACITY = 7;
    private static final double LOAD_THRESHOLD = 3.0;

    private double threshold;
    private int capacity;
    private int numElements;
    private int numDeletes;


    /**
     * Node class for table's each index
     * @param <E> data value for each tables's index
     */
    class Node<E> {
        protected boolean isDeleted;
        protected E data;
        protected Node<E> first;
        protected Node<E> nextNode;
        protected Node<E>[] nextTable;

        protected Node(Node<E> [] tab, E data) {
            this.data = data;
            this.first = tab[0];
            this.nextNode = tab[1];
            this.nextTable = null;

            isDeleted = false;
        }


        /**
         * toggle isDeleted
         * @param deleted true/false value
         */
        private void makeDeleted(boolean deleted) {
            this.data = null;
            this.isDeleted = deleted;
        }


        /**
         * Retrieves the value.
         * @return The value
         */
        public E getData() {
            return data;
        }

        /**
         * Sets the value.
         * @param dat The new value
         * @return The old value
         */
        public E setData(E dat) {
            E oldData = data;
            data = dat;
            return oldData;
        }

        @Override
        public String toString() {
            return "[" + String.valueOf(data) + "] ";
        }
    }


    /**
     * One parameter constructor via default capacity and default threshold
     */
    public RecursiveHashingSet(){
        this(START_CAPACITY, LOAD_THRESHOLD);
    }


    /**
     * One parameter constructor via default threshold
     * @param capacity capacity calue
     */
    public RecursiveHashingSet(int capacity){
        this(capacity, LOAD_THRESHOLD);
    }

    /**
     * Two parameter constructor
     * @param capacity capacity value
     * @param threshold threshold value
     */
    public RecursiveHashingSet(int capacity, double threshold) {
        if (capacity <= 0)
            throw new IllegalArgumentException(String.valueOf(capacity));

        if (threshold <= 1)
            throw new IllegalArgumentException(String.valueOf(threshold));


        this.threshold = threshold;
        this.capacity = capacity;

        this.numElements = 0;

        table = new Node[this.capacity];
    }


    /**
     * Get table's sub array
     * @param table node array for indexes
     * @return next index as node
     */
    public Node<E>[]  nextNode(Node<E>[] table) {
        if(table.length <= 1)
            return null;

        if(table != null){
            Node<E> tab[];

            tab = new Node[table.length-1];
            for (int i = 1; i < table.length ; i++) {
                tab[i-1] = table[i];
            }
            return tab;
        }
        else
            return null;

    }


    /**
     * getter for table array
     * @return table array as node array
     */
    public Node<E>[] getTable() {

        return table;
    }


    /**
     * Generate prime number smaller then table size, if the length smaller then lenth's 1/3 so multiple it as 4x
     * @return prime number smaller then table size
     * @param maxLenth max lengtth
     */
    private int getPrime(int maxLenth) {
        if(maxLenth <= table.length/3)          //if smaller then first table size 's 1/4
            return getPrime(maxLenth*4+1);

        boolean flag = true;

        for (int i = maxLenth; i > 2 ; i -= 2) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    j = i;
                    flag = false;
                }
            }
            if(flag)
                return i;
            flag = true;

        }
        return 2;
    }


    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this set (its cardinality)
     */
    @Override
    public int size() {

        return this.numElements;
    }


    /**
     * Returns <tt>true</tt> if this set contains no elements.
     *
     * @return <tt>true</tt> if this set contains no elements
     */
    @Override
    public boolean isEmpty() {

        return numElements == 0;
    }


    /**
     * Returns <tt>true</tt> if this set contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this set
     * contains an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param element element whose presence in this set is to be tested
     * @return <tt>true</tt> if this set contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this set
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              set does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object element) {
        Object[] obj = this.toArray();

        for (int i = 0; i < obj.length; i++) {
            if(obj[i].equals(element))
                return true;
        }
        return false;
    }


    /**
     * Returns an iterator over the elements in this set.  The elements are
     * returned in no particular order (unless this set is an instance of some
     * class that provides a guarantee).
     *
     * @return an iterator over the elements in this set
     */
    @Override
    public Iterator iterator() {
        Node<E> [] nextNode = this.nextNode(this.table);
        Node<E> [] node = this.table;
        Node<E> [] nextTable = node[0].nextTable;

        Iterator iterator = new Iterator() {
            @Override
            public boolean hasNext() {
                if (nextNode(node) != null)
                    return true;
                else
                    return false;
            }


            @Override
            public Object next() {
                Object obj = nextNode(node);
                return obj;
            }


            /**
             * Get table's sub array
             * @param table node array for indexes
             * @return next index as node
             */
            public Node<E>[]  nextNode(Node<E>[] table) {
                if(table.length <= 1)
                    return null;

                if(table != null){
                    Node<E> tab[];

                    tab = new Node[table.length-1];
                    for (int i = 1; i < table.length ; i++) {
                        tab[i-1] = table[i];
                    }
                    return tab;
                }
                else
                    return null;

            }
        };

        return null;
    }


    /**
     * Returns an array containing all of the elements in this set.
     * If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the
     * elements in the same order.
     * <p>The returned array will be "safe" in that no references to it
     * are maintained by this set.  (In other words, this method must
     * allocate a new array even if this set is backed by an array).
     * The caller is thus free to modify the returned array.
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all the elements in this set
     */
    @Override
    public Object[] toArray() {
        List<E> array = new ArrayList<>();

        Object[] objArr = helperToArray(array, this.table).toArray();

        return objArr;
    }


    private List<E> helperToArray(List<E> array, Node<E>[] table) {

        if (table != null) {
            if (table[0] != null) {
                if (table[0].getData() != null) {
                    array.add(table[0].getData());

                    if (table[0].nextTable != null)         //go to next table
                        helperToArray(array, table[0].nextTable);
                }

                if (nextNode(table) != null)        //next index in the table
                     helperToArray(array, nextNode(table));

                return array;
            }
            //go to next index
            helperToArray(array, nextNode(table));
        }
        return array;
    }


    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).  More formally, adds the specified element
     * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
     * such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns <tt>false</tt>.  In combination with the
     * restriction on constructors, this ensures that sets never contain
     * duplicate elements.
     * <p>The stipulation above does not imply that sets must accept all
     * elements; sets may refuse to add any particular element, including
     * <tt>null</tt>, and throw an exception, as described in the
     * specification for {@link Collection#add Collection.add}.
     * Individual set implementations should clearly document any
     * restrictions on the elements that they may contain.
     *
     * @param element element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     * element
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this set
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this set
     * @throws NullPointerException          if the specified element is null and this
     *                                       set does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified element
     *                                       prevents it from being added to this set
     */
    @Override
    public boolean add(Object element) {
        if (element == null)
            throw new IllegalArgumentException("Null argument : null");

        if(this.contains(element))
            return false;

        return add(this.table, element);
    }

    private boolean add(Node<E>[] table, Object element){
        int index = find(element, table);


        if(table[index] == null){
            table[index] = new Node<E>(this.table, (E) element);
            numElements++;

            //re size if the table reach load factor
            double loadFactor = (double) (numElements + numDeletes) / table.length;
            if ((numElements + numDeletes) > this.threshold * table.length) {
                System.out.println("\n\nRESIZE \n\n");
                reSize();
            }
            return true;
        }
        else{
            if((table[index].getData()== null && table[index].isDeleted==true) || !(table[index].getData().equals(element))){
                //go next table
                if(table[index].nextTable == null)
                    table[index].nextTable = new Node[getPrime(table.length-2)];

                return add(table[index].nextTable, element);
            }
            else
                return false;                           //element is already existed

        }
    }


    /**
     * Expand the capacity if the table element's number is reached to load factor
     */
    private void reSize() {

        // Save a reference to oldTable.
        Object[] obj = this.toArray();

        // Double capacity of this table.
        this.updateCapacity();


        table = new Node[this.capacity];

        // Reinsert all items in oldTable into expanded table.
        numDeletes = 0;
        numElements = 0;

        for (int i = 0; i < obj.length; i++) {
                // Insert entry in expanded table
                add((E)obj[i]);
        }
    }

    /**
     * Increasing capacity as 2x
     */
    private void updateCapacity(){
        this.capacity = this.capacity * 2 + 1;
    }


    /**
     * Find index of element
     * @param element data
     * @return index
     * @param tab table
     */
    private int find(Object element, Node<E>[] tab) {
        // Calculate the starting index.
        int index = element.hashCode() % tab.length;
        if (index < 0) {
            index += tab.length; // Make it positive.
        }

        return index;
    }



    /**
     * Removes the specified element from this set if it is present
     * (optional operation).  More formally, removes an element <tt>e</tt>
     * such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     * this set contains such an element.  Returns <tt>true</tt> if this set
     * contained the element (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     *
     * @param element object to be removed from this set, if present
     * @return <tt>true</tt> if this set contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this set
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       set does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this set
     */
    @Override
    public boolean remove(Object element) {

        if (element == null)
            throw new IllegalArgumentException("Null argument : null");

        return remove(this.table, element);
    }

    private boolean remove(Node<E>[] table, Object element) {

        int index = find(element, table);


        if (table[index] == null) {
            return false;
        } else {
            if ((table[index].getData()!= null && table[index].isDeleted==false) && element.equals(table[index].getData())) {
                table[index].makeDeleted(true);                            //element is found then delete it
                this.numElements--;
                this.numDeletes++;
                return true;
            } else {
                //go next table for find the data
                if (table[index].nextTable == null)
                    return false;                                          //there are no table in the next table
                else
                    return remove(table[index].nextTable, element);         //call recursive in to next table.
            }
        }

    }

    /**
         * Adds all of the elements in the specified collection to this set if
         * they're not already present (optional operation).  If the specified
         * collection is also a set, the <tt>addAll</tt> operation effectively
         * modifies this set so that its value is the <i>union</i> of the two
         * sets.  The behavior of this operation is undefined if the specified
         * collection is modified while the operation is in progress.
         *
         * @param c collection containing elements to be added to this set
         * @return <tt>true</tt> if this set changed as a result of the call
         * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
         *                                       is not supported by this set
         * @throws ClassCastException            if the class of an element of the
         *                                       specified collection prevents it from being added to this set
         * @throws NullPointerException          if the specified collection contains one
         *                                       or more null elements and this set does not permit null
         *                                       elements, or if the specified collection is null
         * @throws IllegalArgumentException      if some property of an element of the
         *                                       specified collection prevents it from being added to this set
         * @see #add(Object)
         */
    @Override
    public boolean addAll(Collection c) {
        if(c == null)
            return false;
        int savedSize = this.numElements;


        ArrayList<E>  arrayList = (ArrayList<E>) c;

        for (int i = 0; i < arrayList.size(); i++) {
          this.add(arrayList.get(i));
        }

        if(this.numElements!= savedSize)
            return true;
        else
            return false;
    }


    /**
     * Removes all of the elements from this set (optional operation).
     * The set will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> method
     *                                       is not supported by this set
     */
    @Override
    public void clear() {
        table = new Node[this.capacity];
        this.numElements = 0;
        this.numDeletes = 0;
    }



    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a set, this operation effectively modifies this
     * set so that its value is the <i>asymmetric set difference</i> of
     * the two sets.
     *
     * @param c collection containing elements to be removed from this set
     * @return <tt>true</tt> if this set changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
     *                                       is not supported by this set
     * @throws ClassCastException            if the class of an element of this set
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this set contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection c) {

        if(c == null)
            return false;

        int savedSize = this.numElements;


        ArrayList<E>  arrayList = (ArrayList<E>) c;

        for (int i = 0; i < arrayList.size(); i++) {
            this.remove(arrayList.get(i));
        }

        if(this.numElements!= savedSize)
            return true;
        else
            return false;

    }


    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this set all of its elements that are not contained in the
     * specified collection.  If the specified collection is also a set, this
     * operation effectively modifies this set so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param c collection containing elements to be retained in this set
     * @return <tt>true</tt> if this set changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *                                       is not supported by this set
     * @throws ClassCastException            if the class of an element of this set
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this set contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     */
    @Override
    public boolean retainAll(Collection c) {
        if(c == null)
            return false;



        ArrayList<E>  arrayList = (ArrayList<E>) c;
        List<E>  intersectionList = new ArrayList<>();



        for (int i = 0; i < arrayList.size(); i++) {
            if(this.contains(arrayList.get(i)))
                intersectionList.add(arrayList.get(i));
        }

        if(intersectionList.size() > 0) { //changed old size
            this.clear();
            for (int i = 0; i < intersectionList.size(); i++) {
                this.add(intersectionList.get(i));
            }

            return true;
        }else
            return false;
    }


    /**
     * Returns <tt>true</tt> if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns <tt>true</tt> if it is a <i>subset</i> of this set.
     *
     * @param c collection to be checked for containment in this set
     * @return <tt>true</tt> if this set contains all of the elements of the
     * specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              set
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this set does not permit null
     *                              elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection c) {

        if(c == null)
            return false;


        ArrayList<E>  arrayList = (ArrayList<E>) c;

        for (int i = 0; i < arrayList.size(); i++) {
            if( !(this.contains(arrayList.get(i))) )
                return false;
        }

        return true;
    }


    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this set.
     * <p>If this set fits in the specified array with room to spare
     * (i.e., the array has more elements than this set), the element in
     * the array immediately following the end of the set is set to
     * <tt>null</tt>.  (This is useful in determining the length of this
     * set <i>only</i> if the caller knows that this set does not contain
     * any null elements.)
     * <p>If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements
     * in the same order.
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     * <p>Suppose <tt>x</tt> is a set known to contain only strings.
     * The following code can be used to dump the set into a newly allocated
     * array of <tt>String</tt>:
     *     String[] y = x.toArray(new String[0]);
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this set are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * @return an array containing all the elements in this set
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in this
     *                              set
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public Object[] toArray(Object[] a) {
        List<E> array = new ArrayList<>();


        for (int i = 0; i < a.length ; i++) {
            if(a[i] != null)
                 array.add((E) a[i]);
        }

        Object[] objArr = helperToArray(array, this.table).toArray();

        return objArr;
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }


    /**
     * traverse table for toString
     * Print the table
     * @param table  a parent node
     * @param depth depth of
     * @param sb    string builder for output
     */
    private void traverseTable(Node<E> table[], int depth, StringBuilder sb) {
        if(depth == 1)
            sb.append((this.capacity-table.length) + ":\t");

        for (int i = 1; i < depth; i++) {
            sb.append("\t\t|"); // indentation
        }

        if( table != null) {
            if (table[0] == null) {
                sb.append("null\n");
            } else {
                if(table[0].isDeleted == true)
                    sb.append("[DELETED]");
                else
                    sb.append(table[0].toString());
                sb.append("\n");
                if(table[0].nextTable != null)
                    traverseTable(table[0].nextTable, depth + 1, sb);

             // if(nextNode(table) != null)
                 //   traverseTable(nextNode(table), depth , sb);
            }
            if(nextNode(table) != null)
                traverseTable(nextNode(table), depth, sb);

        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        traverseTable(this.table, 1, sb);

        return "\n    >Recursive Hashing Set<\n"+ sb.toString();
    }
}
