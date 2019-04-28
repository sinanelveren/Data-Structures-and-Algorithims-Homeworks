package com.generalTreeSinanElveren;


import java.io.Serializable;


/**
 * Chapter 6, Section 3 - Source code of Course's book
 * binary tree that stores objects
 * @param <E> generic type
 */
public class BinaryTree<E extends Comparable<? super E>> implements Serializable {

    /**
     * Inner Class of Binary Tree Class
     */
    protected static class Node<E extends Comparable<? super E>> implements Comparable<Node<E>>, Serializable {
        /**
         * value of node
         * data field changed as public for test class
         */
        public E data;
        /**
         * referencing the left child
         */
        protected Node<E> left;
        /**
         * referencing the right node
         */
        protected Node<E> right;

        /**
         * One parameter constructor
         * @param data value for add to node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        /**
         * No paramter constructor
         * @return  data to store in this node
         */
        public String toString() {
            return data.toString();
        }

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
         * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
         * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
         * <tt>y.compareTo(x)</tt> throws an exception.)
         * <p>The implementor must also ensure that the relation is transitive:
         * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
         * <tt>x.compareTo(z)&gt;0</tt>.
         * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
         * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
         * all <tt>z</tt>.
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
         * class that implements the <tt>Comparable</tt> interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         * <p>In the foregoing description, the notation
         * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
         * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
         * <tt>0</tt>, or <tt>1</tt> according to whether the value of
         * <i>expression</i> is negative, zero or positive.
         *
         * @param node the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(Node<E> node) {
            return this.data.compareTo(node.data);
        }
    }

    /**
     * Node that root of tree
     */
    protected Node<E> root;


    /**
     * No parameter constructor
     */
    public BinaryTree(){
        root = null;
    }

    /**
     * One parameter constructor
     * @param root parent value
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }


    /**
     * Three parameter constructor
     * @param data parent data value
     * @param leftTree  left child value
     * @param rightTree right child value
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }


    /**
     * Getter for subtree
     * @return root tree of left sub
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        }
        else {
            return null;
        }
    }

    /**
     * Getter for right subtree
     * @return root tree of right child or null if there are no child.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }


    /**
     * Getter for root's data
     * @return value of root's data or null if there are no child.
     */
    public E getData(){
        if(root != null)
            return this.root.data;
        else
            return null;
    }

    /**
     * Check if it's leaf empty or not
     * @return true if left tree is null
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }


    /**
     * Pre order traverse way
     * @param node a parent node
     * @param depth depth of
     * @param sb string builder for output
     */
    protected void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" "); // indentation
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Post order traverse way
     * @param node parent root
     * @param depth depth of
     * @param sb string builder for output
     */
    protected void postOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            //sb.append("  ");
        }
        if (node == null) {
            // sb.append("null\n");
        } else {

            postOrderTraverse(node.left, depth + 1, sb);
            postOrderTraverse(node.right, depth + 1, sb);
            sb.append(node.toString());
            sb.append("\n");


        }
    }

    /**
     * In order traverse way
     * @param node parent root
     * @param depth depth of
     * @param sb string builder for output
     */
    private void inOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            //sb.append("  ");
        }
        if (node == null) {
            // sb.append("null\n");
        } else {

            inOrderTraverse(node.left, depth + 1, sb);

            sb.append(node.toString());
            sb.append("\n");

            inOrderTraverse(node.right, depth + 1, sb);
        }
    }


    /**
     * to string method
     * @return string of data
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }



}
