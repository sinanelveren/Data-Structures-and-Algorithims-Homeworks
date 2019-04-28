
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Lütfullah TÜRKER
 */
public class DataHw9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Burada dosyaya kaydedilmiş olan grafı okuma için File objesi oluşturuyoruz
    File graphData = new File("ExampleIGraphInputOrOutputFile.txt");
    Scanner scnr;
        try {
            scnr = new Scanner(graphData );
            ListGraph lgObj = (ListGraph) AbstractGraph.createGraph(scnr, false, "List");
            Scanner scnr2 = new Scanner(graphData );
            MatrixGraph mtObj = (MatrixGraph) AbstractGraph.createGraph(scnr2, false, "Matrix");
            ListGraph lgObj2 = new ListGraph(6, true);
            lgObj2.addRandomEdgesToGraph(10);
            lgObj2.writeGraphToFile("addRandomEdgesForListGraph.txt");
            System.out.println("RandomEdgesForListGraph in ListGraph ==>>\n  BreadthFirstSearch Parent Array ==> ");
            int[] arr = lgObj2.breadthFirstSearch(0);
            for (int i =0;i<arr.length;++i)
                System.out.print("  " + arr[i]);
            System.out.println("\n");
            System.out.println("Is Bipartite ?  ==> "+lgObj2.isBipartiteUndirectedGraph()+"\n");
                //lgObj.getConnectedComponentUndirectedGraph();
            int[] arr2 = lgObj.breadthFirstSearch(0);
            System.out.println("ExampleIGraphInputOrOutputFile in ListGraph ==>>\n  BreadthFirstSearch Parent Array ==> ");
            for (int i =0;i<arr2.length;++i)
                System.out.print("  " + arr2[i]);
            System.out.println("\n");
            lgObj.writeGraphToFile("ExampleIGraphOutput.txt");
            System.out.println("Is Bipartite ?  ==> "+lgObj.isBipartiteUndirectedGraph()+"\n");
            int[] arr3 = mtObj.breadthFirstSearch(0);
            System.out.println("ExampleIGraphInputOrOutputFile in MatrixGraph  ==>>\n  BreadthFirstSearch Parent Array ==> ");
            for (int i =0;i<arr3.length;++i)
                System.out.print("  " + arr3[i]);
            System.out.println("\n");
            lgObj.writeGraphToFile("ExampleIGraphOutputMatrixGraph.txt");
            System.out.println("Is Bipartite ?  ==> "+mtObj.isBipartiteUndirectedGraph()+"\n");
                
            File myTest = new File("myTest.txt");
            Scanner scnr3 = new Scanner(myTest);
            ListGraph lgObj3 = (ListGraph) AbstractGraph.createGraph(scnr3, false, "List");
            int[] arr4 = lgObj3.breadthFirstSearch(0);
            System.out.println("myTest File on ListGraph  ==>>\n  BreadthFirstSearch Parent Array ==> ");
            for (int i =0;i<arr4.length;++i)
                    System.out.print("  " + arr4[i]);
                System.out.println("\n");
            lgObj3.writeGraphToFile("myTestOutputOnListGraph.txt");
            System.out.println("Is Bipartite ?  ==> "+lgObj3.isBipartiteUndirectedGraph()+"\n");
            MatrixGraph mtObj2 = new MatrixGraph(6, true);
            mtObj2.addRandomEdgesToGraph(10);
            mtObj2.writeGraphToFile("addRandomEdgesForMatrixGraph.txt");
            System.out.println("RandomEdgesForMatrixGraph in MatrixGraph ==>>\n  BreadthFirstSearch Parent Array ==> ");
            int[] arr5 = mtObj2.breadthFirstSearch(0);
            for (int i =0;i<arr5.length;++i)
                    System.out.print("  " + arr5[i]);
            System.out.println("\n");
            System.out.println("Is Bipartite ?  ==> "+mtObj2.isBipartiteUndirectedGraph()+"\n");
                
        } catch (FileNotFoundException ex) {
            System.err.println(ex.toString());
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
    
}
