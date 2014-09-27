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
    private boolean[]       onQ;  //is node v currently on the queue?
    private Queue<Integer>  queue;
    private Iterable<DirectedEdge> cycle;

    private int cost;
    //num of calls to relax() used to check if neg cycle

    public BellmanFordSP(EWDigraph G, int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ    = new boolean[G.V()];
        queue = new Queue<>();

        for( int v=0; v < G.V(); ++v ){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;

        while( !queue.isEmpty() && !this.hasNegativeCycle() ){
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EWDigraph G, int v){
        for( DirectedEdge e: G.adj(v) ){
            int w = e.to();

            if( distTo[w] > distTo[v] + e.weight() ){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if( !onQ[w] ){
                //check if already on queue, to prevent going in
                //circles in the cycle
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if( cost++ % G.V() == 0 ){
                //if it is multiple of V, then we know it has a cycle
                findNegativeCycle();
            }
        }
    }

    private boolean hasNegativeCycle(){
        return cycle != null;
    }

    private Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }

    private void findNegativeCycle(){
        int V = edgeTo.length;
        EWDigraph spt = new EWDigraph(V);
        for( int v=0; v < V; ++v ){
            if( edgeTo[v] != null ){
                spt.addEdge(edgeTo[v]);
            }
        }
        EWDirectedCycle finder = new EWDirectedCycle(spt);
        cycle = finder.cycle();
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public double distTo(int v){
        return distTo[v];
    }

    //tester/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EWDigraph G = new EWDigraph(in);

        BellmanFordSP sp = new BellmanFordSP(G, s);

        // print negative cycle
        if (sp.hasNegativeCycle()) {
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.println(e);
        }

        // print shortest paths
        else {
            for (int v = 0; v < G.V(); v++) {
                if (sp.hasPathTo(v)) {
                    StdOut.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                    for (DirectedEdge e : sp.pathTo(v)) {
                        StdOut.print(e + "   ");
                    }
                    StdOut.println();
                }
                else {
                    StdOut.printf("%d to %d           no path\n", s, v);
                }
            }
        }
    }
}
