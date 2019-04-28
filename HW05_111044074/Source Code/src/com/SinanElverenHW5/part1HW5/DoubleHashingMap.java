/*
 * HW 5 part 1 implementation,
 * {@link com.SinanElverenHW4.part1HW4.DoubleHashingMap}
 */
package com.SinanElverenHW5.part1HW5;


import java.util.*;
import java.util.Iterator;
import java.util.Set;


/**
 * Double Hashing map class by using an open addressing hash table,
 * Collisions are resolved by double hashing.
 * @param <K> Key for map
 * @param <V> Value for map which have key
 * @author Sinan Elveren - Gebze Technical University
 * @since <pre>May 2, 2018</pre>
 */
@SuppressWarnings("unchecked")
public class DoubleHashingMap<K, V>  implements Map<K, V> {

    // Data Fields
    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 11;
    private static final double LOAD_THRESHOLD = 0.75;
    private final Entry<K, V> DELETED = new Entry<K, V>(null, null);

    private double threshold;
    private int capacity;
    private int numKeys;
    private int numDeletes;


    // Constructor via default threshold and default capacity
    public DoubleHashingMap() {
        this(START_CAPACITY, LOAD_THRESHOLD);
    }


    /**
     * One parameter constructor via default threshold value
     * @param capacity capacity value
     */
    public DoubleHashingMap(int capacity)throws IllegalArgumentException {
        this(capacity, LOAD_THRESHOLD);
    }

    // Two parameter constructor
    public DoubleHashingMap(int capacity, double threshold) throws IllegalArgumentException{
        if (capacity <= 0)
            throw new IllegalArgumentException(String.valueOf(capacity));

        if (threshold <= 0 || threshold >=1)
            throw new IllegalArgumentException(String.valueOf(threshold));

        this.threshold = threshold;
        this.capacity = capacity;
        this.numKeys = 0;
        this.numDeletes = 0;

        table = new Entry[this.capacity];
    }

