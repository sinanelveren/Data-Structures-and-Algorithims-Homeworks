package MainTest;


import com.SinanElverenHW5.part1HW5.DoubleHashingMap;
import com.SinanElverenHW5.part2HW5.RecursiveHashingSet;
import com.SinanElverenHW5.part3HW5.MergeSortDLL;
import com.SinanElverenHW5.part4HW5.*;


import java.lang.reflect.Array;
import java.util.*;
import java.util.Random;


/**
 * @author Sinan Elveren - Gebze Technical University
 * @since <pre>May 2, 2018</pre>
 * Main class that testing ..*
 */
@SuppressWarnings("unchecked")
public class Main {
    static Random rand = new Random();

    public static long mergeDLLTime = 0;
    public static long mergeTime = 0;
    public static long insertTime = 0;
    public static long quickTime = 0;
    public static long heapTime = 0;



    public static void main(String args[]) {
        int size = 0;

        try {
            System.out.println("Testing PART 1");
            testPart1();

            System.out.println("\n\n\n\nTesting PART 2");
            testPart2();

            System.out.println("\n\n\n\nTesting PART 3");
            testPart3();
            
            System.out.println("\n\n\n\nTesting PART 4\n\n\n\n");


            for (int i = 0; i < 10; i++) {
                mergeDLLTime = 0;
                mergeTime = 0;
                insertTime = 0;
                quickTime = 0;
                heapTime = 0;


                size =  rand.nextInt(7000) + 20;
                for (int j = 0; j < 10; j++) {

                    testPart4(size);
                }
                System.out.println(i+". AVARAGE :");
                System.out.println(" : MERGE DLL = " +mergeDLLTime /10);
                System.out.println(" : MERGE = " +mergeTime / 10);
                System.out.println(" : INSERT = " +insertTime/ 10);
                System.out.println(" : QUICK = " +quickTime/10);
                System.out.println(" : HEAP = " +heapTime/10);
                System.out.println("\n\n");
            }





            System.out.println("\n\n\n\nTesting PART 5\n\n\n\n");

            //need to reverse sorted array..

             for (int i = 0; i <10 ; i++) {

                testPart5(100);
                System.out.println("End of size 100");


                testPart5(1000);
                System.out.println("End of size 1000");

                testPart5(5000);
                System.out.println("End of size 5000");


                testPart5(10000);
                System.out.println("End of size 10 000");


                testPart5(7000);
                System.out.println("End of size 7 000");

                testPart5(500);
                System.out.println("End of size 500");


                System.out.println(i+1 + " : MERGE DLL = " +mergeDLLTime);
                System.out.println(i+1 + " : MERGE = " +mergeTime);
                System.out.println(i+1 + " : INSERT = " +insertTime);
                System.out.println(i+1 + " : QUICK = " +quickTime);
                System.out.println(i+1 + " : HEAP = " +heapTime);
                System.out.println("\n\n");
            }


        }
        catch (IllegalArgumentException e){
            System.out.println("Illegal Argument :" + e);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Out of bound - size :" + e);
        }
        catch (ClassCastException e){
            System.out.println("Class Cast :" + e);
        }
        catch (UnsupportedOperationException e){
            System.out.println("Unsupported operation :" + e);
        }
        catch (NullPointerException e){
            System.out.println("Null pointer :" + e);
        }
        catch (Exception e){
            System.out.println("unHandled exception");
        }

    }


