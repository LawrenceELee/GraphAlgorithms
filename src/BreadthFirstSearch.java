/** *************************************************
 * Breath first search.
 *
 * Explores graph from source s in concentric rings at increaseing distances
 * from source s. Like layers in an onion.
 *
 * Uses a Queue data structure. If you switch Queue with Stack, you get DFS.
 *
 * Runtime: O(E+V)
 *          since every node and edge are explored in worst case.
 *          even though there are nested loops.
 *
 *
 * Space  : O(V) if using adj list, proportional to the number of nodes.
 *          O(V^2) if using adj matrix
 *
 * Sample run and output:
 * java BreadthFirstSearch data/graph2.txt 0
 * 0 to 0 (0):  0
 * 0 to 1 (3):  0-24-3-1
 * 0 to 2 (1):  0-2
 * 0 to 3 (2):  0-24-3
 * 0 to 4 (2):  0-2-4
 * 0 to 5 (2):  0-2-5
 * 0 to 6 (2):  0-24-6
 * 0 to 7 (2):  0-2-7
 * 0 to 8 (3):  0-2-10-8
 * 0 to 9 (3):  0-2-7-9
 * 0 to 10 (2):  0-2-10
 * 0 to 11 (2):  0-24-11
 * 0 to 12 (2):  0-2-12
 * 0 to 13 (3):  0-2-10-13
 * 0 to 14 (3):  0-2-4-14
 * 0 to 15 (3):  0-24-3-15
 * 0 to 16 (3):  0-2-5-16
 * 0 to 17 (3):  0-2-4-17
 * 0 to 18 (3):  0-2-12-18
 * 0 to 19 (4):  0-2-10-8-19
 * 0 to 20 (3):  0-2-12-20
 * 0 to 21 (3):  0-2-12-21
 * 0 to 22 (3):  0-2-10-22
 * 0 to 23 (2):  0-2-23
 * 0 to 24 (1):  0-24
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
        Queue<Integer> que = new Queue<>();
        distTo[s] = 0;
        marked[s] = true;
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
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);

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

