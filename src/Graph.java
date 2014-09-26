/** *************************************************
 * Unweighted, undirected graph object.
 *
 * Edges are implicit between nodes via the adjcency list,
 * for weighted graphs edges will be explicit objects.
 *
 * ************************************************** */

class Graph{
    private Bag<Integer>[] adj; //adj list
    private         int    E;   //num of edges
    private final   int    V;   //num of vertices/nodes

    public Graph(int V){
        this.V = V;
        this.E = 0;
        
        adj = (Bag<Integer>[]) new Bag[V];
        //need ugly cast b/c can't instatiate generic arrays
        for( int v=0; v < V; ++v ){
            adj[v] = new Bag<Integer>();
        }
    }

    //helper to create graph from text file quickly
    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        ++E;
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    //tester/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
    
}