    /*<listing chapter="7" number="3">*/
    /** Contains key-value pairs for a hash table. */
    public static class Entry<K, V> implements Map.Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;



        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        // Insert solution to programming exercise 3, section 4, chapter 7 here
    }



    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return this.numKeys;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }


    /**
     * Getter for capacity
     * @return capacity value of table
     */
    public int getCapacity() {
        return this.capacity;
    }


    /**
     * Increasing capacity as 2x
     */
    private void updateCapacity(){
        this.capacity = this.capacity * 2 + 1;
    }


    /*<listing chapter="7" number="4">*/
    /**
     * Finds either the target key or the first empty slot in the
     * search chain using linear probing.
     * pre: The table is not full.
     * @param key The key of the target object
     * @return The position of the target or the first empty slot if
     *         the target is not in the table.
     */
    private int find(Object key) {
        // Insert solution to programming exercise 6, section 4, chapter 7 here
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length; // Make it positive.
        }



        if (table[index] != null && (!key.equals(table[index].key))){
            int oldIndex = index;
            index =  getPrime() - ( key.hashCode()  %  getPrime() );
            index = (index + oldIndex) % table.length;

        }


        // Increment index until an empty slot is reached
        // or the key is found.
        while ((table[index] != null) && (!key.equals(table[index].key))) {
            // Insert solution to programming exercise 6, section 4, chapter 7 here
            index++;
            // Check for wraparound.
            if (index >= table.length) {
                index = 0; // Wrap around.
            }
        }
        return index;
    }


    /**
     * Generate prime number smaller then table size source: sanfoundry.com
     * @return prime number smaller then table size
     */
    private int getPrime() {

        for (int i = this.table.length - 1; i >= 1; i--) {
            int fact = 0;
            for (int j = 2; j < (int) Math.sqrt(i); j++) {
                if(i % j == 0)
                    fact++;
            }
            if (fact == 0)
                return i;
        }
        return this.table.length - 2;
    }



    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsKey(Object key)throws RuntimeException {
        if (key == null)
            throw new IllegalArgumentException(String.valueOf(key));

        int hash = key.hashCode();
        int index = find(key);

        if (table[index] != null) {
            if (table[index].key.hashCode() == hash && table[index].key.equals(key))
                return true;
            else
                return false;
        } else
            return false;
        /*

        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length;


        if (index < 0) {
            index += table.length; // Make it positive.
        }

        while ((table[index] != null)) {
            if (table[index].key.hashCode() == hash && table[index].key.equals(key))
                return true;        //found
            index++;
            // Check for wraparound.
            if (index >= table.length) {
                index = 0; // Wrap around.
            }
        }
*/

    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     * specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsValue(Object value) throws RuntimeException{
        if (value == null) {
            throw new IllegalArgumentException("Null argument : value");
        }

        for (int i = 0; i < table.length; i++) {
            if ((table[i] != null) && (table[i] != DELETED)) {
                if(value.equals(table[i].getValue()))
                    return true;
            }
        }
        return false;
    }




    /*<listing chapter="7" number="5">*/
    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V get(Object key) throws RuntimeException{
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If the search is successful, return the value.
        if (table[index] != null) {
            return table[index].value;
        } else {
            return null; // key not found.
        }
    }


    /*<listing chapter="7" number="6">*/
    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>,
     * if the implementation supports <tt>null</tt> values.)
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     */
    @Override
    public V put(K key, V value) throws RuntimeException {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry<K, V>(key, value);
            numKeys++;
            // Check whether rehash is needed.
            double loadFactor = (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > this.threshold) {
                rehash();
            }
            return null;
        }

        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].value;
        table[index].value = value;
        return oldVal;
    }



    /*<listing chapter="7" number="7">*/
    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD
     * The size of table is doubled and is an odd integer.
     *       Each nondeleted entry from the original table is
     *       reinserted into the expanded table.
     *       The value of numKeys is reset to the number of items
     *       actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        // Double capacity of this table.
        this.updateCapacity();
        table = new Entry[this.getCapacity()];

        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
                // Insert entry in expanded table
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }


    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     * <p>If this map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to <tt>null</tt>.
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the key is of an inappropriate type for
     *                                       this map
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key is null and this
     *                                       map does not permit null keys
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V remove(Object key) throws RuntimeException{
        if (key == null)
            throw new IllegalArgumentException("Null argument : key");


        int index = find(key);

        if(table[index] != null){
            V oldValue = table[index].value;
            numKeys--;
            numDeletes++;

            table[index] = DELETED;
            return oldValue;
        }else
            return null;
    }



    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object, Object) put(k, v)} on this map once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of a key or value in the
     *                                       specified map prevents it from being stored in this map
     * @throws NullPointerException          if the specified map is null, or if
     *                                       this map does not permit null keys or values, and the
     *                                       specified map contains null keys or values
     * @throws IllegalArgumentException      if some property of a key or value in
     *                                       the specified map prevents it from being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) throws RuntimeException {
        Set<? extends K> setMapKeys =  m.keySet();
        Iterator it = this.keySet().iterator();

        DoubleHashingMap map = (DoubleHashingMap) m;

        m.keySet().forEach((k)-> {
            put(k, m.get(k));
        });
    }


    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this map
     */
    @Override
    public void clear() throws RuntimeException {
        for (int i = 0; i < this.getCapacity(); i++) {
            table[i] =  null;
        }
        this.numKeys = 0;
        this.numDeletes = 0;
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();

        for (int i=0; i<table.length; i++) {
            if (table[i] != null && table[i] != DELETED)
                keySet.add(table[i].getKey());
        }

        return keySet;

    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        Collection<V> valueCollection= new ArrayList<>();

        for (int i=0; i<table.length; i++) {
            if (table[i] != null && table[i] != DELETED)
                valueCollection.add(table[i].getValue());
        }

        return valueCollection;
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        ArrayList al = new ArrayList();
        for (int i=0; i<table.length; i++) {
            if(table[i] != null && table[i] != DELETED)
                al.add(new Entry<K, V>(table[i].getKey(), table[i].getValue()));
        }

        return new EntrySet(al);
    }

    /** Inner class to implement the set view. */
    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        List list;
        EntrySet(ArrayList al) {
            list = al;
        }
        /** Return an iterator over the set. */
        @Override
        public Iterator iterator() {
            return list.iterator();
        }

        /** Return the size of the set. */
        @Override
        public int size() {
            return numKeys;
        }

    /*    @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();// test
        }*/
    }


    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("MAP : \t");

        for (int i = 0; i < table.length; i++) {
            if (i%5 == 0)
                sb.append("\n");

            if ( (table[i] != null ) && (table[i] != DELETED) ){
                sb.append(i+": [");
                sb.append(table[i].getKey().toString());
                sb.append(" -> ");
                sb.append(table[i].getValue().toString());
                sb.append("] \t\t");
            }
            if(table[i] == DELETED){
                sb.append(i+": [" + i + " -> DELETED] \t");
            }
            if(table[i] == null){
                sb.append(i+": [" + i + " ->  NULL ] \t\t");
            }

        }

        return "\n" + String.valueOf(sb) + "\n";
    }
}
