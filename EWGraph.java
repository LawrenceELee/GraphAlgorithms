/** *************************************************
 * Undirected, weighted graph.
 *
 * This data structure is used primarily for min spanning tree finding algs.
 *
 * ************************************************** */

class EWGraph{
    private Bag<Edge>[] adj;
    private int         E;
    private final int   V;

    public EWGraph(int V){
        this.V = V;
        this.E = 0;
        
        adj = (Bag<Edge>[]) new Bag[V];
        for( int v=0; v < V(); ++v ){
            adj[v] = new Bag<>();
        }
    }

    public EWGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);

        adj[v].add(e);
        adj[w].add(e);
        ++E;
    }

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public int E(){ return E; }
    public int V(){ return V; }

    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    //tester/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        EWGraph G = new EWGraph(in);
        StdOut.println(G);
    }


}
