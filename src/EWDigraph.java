/** *************************************************
 *
 * ************************************************** */

class EWDigraph{
    private Bag<DirectedEdge>[] adj;
    private int         E;
    private final int   V;

    public EWDigraph(int V){
        this.V = V;
        this.E = 0;
        
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for( int v=0; v < V; ++v ){
            adj[v] = new Bag<>();
        }
    }

    public EWDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public void addEdge(DirectedEdge e){
        int v = e.from();

        adj[v].add(e);
        ++E;
    }

    public Iterable<DirectedEdge> adj(int v){
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
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    //test/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        EWDigraph G = new EWDigraph(in);
        StdOut.println(G);
    }

}
