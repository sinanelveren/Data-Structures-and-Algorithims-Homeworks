package com.SinanElverenHW6.AVLTreeFromBinary;


import java.io.*;


public class MainQ3 {


    public static void main(String args[]) {

        AVLTree<Integer> avlTree = new AVLTree<>();


        avlTree.add(5);
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(30);
        avlTree.add(40);
        avlTree.add(100);
        avlTree.add(70);
        avlTree.add(6);
        avlTree.add(7);
        avlTree.add(8);
        avlTree.add(15);
        System.out.println("\n\nRed&Black Tree\n______________add TEST\n");
        System.out.println(avlTree.toString());

        avlTree.delete(30);
        avlTree.delete(15);
        avlTree.delete(8);


        System.out.println("\n\nRed&Black Tree\n______________delete TEST\n");
        System.out.println(avlTree.toString());


        try {
            String fileName = "binaryTreeData.txt";
            String fileName1 = "binaryTreeData1.txt";

            BinaryTree<String> binaryTree = new BinaryTree<>();


            //read binary Tree from file and return the tree

            binaryTree = getBinaryTree(fileName);
            //check avl or nor
            AVLTree<String> avlTree2 = new AVLTree<>(binaryTree);

            System.out.println(avlTree2.toString());

            binaryTree = getBinaryTree(fileName1);
            //check avl or nor
            AVLTree<String> avlTree3 = new AVLTree<>(binaryTree);




        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File couldnt opened");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error IO");
        }

    }


    private static BinaryTree<String> getBinaryTree(String fileName) throws IOException {

        BinaryTree<String> binaryTree = new BinaryTree<>();


        BufferedReader br;

            br = new BufferedReader(new FileReader(new File(fileName)));

            binaryTree = binaryTree.readBinaryTree(br);

            System.out.println(binaryTree.toString());
            br.close();


        return binaryTree;
    }


}