    private static void testPart5(int size) {

        long oldTime = 0;
        Integer [] array = new Integer[size];



        MergeSortDLL.Node  mergeDllNode;
        MergeSortDLL<Integer>       mergeSortDLL = new MergeSortDLL<>();


        InsertionSort   insertionSort = new InsertionSort();
        MergeSort       mergeSort     = new MergeSort();
        QuickSort       quickSort     = new QuickSort();
        HeapSort        heapSort      = new HeapSort();




        //Generate - fill array
        for (int i = 0; i < array.length; i++) {

            array[i] = rand.nextInt(100) + 1;
        }




        //copy to nodes for merge sort
        for (int i = 0; i < array.length; i++) {

            new MergeSortDLL.Node(array[i]);
        }





        oldTime = System.nanoTime();
        mergeSortDLL.mergeSort(mergeSortDLL.root);
        mergeDLLTime += System.nanoTime() - oldTime;


        oldTime = System.nanoTime();
        insertionSort.sort(array);
        insertTime += System.nanoTime() - oldTime;


        oldTime = System.nanoTime();
        mergeSort.sort(array);
        mergeTime += System.nanoTime() - oldTime;



        oldTime = System.nanoTime();
        quickSort.sort(array);
        quickTime += System.nanoTime() - oldTime;



        oldTime = System.nanoTime();
        heapSort.sort(array);
        heapTime += System.nanoTime() - oldTime;






    }


    private static void testPart4(int size) {

        long oldTime = 0;
        Integer [] array = new Integer[size];



        MergeSortDLL.Node  mergeDllNode;
        MergeSortDLL<Integer>       mergeSortDLL = new MergeSortDLL<>();


        InsertionSort   insertionSort = new InsertionSort();
        MergeSort       mergeSort     = new MergeSort();
        QuickSort       quickSort     = new QuickSort();
        HeapSort        heapSort      = new HeapSort();





        //Generate - fill array
        for (int i = 0; i < array.length; i++) {

            array[i] = rand.nextInt(100) + 1;
        }




        //copy to nodes for merge sort
        for (int i = 0; i < array.length; i++) {

            new MergeSortDLL.Node(array[i]);
        }





        oldTime = System.nanoTime();
        mergeSortDLL.mergeSort(mergeSortDLL.root);
        mergeDLLTime += System.nanoTime() - oldTime;


        oldTime = System.nanoTime();
        insertionSort.sort(array);
        insertTime += System.nanoTime() - oldTime;


        oldTime = System.nanoTime();
        mergeSort.sort(array);
        mergeTime += System.nanoTime() - oldTime;



        oldTime = System.nanoTime();
        quickSort.sort(array);
        quickTime += System.nanoTime() - oldTime;



        oldTime = System.nanoTime();
        heapSort.sort(array);
        heapTime += System.nanoTime() - oldTime;







    }


    private static void testPart3() {



        MergeSortDLL.Node  mergeDllNode;
        MergeSortDLL<Integer>       mergeSortDLL = new MergeSortDLL<>();



        for (int i = 0; i < 15; i++) {

            new MergeSortDLL.Node(rand.nextInt(100) + 1);
        }



        mergeSortDLL.mergeSort(mergeSortDLL.root);


        mergeSortDLL.print(mergeSortDLL.root);


    }


