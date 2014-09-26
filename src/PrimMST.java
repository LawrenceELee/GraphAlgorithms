/** *************************************************
 * Prim min spanning tree.
 *
 * It grows by adding edges like limbs/branches on a tree.
 *
 * Prim and Dijkstra are very similar to each other.
 *
 * ************************************************** */

class PrimMST{
    private Edge[]           edgeTo;
    private double[]         distTo;
    private boolean[]        marked;
    private IndexMinPQ<Double> pq;


    public PrimMST(EWGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        
        //set all distances to infinity
        for( int v=0; v < G.V(); ++v ){ distTo[v] = Double.POSITIVE_INFINITY; }

        //run from every node to find all min spanning tress (forest) for graph
        for( int v=0; v < G.V(); ++v ){
            if( !marked[v] ){
                prim(G, v);
            }
        }

    }

    private void prim(EWGraph G, int s){
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);

        while( !pq.isEmpty() ){
            int v = pq.delMin();
            visit(G,v);
        }
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }

    private void visit(EWGraph G, int v){
        marked[v] = true;

        for(Edge e: G.adj(v) ){
            int w = e.other(v);             //find other end of Edge e

            if( marked[w] ) continue;       //skip edge that we've seen before
            if( distTo[w] > e.weight() ){
                distTo[w] = e.weight();
                edgeTo[w] = e;

                if( pq.contains(w) )    pq.decreaseKey(w, distTo[w]);
                else                    pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EWGraph G = new EWGraph(in);
        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }


}
