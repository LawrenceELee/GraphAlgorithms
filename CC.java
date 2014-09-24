/** *************************************************
 * Computes connected components of a graph.
 *
 * A connected component is a 
 *
 * ************************************************** */

class CC{
    private boolean[]   marked;
    private int[]       id;
    private int         count;

    public CC(Graph G){
        marked = new boolean[G.V()];
        id     = new int[G.V()];
        count  = 0;

        for( int s=0; s < G.V(); ++s ){
            if( !marked[s] ){
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v){
        id[v] = count;
        marked[v] = true;

        for( int w: G.adj(v) ){
            if( !marked[w] ){
                dfs(G, w);
            }
        }
    }

    //returns which cc this node belows to
    public int id(int v){
        return id[v];
    }

    //number of connected components
    public int count(){
        return count;
    }

    public boolean areConnected(int v, int w){
        return id(v) == id(w);
    }

    //test/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        int M = cc.count();
        StdOut.println(M + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
    
}
