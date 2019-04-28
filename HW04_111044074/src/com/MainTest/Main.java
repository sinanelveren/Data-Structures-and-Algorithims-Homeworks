package com.MainTest;

import com.generalTreeSinanElveren.GeneralTree;

/**
 * @author Sinan Elveren - Gebze Technical University
 * @since <pre>Apr 16, 2018</pre>
 * @version 1.0
 * Main class that testing general tree
 */
public class Main {

    public static void main(String args[]){

        GeneralTree<String> testTree1 = new GeneralTree<>();
        GeneralTree<String> testTree2 = new GeneralTree<>();
        GeneralTree<String> testTree3 = new GeneralTree<>();


        /*
         *          1_______________________________
         *           a_           c_________       3____________
         *            .            2_  e   f____    x______    y
         *                          d       X_ Y_    k___ l___
         *                          .        B  .     .    A
         */
        try {
            testTree1.add("1", "a");
            System.out.println(testTree1.toString());
            testTree1.add("2", "b");        //not inculde
            System.out.println(testTree1.toString());
            testTree1.add("1", "c");
            System.out.println(testTree1.toString());
            testTree1.add("1", "3");
            System.out.println(testTree1.toString());
            testTree1.add("c", "2");
            System.out.println(testTree1.toString());
            testTree1.add("2", "d");
            System.out.println(testTree1.toString());
            testTree1.add("c", "e");
            System.out.println(testTree1.toString());
            testTree1.add("c", "f");
            System.out.println(testTree1.toString());

            testTree1.add("3", "x");
            System.out.println(testTree1.toString());
            testTree1.add("3", "y");
            System.out.println(testTree1.toString());
            testTree1.add("x", "k");
            System.out.println(testTree1.toString());
            testTree1.add("x", "l");
            System.out.println(testTree1.toString());
            testTree1.add("l", "A");
            System.out.println(testTree1.toString());
            testTree1.add("f", "X");
            System.out.println(testTree1.toString());
            testTree1.add("f", "Y");
            System.out.println(testTree1.toString());
            testTree1.add("X", "B");
            System.out.println(testTree1.toString());

            testTree1.add("T", "C");         //not include
            System.out.println(testTree1.toString());

            System.out.println("TESTED search method - traverse all elements");

            System.out.println(testTree1.toStringBinaryTree());
            System.out.println(testTree1.toString());


            /* test second tree
             * from cours's slide
             */
            testTree2.add("William I", "Robert");
            testTree2.add("Robert", "William III");
            testTree2.add("William I", "William II");
            testTree2.add("William I", "Adela");
            testTree2.add("William I", "Henry I");
            testTree2.add("Adela", "Stephan");
            testTree2.add("Henry I", "William");
            testTree2.add("Henry I", "Matilda");
            testTree2.add("Matilda", "Henry II");
            testTree2.add("Henry II", "Henry");
            testTree2.add("Henry II", "Richard I");
            testTree2.add("Henry II", "Geoffrey");
            testTree2.add("Henry II", "Jhon");
            testTree2.add("Geoffrey", "Arthur");
            testTree2.add("Jhon", "Henry III");
            testTree2.add("Jhon", "Richard");
            testTree2.add("Henry III", "Edward I");
            testTree2.add("Henry III", "Edmund");
            testTree2.add("Edward I", "Edward II");
            testTree2.add("Edward I", "Thomas");
            testTree2.add("Edward I", "Edmund II");
            testTree2.add("Edward II", "Edward III");

            System.out.println(testTree2.toStringBinaryTree());
            System.out.println(testTree2.toString());
            System.out.println("Added Second Tree");


            //test level order
            testTree1.testLevelOrder =true;
            testTree2.testLevelOrder =true;
            testTree1.add("X", "B");
            System.out.println(testTree1.toString());

        }
        catch (IllegalArgumentException e){
            System.out.println("This child item already exist in tree");
            System.out.println(e + " < already exist");
        }
        catch (NullPointerException e){
            System.out.println(e+ " <Unknown error");
        }


    }
}
