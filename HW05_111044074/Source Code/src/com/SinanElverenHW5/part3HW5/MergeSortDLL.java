package com.SinanElverenHW5.part3HW5;


import java.util.ListIterator;
import java.util.NoSuchElementException;

/** Implements the recursive merge sort algorithm. In this version, copies
 *   of the subtables are made, sorted, and then merged.
 *   source : geeksforgeeks.org
 *   @author Koffman and Wolfgang and Sinan Elveren
 */
public class MergeSortDLL < E extends Comparable < E >> {

    public static Node root;  // head of list
    public static Node head;  // head of list
    public static Node tail;  // head of list


    /**
     * Node class inner
     */
    public static class Node {

        int data;
        Node next, prev;

        // Constructor to create a new node
        public Node(int obj) {
            if(head == null){
                this.data = obj;
                head = this;
                tail = head;

                root = head;
                next = prev = null;
            }
            else{   //insert to tail
                this.prev = tail;
                this.data = obj;
                this.next = null;

                // Link the tail to the new node.
                tail.next = this; // Step 1

                tail = this; // Step 3
            }
        }
    }


    /**
     *
     * @param node print this node
     */
    public void print(Node node) {
        Node temp = node;
        System.out.println("Forward Traversal using next pointer");
        while (node != null) {
            System.out.print(node.data + " ");
            temp = node;
            node = node.next;
        }
        System.out.println("\nBackward Traversal using prev pointer");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.prev;
        }
    }



    /**
     * Split a doubly linked list (DLL) into 2 DLLs of half sizes
     * @param head head of list
     * @return node
     */
    protected Node split(Node head) {
        Node fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node temp = slow.next;
        slow.next = null;
        return temp;
    }


    /**
     * mergesort on given node
     * @param node node
     * @return node
     */
    public Node mergeSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node second = split(node);

        // Recur for left and right halves
        node = mergeSort(node);
        second = mergeSort(second);

        // Merge the two sorted halves
        return merge(node, second);
    }


    /**
     * Function to merge two linked lists
     * @param first first part of list
     * @param second second part of list
     * @return small one
     */
    protected Node merge(Node first, Node second) {
        // If first linked list is empty
        if (first == null) {
            return second;
        }

        // If second linked list is empty
        if (second == null) {
            return first;
        }

        // Pick the smaller value
        if ( first.data < second.data ) {
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }
}
