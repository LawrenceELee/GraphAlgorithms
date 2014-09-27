/** *************************************************
 * Directed graph implementation.
 *
 * Applications for directed graphs:
 * Road network: vertex: intersection, edge: one-way streets.
 * Implication graph
 * Combinational circuit (logic circuit)
 * Internet. vertex: webpage, edge: hyperlink
 * Scheduling. vertex: task, edge: precedence constraint.
 * Citation: vert: article, edge: citation.
 * Java garbage collection. vert: object, edge: reference.
 *
 *
 * ************************************************** */

class Digraph{
    private Bag<Integer>[]  adj;
    private final int       V;
    private       int       E;

    public Digraph(int V){
        this.V = V;
        this.E = 0;

        adj = (Bag<Integer>[]) new Bag[V];
        for( int v=0; v < V; ++v ){
            adj[v] = new Bag<>();
        }
    }

    //constructor to build digraph from reading text input file
    public Digraph(In in) {
        this.V = in.readInt();
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w); 
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        ++E;
    }

    public int E(){ return E; }
    public int V(){ return V; }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    //will be useful for SCC
    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for( int v=0; v < V; ++v ){
            for( int w: adj[v] ){
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    //test
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }

}
