package com.SinanElverenHW6.RedBlackTree;

import java.util.*;

public class MainQ1 {


    public static void main(String args[]) {

        RedBlackTreeWorst<Integer> redblack = new RedBlackTreeWorst<Integer>();


        redblack = createWorstTree();

        System.out.println("\n\nRed&Black Tree\n______________1th TEST\n");
        System.out.println(redblack.toString());



        redblack = createWorstTree();

        System.out.println("\n\nRed&Black Tree\n______________2nd TEST\n");
        System.out.println(redblack.toString());


    }


    private static RedBlackTreeWorst<Integer> createWorstTree() {
        Random generateInt = new Random();
        RedBlackTreeWorst<Integer> redblack = new RedBlackTreeWorst<Integer>();
        long time = 0;


        Set<Integer> arrSet = new HashSet<>();


        //create sequence via random numbers
        while (arrSet.size() != 14)
            arrSet.add(generateInt.nextInt(100) + 1);


        Object[] arr = arrSet.toArray();
        Arrays.sort(arr);


        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + "  ");
        }

        System.out.println("\nTotal elements count is : " + arr.length);


        //created sorted array  <-- above


        time = System.nanoTime();


        //now add items to R-B Tree
        //add first eleman,then add last element for cause maximum rotate and recoloring in tree
        redblack.add((Integer) arr[arr.length-1]);
        redblack.add((Integer) arr[0]);
        //add second and third elements
        redblack.add((Integer) arr[1]);
        redblack.add((Integer) arr[2]);




        //now add items from last-1 to 3rd element
        for (int i = arr.length-2; i > 2; i--) {
            redblack.add((Integer) arr[i]);
        }

        System.out.println("Creating time of tree :" + (System.nanoTime() - time));


        return redblack;
    }


}
