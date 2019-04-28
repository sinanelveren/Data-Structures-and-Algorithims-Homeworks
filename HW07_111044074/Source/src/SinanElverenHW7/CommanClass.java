package SinanElverenHW7;

import SinanElverenHW7.Q1.Edge;
import SinanElverenHW7.Q1.Graph;
import SinanElverenHW7.Q1.ListGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CommanClass {

    public static void plot_graph(Graph graph){

        graph = (ListGraph) graph;

        StringBuilder s = new StringBuilder();
        s.append("The Linked List Representation of the graph is: \n");
        for (int v = 0; v < graph.getNumV(); v++) {
            s.append("\t(" + v + ") : ");
            for (Edge edge: ((ListGraph) graph).getEdges()[v]) {
                if(((ListGraph) graph).getEdges()[edge.getSource()].size() != 0)
                    s.append("-> [" + edge.getDest() + "] ");
            }
            s.append("\n");
        }

        System.out.println( s.toString());

    }




    /** Dijkstras Shortest-Path algorithm.
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     * @param pred Output array to contain the predecessorsin the shortest path
     * @param dist Output array to contain the distance in the shortest path
     * @param destination last vertex for shortes path
     * @return array of shortest path
     */
    public static ArrayList<Integer> dijkstrasAlgorithm(Graph graph,
                                                        int start,
                                                        int[] pred,
                                                        double[] dist, int destination) {
        int numV = graph.getNumV();
        int flag =1;
        HashSet < Integer > vMinusS = new HashSet< Integer >(numV);
        ArrayList< Integer > shortestPath = new ArrayList<>();

        shortestPath.add(start);
        // Initialize VS.
        for (int i = 0; i < numV; i++) {
            if (i != start) {
                vMinusS.add(i);
            }
        }
        // Initialize pred and dist.
        for (int v : vMinusS) {
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        // Main loop
        while (vMinusS.size() != 0) {
            // Find the value u in VS with the smallest dist[u].
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v];

                  //  System.out.println("\n"+u+" //// "+v);
                    u = v;

                }
            }
            // Remove u from vMinusS.
            vMinusS.remove(u);
            // Update the distances.
            for (int v : vMinusS) {
    //            System.out.println(u+" "+v);
                if(u < 0)
                    System.out.println("-1?error");

                if (graph.isEdge(u, v)) {
                    double weight = graph.getEdge(u, v).getWeight();

                    if (dist[u] + weight < dist[v]) {

                       // System.out.println("\n" + u + " **** " + v + ": " + pred[1]);

                        dist[v] = dist[u] + weight;
                        pred[v] = u;

                        //check previous node for delete from path
                        for (int i = 0; i < shortestPath.size(); i++) {
                            //is there any edge betweet node and previous node
                            if(graph.isEdge(shortestPath.get(i), u)){
                                for (int j = i+1; j < shortestPath.size(); j++) {
                                    shortestPath.remove(j);
                                }
                            }
                        }
                        // add to path until destinition

                        shortestPath.add(u);            //add a node to shortest path

                       if (u == destination) {
                            return shortestPath;        //return if the node isdestination
                        }
                    }

                }

            }
        }


        return shortestPath;

    }
}
