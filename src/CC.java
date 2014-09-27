/** *************************************************
 * Computes connected components of a graph.
 * 
 * Two nodes are connected if they are joined by a path.
 * A connected component is a a maximal set of these connected nodes.
 *
 * Applications:
 * Groups/cliques in a social (undirected) network like facebook 
 * (friending requires mutal confirmation),
 * but not twitter (directed, followees don't have to follow followers).
 *
 * Runtime: O(V+E) since just a modified DFS.
 *
 * ************************************************** */

class CC{
    private boolean[]   marked;
    private int[]       id;         //name/id of this group of components
    private int[]       size;       //num of nodes in this component
    private int         count;

    public CC(Graph G){
        marked = new boolean[G.V()];
        id     = new int[G.V()];
        size   = new int[G.V()];
        count  = 0;

        for( int s=0; s < G.V(); ++s ){
            if( !marked[s] ){
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;  //the component name/id of node v is count
        size[count]++;  //increase size of this component by 1

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

    public int size(int v){
        return size[id(v)];
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
