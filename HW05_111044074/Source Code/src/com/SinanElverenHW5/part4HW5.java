package com.SinanElverenHW5;

public class part4HW5 {

    /**
     * inner class insertion sort
     */
    public static class InsertionSort {

        /**
         * Sort the table using insertion sort algorithm.
         * pre:  table contains Comparable objects.
         * post: table is sorted.
         *
         * @param table The array to be sorted
         * @param <T> generic
         */
        public static <T extends Comparable<T>> void sort(T[] table) {

            for (int nextPos = 1; nextPos < table.length; nextPos++) {
                // Invariant: table[0 . . . nextPos - 1] is sorted.
                // Insert element at position nextPos
                // in the sorted subarray.
                insert(table, nextPos);
            } // End for.
        } // End sort.


        /**
         * Insert the element at nextPos where it belongs
         * in the array.
         * pre:  table[0 . . . nextPos - 1] is sorted.
         * post: table[0 . . . nextPos] is sorted.
         *
         * @param table   The array being sorted
         * @param nextPos The position of the element to insert
         * @param <T> generic
         */
        private static <T extends Comparable<T>> void insert(T[] table, int nextPos) {

            T nextVal = table[nextPos]; // Element to insert.
            while (nextPos > 0
                    && nextVal.compareTo(table[nextPos - 1]) < 0) {
                table[nextPos] = table[nextPos - 1]; // Shift down.
                nextPos--; // Check next smaller element.
            }
            // Insert nextVal at nextPos.
            table[nextPos] = nextVal;
        }
    }


    /**
     * Implements the recursive merge sort algorithm. In this version, copies
     * of the subtables are made, sorted, and then merged.
     *
     * @author Koffman and Wolfgang
     */

    public static class MergeSort {

        /**
         * Sort the array using the merge sort algorithm.
         * pre: table contains Comparable objects.
         * post: table is sorted.
         *
         * @param table The array to be sorted
         * @param <T> generic
         */
        public static <T
                extends Comparable<T>> void sort(T[] table) {
            // A table with one element is sorted already.
            if (table.length > 1) {
                // Split table into halves.
                int halfSize = table.length / 2;
                T[] leftTable = (T[]) new Comparable[halfSize];
                T[] rightTable =
                        (T[]) new Comparable[table.length - halfSize];
                System.arraycopy(table, 0, leftTable, 0, halfSize);
                System.arraycopy(table, halfSize, rightTable, 0,
                        table.length - halfSize);

                // Sort the halves.
                sort(leftTable);
                sort(rightTable);

                // Merge the halves.
                merge(table, leftTable, rightTable);
            }
        }


        /**
         * Merge two sequences.
         * pre: leftSequence and rightSequence are sorted.
         * post: outputSequence is the merged result and is sorted.
         *
         * @param outputSequence The destination
         * @param leftSequence   The left input
         * @param rightSequence  The right input
         * @param <T> generic
         */
        private static <T extends Comparable<T>> void merge(T[] outputSequence,
                                                            T[] leftSequence,
                                                            T[] rightSequence) {

            int i = 0; // Index into the left input sequence.
            int j = 0; // Index into the right input sequence.
            int k = 0; // Index into the output sequence.

            // While there is data in both input sequences
            while (i < leftSequence.length && j < rightSequence.length) {
                // Find the smaller and
                // insert it into the output sequence.
                if (leftSequence[i].compareTo(rightSequence[j]) < 0) {
                    outputSequence[k++] = leftSequence[i++];
                } else {
                    outputSequence[k++] = rightSequence[j++];
                }
            }
            // assert: one of the sequences has more items to copy.
            // Copy remaining input from left sequence into the output.
            while (i < leftSequence.length) {
                outputSequence[k++] = leftSequence[i++];
            }
            // Copy remaining input from right sequence into output.
            while (j < rightSequence.length) {
                outputSequence[k++] = rightSequence[j++];
            }
        }


    }






    /** Implements the quicksort algorithm.
     *   @author Koffman and Wolfgang
     * */

    public static class QuickSort {

        /** Sort the table using the quicksort algorithm.
         pre: table contains Comparable objects.
         post: table is sorted.
         @param <T> generic
         @param table The array to be sorted
         */
        public static < T
                extends Comparable < T >> void sort(T[] table) {
            // Sort the whole table.
            quickSort(table, 0, table.length - 1);
        }

        /** Sort a part of the table using the quicksort algorithm.
         post: The part of table from first through last is sorted.
         @param <T> generic
         @param table The array to be sorted
         @param first The index of the low bound
         @param last The index of the high bound
         */
        private static < T
                extends Comparable < T >> void quickSort(T[] table,
                                                         int first,
                                                         int last) {
            if (first < last) { // There is data to be sorted.
                // Partition the table.
                int pivIndex = partition(table, first, last);
                // Sort the left half.
                quickSort(table, first, pivIndex - 1);
                // Sort the right half.
                quickSort(table, pivIndex + 1, last);
            }
        }

