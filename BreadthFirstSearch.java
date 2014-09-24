/** *************************************************
 * Breath first search.
 *
 * ************************************************** */

class BreadthFirstSearch{
    private boolean[] marked; 
    private int[]     edgeTo;
    private final int s;

    private int[] distTo; //optional. add if you want added functionality
    //to find shortest path for unweighted, undirected graphs

    public BreadthFirstSearch(Graph G, int s){
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        //optional
        distTo = new int[G.V()];
        for( int v=0; v < G.V(); ++v){
            distTo[v] = Integer.MAX_VALUE;
        }

        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> que = new Queue();
        que.enqueue(s);

        while( !que.isEmpty() ){
            int v = que.dequeue();

            for( int w: G.adj(v) ){
                if( !marked[w] ){
                    edgeTo[w] = v;
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1; //optional
                    que.enqueue(w);
                }
            }
        }
    }

    public int distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        Stack<Integer> path = new Stack<>();

        for( int x=v; x != s; x = edgeTo[x] ){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    //tester/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);

        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }

}

