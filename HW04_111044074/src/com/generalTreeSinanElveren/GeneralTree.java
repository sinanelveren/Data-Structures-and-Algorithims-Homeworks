package com.generalTreeSinanElveren;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sinan Elveren - Gebze Technical University
 * @since <pre>Apr 16, 2018</pre>
 * GeneralTree Class that implement binary tree representation of general tree
 * @param <E> Generic type
 */
public class GeneralTree<E extends Comparable<? super E>> extends BinaryTree {
    /**
     * Check there are an addition
     */
    protected boolean addReturn;
    /**
     * Use it for level order search, shows level of tree
     */
    private int level;
    /**
     * Store tree as level by level
     */
    protected Map<Integer, ArrayList<Node<E>>> map = new HashMap<>();


    public Node<E> searchReturn;

    public boolean testLevelOrder =false;       //use it levelorder test method. make it true.

    /**
     * No parameter constructor that call super
     */
    public GeneralTree() {
        super();
        addReturn = false;
        searchReturn = null;
    }

    /**
     * One parameter constructor
     * @param root Node that root node
     */
    public GeneralTree(Node<E> root) {
        super(root);
        addReturn = true;
        searchReturn = null;
    }

    /**
     * Three parameter constructor
     *
     * @param data      parent data value
     * @param leftTree  left child value
     * @param rightTree right child value that sibling
     * pre: first parameter must be comparable value.
     */
    public GeneralTree(Comparable data, BinaryTree leftTree, BinaryTree rightTree) {
        super(data, leftTree, rightTree);
        this.addReturn = true;
        searchReturn = null;
    }


    /**
     * Starter method add.
     * pre:  The object to insert must implement the
     * Comparable interface.
     *
     * @param parentItem The object being inserted
     * @param childItem  The object being inserted
     * @return true if the object is inserted, false
     * if the object already exists in the tree
     */
    public boolean add(E parentItem, E childItem) throws IllegalArgumentException {
        if (root == null) {
            root = new Node(parentItem);
            root.left = new Node(childItem);
            this.searchReturn = root;
            return true;
        }
        System.out.println("\nSearch in tree (Parent & Child) " + parentItem + "&" + childItem);



        //Post Order Search is here.
        if(this.testLevelOrder == false) {
            if(postOrderSearch(this.root, childItem) != null) {
                //child item is alread exist in tree
                throw new IllegalArgumentException(childItem.toString());
            }
            this.searchReturn = postOrderSearch(this.root, parentItem);
        }
        /* *
         * Level Order Search is here, check it.
         */
        //
        if(this.testLevelOrder == true) {
           /*check for add uniq element
            if(levelOrderSearch(this.root, childItem) != null) {
                //child item is alread exist in tree
                throw new IllegalArgumentException(childItem.toString());
            }*/
            this.searchReturn = levelOrderSearch(this.root, parentItem);
        }
        System.out.println(searchReturn + "found");
        if (searchReturn == null)
            return false;
        if (searchReturn.left == null) {
            searchReturn.left = new Node<E>(childItem);
            return true;
        }

        return add(searchReturn.left, childItem);
    }

    /**
     * Helper methot for add method
     * @param node sibling node
     * @param childItem sibling
     * @return true if adding is success, else false
     */
    private boolean add(Node<E> node, E childItem)throws IllegalArgumentException {

        if (node.right == null) {
            node.right = new Node<E>(childItem);
            return true;
        } else
            add(node.right, childItem);

        return false;
    }

    /**
     * Post order traverse way
     * Search item in tree
     * @param node   parent root
     * @param target parent data to search
     * @return node where find the target, and null if target not found
     */
    protected Node<E> postOrderSearch(Node<E> node, E target) {
        Node<E> localNode = new Node<E>(null);
        localNode = null;

        if (node == null)
            return null;

        if (node.left != null) {
            localNode = postOrderSearch(node.left, target);

            if (localNode != null && localNode.data.compareTo(target) == 0)
                return localNode;
        }

        //last node
        System.out.print("(" + node.data + ")  ");             //test
        if (node.data.compareTo(target) == 0)
            return node;
        if (node.right != null)
            localNode = postOrderSearch(node.right, target);

        return localNode;
    }


