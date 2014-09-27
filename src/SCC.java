/** *************************************************
 * Computes the strongly connected components using Kosaraju-Sharir algorithm.
 *
 * Two VERTICES v and w are strongly connected if there is both a directed
 * pat from v to w and from w to v.
 *
 * A GRAPH is strongly connected if every vertex is reachable from 
 * every other vertex (i.e. all vertices are strongly connected).
 *
 * Kosaraju SCC alg runs DFS twice:
 * 1) Compute reverse(G).
 * 2) Compute post-ordering on G reverse. (first dfs)
 * 3) Run DFS on G reverse post-ordering. (second dfs)
 *
 * Runtime: O(V+E), linear to number of nodes V and edges E.
 *
 * Applications:
 * 2-satisfiablity problem.
 *
 * ************************************************** */

class SCC{
    private boolean[]   marked;
    private int[]       id;
    private int         count;


    public SCC(Digraph G){
        marked      = new boolean[G.V()];
        id          = new int[G.V()];
        this.count  = 0;

        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

        for( int v: dfs.reversePost() ){
            if( !marked[v] ){
                dfs(G, v);
                ++count;
            }
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        id[v]     = count;
        for( int w: G.adj(v) ){
            if( !marked[w] ){
                dfs(G, w);
            }
        }
    }

    public int count(){
        return count;
    }

    public int id(int v){
        return id[v];
    }

    public boolean areStronglyConnected(int v, int w){
        return id(v) == id(w);
    }

    //tester/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SCC scc = new SCC(G);

        // number of connected components
        int M = scc.count();
        StdOut.println(M + " components");

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[scc.id(v)].enqueue(v);
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
