/** *************************************************
 *
 * ************************************************** */
class BellmanFordSP{
    private double[]        distTo;
    private DirectedEdge[]  edgeTo;
    private boolean[]       onQueue;  //is node v currently on the queue?
    private Queue<Integer>  que;
    private Iterabe<DirectedEdge> cycle;

    public BellmanFord(EWDigraph G, int s){
        distTo = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        que = new Queue<>();


    }

}