    /**
     * Level order search
     * @param node   parent root
     * @param target parent data to search
     * @return node where find the target, and null if target not found
     */
    protected Node<E> levelOrderSearch(Node<E> node, E target) {
        ArrayList<Node<E>> list = new ArrayList<>();
        map.clear();

        //set level as zero for new search
        level = 0;
        //fill map-array level by level
        helperLevelOrder(node, map);

        // System.out.println(map);                                 //test
        //search target as level order
        for (int i = 0; i < map.size(); i++) {
            list = (ArrayList<Node<E>>) map.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print("[" + list.get(j).data +"]  ");             //test
                if (list.get(j).data.compareTo(target) == 0)
                    return list.get(j);     //node which found
            }
        }
        return null;
    }

    /**
     * Helper methot for level order search. this methot will fill arraylist(map) as level by level
     * @param node Root node
     * @param map That will keep arraylist of nodes and them level
     * @return next node for recursive method
     */
    private Node<E> helperLevelOrder(Node<E> node, Map<Integer, ArrayList<Node<E>>> map) {
        Node<E> localNode = new Node<E>(null);
        localNode = null;

        if (node == null) {
            return null;
        }

        //fill array*map as level by level
        Object obj = map.get(level);
        map.remove(level);
        ArrayList<Node<E>> list;
        if (obj != null) {
            list = (ArrayList<Node<E>>) obj;
        } else {
            list = new ArrayList<Node<E>>();
        }
        list.add(node);
        map.put(level, list);


        //System.out.println(map);  //check map how is filled - test

        if (node.right != null) {
            localNode = helperLevelOrder(node.right, map);

        }

        if (node.left != null) {
            ++level;
            localNode = helperLevelOrder(node.left, map);
            --level;
        }

        return localNode;
    }


    /**
     * Pre order traverse way - override
     * Print the tree as general tree
     * @param node  a parent node
     * @param depth depth of
     * @param sb    string builder for output
     */
    @Override
    protected void preOrderTraverse(Node node, int depth, StringBuilder sb) {
        //super.preOrderTraverse(node, depth, sb);
        Node<E> localNode = new Node<E>(null);
        localNode = null;


        if (node != null) {
            for (int i = 1; i < depth; i++) {
                sb.append("   |"); // indentation
            }

            sb.append(node.toString());
            sb.append("\n");

            helperPre(node.left, depth +1, sb);
            preOrderTraverse(node.right, depth  , sb);
        }
    }


    /**
     * helper methot for pre order traverse
     * call this at each left root
     * @param node left node mean child node
     * @param depth depth for indention
     * @param sb String builder for print the tree
     */
    protected void helperPre(Node<E> node, int depth, StringBuilder sb) {
        Node<E> localNode = new Node<E>(null);
        localNode = null;

        if (node != null){

            for (int i = 1; i < depth; i++) {
                sb.append("   |"); // indentation
            }


            sb.append(node.toString());
            sb.append("\n");

            if(node.left != null)
                preOrderTraverse(node.left, depth+1, sb);   //call if this node have children
            preOrderTraverse(node.right, depth, sb);              //call if this node have sibling
        }

    }

    /**
     * Pre order traverse way - for binary representation
     * Print the tree as binary tree
     * @param node  a parent node
     * @param depth depth of
     * @param sb    string builder for output
     */
    private void preOrderTraverseBinary(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("   |"); // indentation
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverseBinary(node.left, depth + 1, sb);
            preOrderTraverseBinary(node.right, depth + 1, sb);
        }
    }


    /**
     * for test
     * @return searchReturn
     */
    public Node<E> getSearchReturn() {
        return searchReturn;
    }

    /**
     * to string method for general tree represantation
     * @return string of tree
     */
    @Override
    public String toString() {

        return "\n    >General Tree<\n" + super.toString();
    }

    /**
     * to string methot for binary represantation
     * @return string of tree
     */
    public String toStringBinaryTree() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverseBinary(root, 1, sb);

        return "\n    >Binary Tree<\n"+ sb.toString();
    }
}