    public static void testPart1() throws RuntimeException {


            Map<Integer, String> testDoubleHash = new DoubleHashingMap<Integer, String>();
            Map<Integer, String> testDoubleHash2 = new DoubleHashingMap<Integer, String>(20,0.5);



            System.out.println("Size() : " + testDoubleHash.size());
            System.out.println("containsKey(2) : " + testDoubleHash.containsKey(2));

            System.out.println("\nput(1, sinan) : " + testDoubleHash.put(1,"sinan"));
            System.out.println("put(2, elveren) : " + testDoubleHash.put(2,"elveren"));

            System.out.println("\ncontainsKey(2) : " + testDoubleHash.containsKey(2));

            System.out.println(testDoubleHash.toString());

            testDoubleHash.clear();
            System.out.println("clear() ");
            System.out.println("size() : "+ testDoubleHash.size() );


            System.out.println("\nput(1, sinan) : " + testDoubleHash.put(1,"sinan"));
            System.out.println("put(2, elveren) : " + testDoubleHash.put(2,"elveren"));


            System.out.println("put(10, Gebze) : " + testDoubleHash.put(10,"Gebze"));
            System.out.println("put(11, Tech) : " + testDoubleHash.put(11,"Tech"));
            System.out.println("put(12, Univ) : " + testDoubleHash.put(12,"Univ"));

            System.out.println("\ncontainsKey(2) : " + testDoubleHash.containsKey(2));
            System.out.println(testDoubleHash.toString());


            System.out.println("Get(2) : " + testDoubleHash.get(2));
            System.out.println("Remove(2) : " + testDoubleHash.remove(2));
            System.out.println("Get(2) : " + testDoubleHash.get(2));


            System.out.println("\nput(1, sinan) change : " + testDoubleHash.put(1,"SINAN"));
            System.out.println("\nput(13, onUc)  : " + testDoubleHash.put(13,"onUc"));
            System.out.println("\nput(14, onDort)  : " + testDoubleHash.put(14,"onDort"));

            System.out.println("\nSize() :" +testDoubleHash.size());
            System.out.println(testDoubleHash.toString());

            System.out.println("\nput(22, yirmiIki)  : " + testDoubleHash.put(22,"yirmiiki"));
            System.out.println("\nput(20, yirmi)  : " + testDoubleHash.put(20,"yirmi"));
            System.out.println("\noto rehash");

            System.out.println("Size() :" +testDoubleHash.size());
            System.out.println(testDoubleHash.toString());

            System.out.println("containsKey(11)" + testDoubleHash.containsKey(11));
            System.out.println("containsKey(15)" + testDoubleHash.containsKey(15));
            System.out.println("containsKey(20)" + testDoubleHash.containsKey(20));

            System.out.println("containsValue(Tech)" + testDoubleHash.containsValue("Tech"));
            System.out.println("containsValue(yok)" + testDoubleHash.containsValue("yok"));
            System.out.println("containsValue(yirmi)" + testDoubleHash.containsValue("yirmi"));

            Set<Map.Entry<Integer, String>> entrySetDoubleHash = testDoubleHash.entrySet();


            System.out.println("\nEntrySet() - Iterator via forEach");
            testDoubleHash.forEach((k,v)->System.out.println("[" + k + "]->\t[" + v+"]"));

            System.out.println("Size() : " + entrySetDoubleHash.size());


            System.out.println("\nTest keySet() : " + testDoubleHash.keySet());


            testDoubleHash2.put(7, "SEVEN");
            testDoubleHash2.put(24, "2.4.");
            testDoubleHash2.put(5, "FIVE");
            testDoubleHash2.put(0, "ZERO");
            testDoubleHash2.put(4, "FOUR");

            System.out.println(testDoubleHash2.toString());

            System.out.println("\nput(7) & put(24) in to newMap & putAll to oldMap");
            testDoubleHash.putAll(testDoubleHash2);
            System.out.println("\nkeySet() : " + testDoubleHash.keySet());


            System.out.println("Get(24) : " + testDoubleHash.get(24));
            System.out.println("Remove(24) : " + testDoubleHash.remove(24));

            System.out.println("\nkeySet() : " + testDoubleHash.keySet());
            System.out.println("\nValues() : " + testDoubleHash.values());

            System.out.println(testDoubleHash.toString());




    }