        /** Partition the table so that values from first to pivIndex
         are less than or equal to the pivot value, and values from
         pivIndex to last are greater than the pivot value.
         @param table The table to be partitioned
         @param first The index of the low bound
         @param last  The index of the high bound
         @return The location of the pivot value
         @param <T> generic
         */
        private static < T
                extends Comparable < T >> int partition(T[] table,
                                                        int first,
                                                        int last) {
            // Select the first item as the pivot value.
            T pivot = table[first];
            int up = first;
            int down = last;
            do {
      /* Invariant:
         All items in table[first . . . up - 1] <= pivot
         All items in table[down + 1 . . . last] > pivot
       */
                while ( (up < last) && (pivot.compareTo(table[up]) >= 0)) {
                    up++;
                }
                // assert: up equals last or table[up] > pivot.
                while (pivot.compareTo(table[down]) < 0) {
                    down--;
                }
                // assert: down equals first or table[down] <= pivot.
                if (up < down) { // if up is to the left of down.
                    // Exchange table[up] and table[down].
                    swap(table, up, down);
                }
            }
            while (up < down); // Repeat while up is left of down.

            // Exchange table[first] and table[down] thus putting the
            // pivot value where it belongs.
            swap(table, first, down);

            // Return the index of the pivot value.
            return down;
        }

        /** Swap the items in table[i] and table[j].
         @param <T> generic
         @param table The array that contains the items
         @param i The index of one item
         @param j The index of the other item
         */
        private static < T
                extends Comparable < T >> void swap(T[] table,
                                                    int i, int j) {
            T temp = table[i];
            table[i] = table[j];
            table[j] = temp;
        }

    }






    /** Implementation of the heapsort algorithm.
     *  @author Koffman and Wolfgang
     * */

    public static class HeapSort {
        /** Sort the array using heapsort algorithm.
         pre: table contains Comparable items.
         post: table is sorted.
         @param table The array to be sorted
         @param <T> generic
         */
        public static < T
                extends Comparable < T >> void sort(T[] table) {
            buildHeap(table);

            shrinkHeap(table);
        }

        /** buildHeap transforms the table into a heap.
         pre:  The array contains at least one item.
         post: All items in the array are in heap order.
         @param table The array to be transformed into a heap
         @param <T> generic
         */
        private static < T
                extends Comparable < T >> void buildHeap(T[] table) {
            int n = 1;

            // Invariant: table[0 . . . n - 1] is a heap.
            while (n < table.length) {
                n++; // Add a new item to the heap and reheap.
                int child = n - 1;
                int parent = (child - 1) / 2; // Find parent.
                while (parent >= 0
                        && table[parent].compareTo(table[child]) < 0) {
                    swap(table, parent, child);
                    child = parent;
                    parent = (child - 1) / 2;
                }
            }
        }

        /** shrinkHeap transforms a heap into a sorted array.
         pre: All items in the array are in heap order.
         post: The array is sorted.
         @param table The array to be sorted
         @param <T> generic
         */
        private static < T
                extends Comparable < T >> void shrinkHeap(T[] table) {
            int n = table.length;

            // Invariant: table[0 . . . n - 1] forms a heap.
            // table[n . . . table.length - 1] is sorted.
            while (n > 0) {
                n--;
                swap(table, 0, n);
                // table[1 . . . n - 1] form a heap.
                // table[n . . . table.length - 1] is sorted.
                int parent = 0;
                while (true) {
                    int leftChild = 2 * parent + 1;
                    if (leftChild >= n) {
                        break; // No more children.
                    }
                    int rightChild = leftChild + 1;
                    // Find the larger of the two children.
                    int maxChild = leftChild;
                    if (rightChild < n // There is a right child.
                            && table[leftChild].compareTo(table[rightChild]) < 0) {
                        maxChild = rightChild;
                    }
                    // If the parent is smaller than the larger child,
                    if (table[parent].compareTo(table[maxChild]) < 0) {
                        // Swap the parent and child.
                        swap(table, parent, maxChild);
                        // Continue at the child level.
                        parent = maxChild;
                    }
                    else { // Heap property is restored.
                        break; // Exit the loop.
                    }
                }
            }
        }

        /** Swap the items in table[i] and table[j].
         @param table The array that contains the items
         @param i The index of one item
         @param j The index of the other item
         @param <T> generic
         */
        private static < T
                extends Comparable < T >> void swap(T[] table,
                                                    int i, int j) {
            T temp = table[i];
            table[i] = table[j];
            table[j] = temp;
        }
    }



}
