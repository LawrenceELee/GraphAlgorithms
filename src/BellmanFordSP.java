/** *************************************************
 * Bellman-Ford alg for finding the shortest path on Weighted, Directed
 * graphs with negative edge weights.
 *
 * BF can not solve shorest path problem if there is a negative cycle
 * (a cycle whose edges sum to a negative value). Since you can repeatedly
 * traverse the cycle over and over again to continually reduce the cost.
 * However, it can detect if the negative cycle exisits.
 *
 * If you know ahead of time that there are only positive weights, then
 * you can used Dijkstra alg. Since BF is slower than Dijkstra.
 *
 * Runtime: O(V*E)
 * Space  : O(V)
 *
 * BF, like Dijkstra, uses the principle of relaxation, in which an
 * approximation to the correct dist is gradually replaced by more accurate
 * values until eventually reaching the optimum soln.
 *
 * Unlike, Dijkstra, BF relaxes all outgoing edges, hence the O(V*E) 
 * runtime. Compared with Dijkstra which relaxes the min weight node
 * that has not been processed.
 *
 * ************************************************** */
class BellmanFordSP{
    private double[]        distTo;
    private DirectedEdge[]  edgeTo;
    private boolean[]       onQueue;  //is node v currently on the queue?
    private Queue<Integer>  queue;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EWDigraph G, int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        queue = new Queue<>();
    }

}
