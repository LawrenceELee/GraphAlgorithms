/** *************************************************
 * Dijkstra's shortest path algorithm.
 *
 * No negative weights.
 * No directed cycles.
 *
 *
 * Applications:
 * GPS (map routing)
 * Currentcy arbitrage
 *
 *
 * ************************************************** */

class DijkstraSP{
    private DirectedEdge[]      edgeTo;
    private double[]            distTo;
    private IndexMinPQ<Double>  pq;

    public DijkstraSP(EWDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for( int v=0; v < G.V(); ++v ){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq     = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while( !pq.isEmpty() ){
            int v = pq.delMin();
            for( DirectedEdge e: G.adj(v) ){
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e){
        int v = e.from();
        int w = e.to();
        
        if( distTo[w] > distTo[v] + e.weight() ){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;

            if( pq.contains(w) )    pq.decreaseKey(w, distTo[w]);
            else                    pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo(v) < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if( !hasPathTo(v) )     return null;
        Stack<DirectedEdge> path = new Stack<>();
        for( DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()] ){
            path.push(e);
        }
        return path;
    }

    //tester/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        EWDigraph G = new EWDigraph(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        DijkstraSP sp = new DijkstraSP(G, s);


        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                if (sp.hasPathTo(t)) {
                    for (DirectedEdge e : sp.pathTo(t)) {
                        StdOut.print(e + "   ");
                    }
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }
    
}