    public static void testPart2()  throws RuntimeException {

            RecursiveHashingSet<Integer> testRecHashSet = new RecursiveHashingSet<>();
            RecursiveHashingSet<Integer> testRecHashSet2 = new RecursiveHashingSet<>(20);

            System.out.println("add(4) : " + testRecHashSet.add(4));
            System.out.println("add(4) : " + testRecHashSet.add(4));
            System.out.println("size() : " + testRecHashSet.size());

            System.out.println("add(7) : " + testRecHashSet.add(7));
            System.out.println("add(0) : " + testRecHashSet.add(0));
            System.out.println("add(14) : " + testRecHashSet.add(14));
            System.out.println("add(21) : " + testRecHashSet.add(21));
            System.out.println("add(28) : " + testRecHashSet.add(28));
            System.out.println("add(35) : " + testRecHashSet.add(35));
            System.out.println("add(140) : " + testRecHashSet.add(140));
            System.out.println("add(105) : " + testRecHashSet.add(105));



            System.out.println("add(5) : " + testRecHashSet.add(5));
            System.out.println("add(15) : " + testRecHashSet.add(15));
            System.out.println("add(16) : " + testRecHashSet.add(16));

            System.out.println("add(17) : " + testRecHashSet.add(17));
            System.out.println("add(18) : " + testRecHashSet.add(18));
            System.out.println("add(22) : " + testRecHashSet.add(22));



            System.out.println("size() : " + testRecHashSet.size());
            System.out.println(testRecHashSet.toString());

            System.out.println("remove(22) " + testRecHashSet.remove(22));
            System.out.println("remove(105) " + testRecHashSet.remove(105));
            System.out.println("add(105) : " + testRecHashSet.add(22));
            System.out.println("reove(22) : " + testRecHashSet.remove(22));
            System.out.println("remove(105) : " + testRecHashSet.remove(105));

            System.out.println("size() : " + testRecHashSet.size());
            System.out.println(testRecHashSet.toString());

            Object[] obj = testRecHashSet.toArray();

            System.out.println("toArray() :");
            for (int i = 0; i < obj.length; i++) {
                System.out.print( "[" + obj[i] + "] ");
            }


            System.out.println("\n\n");

            Collection<Integer> c = new ArrayList<>();
            System.out.println("Collection.add(100) : " + c.add(100));
            System.out.println("Collection.add(101) : " + c.add(5));
            System.out.println("addAll(collection)" + testRecHashSet.addAll(c));


            System.out.println(testRecHashSet.toString());

            System.out.println("Again --> addAll(collection)" + testRecHashSet.addAll(c));


            System.out.println("\n\nTest toArray(arr [101, 102, 103]) : ");
            Object obj2[] = new Object[]{101, 102, 103};

            obj2 = testRecHashSet.toArray(obj2);

            System.out.println("\ntoArray( arr[] ) :");
            for (int i = 0; i < obj2.length; i++) {
                System.out.print( "[" + obj2[i] + "] ");
            }


            System.out.println("\n\nContains(7) : " + testRecHashSet.contains(7));
            System.out.println("Contains(13) : " + testRecHashSet.contains(13));



            obj = testRecHashSet.toArray();

            System.out.println("\n\ntoArray() :");
            for (int i = 0; i < obj.length; i++) {
                System.out.print( "[" + obj[i] + "] ");
            }

            System.out.println("\n\nRemoveAll(collection [100, 5] ): " + testRecHashSet.removeAll(c));
            obj = testRecHashSet.toArray();

            System.out.println("toArray() :");
            for (int i = 0; i < obj.length; i++) {
                System.out.print( "[" + obj[i] + "] ");
            }


            c.add(21);
            c.add(7);



            System.out.println("\n\nretainAll( collection arr[100, 5, 7, 21] ) : " + testRecHashSet.retainAll(c));
            obj = testRecHashSet.toArray();

            System.out.println("toArray() :");
            for (int i = 0; i < obj.length; i++) {
                System.out.print( "[" + obj[i] + "] ");
            }


            System.out.println("\n\nContainsAll( arr[100, 5, 21, 7] ) : " + testRecHashSet.containsAll(c));

            c.remove(100);
            c.remove(5);
            System.out.println("\n\nContainsAll( arr[21, 7] ) : " + testRecHashSet.containsAll(c));



            System.out.println("\n\nset1.equals(set2) : TRUE?" + testRecHashSet.equals(testRecHashSet));

            testRecHashSet2.add(1);
            System.out.println("\n\nset1.equals(set2) : FALSE?" + testRecHashSet.equals(testRecHashSet2));



            testRecHashSet.clear();
            System.out.println("\n\nClear() --> Size() : " + testRecHashSet.size());




    }

}