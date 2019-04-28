package com.SinanElverenHW6.BTreeSearch;


public class MainQ2 {


    public static void main(String args[]) {

        BTree<Integer> bTree = new BTree<>(4);

        bTree.add(12);
        System.out.println("_______________________add 12\n"+bTree.toString());

        bTree.add(10);
        System.out.println("_______________________add 10\n"+bTree.toString());

        bTree.add(9);
        System.out.println("_______________________add 9\n"+bTree.toString());

        bTree.add(30);
        System.out.println("_______________________add 30\n"+bTree.toString());

        bTree.add(23);
        bTree.add(3);
        System.out.println("_______________________add 23 3\n"+bTree.toString());

        bTree.add(2);
        System.out.println("_______________________add 2\n"+bTree.toString());

        bTree.add(1);
        System.out.println("_______________________add 1\n"+bTree.toString());

        bTree.add(11);
        System.out.println("_______________________add 11\n"+bTree.toString());

        bTree.add(4);
        System.out.println("_______________________add 4\n"+bTree.toString());

        bTree.add(20);
        System.out.println("_______________________add 20\n"+bTree.toString());

        bTree.add(6);
        System.out.println("_______________________add 6\n"+bTree.toString());

        bTree.add(7);
        System.out.println("_______________________add 7\n"+bTree.toString());

        bTree.add(8);
        System.out.println("_______________________add 8\n"+bTree.toString());



        BTree<Integer> bTree2 = new BTree<>(6);

        System.out.println("_______________________Second Tree \n");
        bTree2.add(5);
        bTree2.add(6);
        bTree2.add(7);
        bTree2.add(10);
        bTree2.add(20);
        System.out.println("_______________________add 5  6  7  10  20\n"+bTree2.toString());
        bTree2.add(200);
        System.out.println("_______________________add 200\n"+bTree2.toString());

        bTree2.add(100);
        bTree2.add(30);
        System.out.println("_______________________add 100  30\n"+bTree2.toString());

        bTree2.add(50);
        System.out.println("_______________________add 50\n"+bTree2.toString());

        bTree2.add(45);
        bTree2.add(44);
        bTree2.add(65);
        bTree2.add(70);
        bTree2.add(80);
        bTree2.add(15);
        bTree2.add(1);
        bTree2.add(2);
        bTree2.add(3);
        bTree2.add(8);
        bTree2.add(11);
        bTree2.add(12);
        bTree2.add(13);
        bTree2.add(14);
        bTree2.add(16);
        bTree2.add(18);
        bTree2.add(19);
        bTree2.add(21);
        System.out.println("_______________________\n"+bTree2.toString());



    }
}
