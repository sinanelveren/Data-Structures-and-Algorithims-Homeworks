package SinanElverenHW7.Q1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GraphGTU extends AbstractGraph {

    private ArrayList<Edge> Array;


    /**
     * Construct a graph with the specified number of vertices
     * and the directed flag. If the directed flag is true,
     * this is a directed graph.
     *
     * @param numV     The number of vertices
     * @param directed The directed flag
     */
    public GraphGTU(int numV, boolean directed) {
        super(numV, directed);
        ListGraph listGraph = new ListGraph(numV, directed);

    }


    public void addEdges(int edgeCount){
        Random generate = new Random();
        int randomEdge = generate.nextInt(edgeCount);

        int count = 0;

        for (int i =0; i< randomEdge; i++)
        {
            int s = generate.nextInt(this.getNumV()); // source
            int d = generate.nextInt(this.getNumV()); // destination

            Edge nEdge = new Edge(s,d);
            //System.out.println("edge: " + nEdge);
            boolean status = false; // ayni edge olup olmadigini kontrol eder

            for (int j =0 ; j< this.getNumV(); j++)
            {
                Iterator itr = this.edgeIterator(j);

                while (itr.hasNext())
                {
                    Edge edge = (Edge) itr.next();

                    if(edge.equals(nEdge) || nEdge.getDest() == nEdge.getSource())
                    {
                        status = true;
                    }
                }
            }
            if(!status)
            {
                this.insert(nEdge);
                count++;
            }
        }

    }


    /**
     * Insert a new edge into the graph.
     *
     * @param edge The new edge
     */
    @Override
    public void insert(Edge edge) {

    }


    /**
     * Determine whether an edge exists.
     *
     * @param source The source vertex
     * @param dest   The destination vertex
     * @return true if there is an edge from source to dest
     */
    @Override
    public boolean isEdge(int source, int dest) {

        return false;
    }


    /**
     * Get the edge between two vertices.
     *
     * @param source The source vertex
     * @param dest   The destination vertex
     * @return The Edge between these two vertices
     * or an Edge with a weight of
     * Double.POSITIVE_INFINITY if there is no edge
     */
    @Override
    public Edge getEdge(int source, int dest) {

        return null;
    }


    /**
     * Return an iterator to the edges connected
     * to a given vertex.
     *
     * @param source The source vertex
     * @return An Iterator to the vertices
     * connected to source
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {

        return null;
    }
}
