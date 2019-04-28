package SinanElverenHW7.Q1;


import SinanElverenHW7.CommanClass;

import java.util.ArrayList;

public class MainQ1 {


    public static void main(String args[]) {

        Graph graph = new ListGraph(6, false);

        graph.insert(new Edge(0,1));
        graph.insert(new Edge(1,2));
        graph.insert(new Edge(2,3));
        graph.insert(new Edge(3,4));
        graph.insert(new Edge(4,5));
      //  graph.insert(new Edge(0,3));


        CommanClass.plot_graph(graph);

        int pred[] = new int[6];
        double dist[] = new double[6];

        ArrayList<Integer> path = CommanClass.dijkstrasAlgorithm(graph,0,pred, dist, 4);



        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");

        }

        System.out.println("\nTotal Distance : "+ dist[path.get(path.size()-1)]);
    }
}