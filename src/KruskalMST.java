/** *************************************************
 * Kruskal min spanning tree.
 *
 * Grows by joining edges as clusters.
 *
 * It puts all the edges into a priority queue. Then it selects the top
 * item (the edge with lowest weight) from the pq.
 *
 * It check if adding edge e creates a loop/cycle via the union find data structure.
 * If it does, don't add it to the MST.
 *
 * Applications of MST (Kruskal or Prim):
 * Cluster analysis
 * Finding road networks
 * Approximation algs for NP-hard problems like traveling salesman
 * Real time facial recognition
 *
 * Runtime: O(E log E) due to the delMin() for PQ.
 * ************************************************** */

class KruskalMST{
    private Queue<Edge> mst = new Queue<>();
    //used to keep track of which edges we used to construct mst
    private double weight;      //total weight of tree

    public KruskalMST(EWGraph G){
        MinPQ<Edge> pq = new MinPQ<>();
        UF          uf = new UF(G.V());

        //add all edges to priority queue, the pq will do all the sorting
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        while( !pq.isEmpty() && mst.size() < G.V()-1 ){
            Edge e = pq.delMin();   //greedily add edges to mst

            int v = e.either();
            int w = e.other(v);

            if( uf.connected(v,w) )     continue; 
            //skip if adding this edge creates a cycle

            uf.union(v,w);          //merge sets
            mst.enqueue(e);         //add edge to mst
            weight += e.weight();   //update new weight
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return weight;
    }

    //test/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        EWGraph G = new EWGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}

