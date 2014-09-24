/** *************************************************
 * Depth first search traversal.
 *
 * Applications: 
 * * Finding if a path from v to w exists. If so, return the path.
 * * Checking connectivity.
 *
 * Usage:
 * java DepthSearchFirst <pathToGraphText> <sourceNode>
 *
 * ************************************************** */

import java.util.Stack;

class DepthFirstSearch{
    private int[]     edgeTo; //what was the previous node used to get to this node?
    private boolean[] marked; //have we visited this node before?
    private final int s;      //starting point, source node for the alg to begin

    public DepthFirstSearch(Graph G, int s){
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];

        dfs(G, s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;

        for( int w: G.adj(v) ){  //visit all neighbors, w, of current node v
            if( !marked[w] ){
                edgeTo[w] = v; //record which prev node we used to to here
                dfs(G, w);     //recurse on w
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    //returns the path use to get from s to v
    public Iterable<Integer> pathTo(int v){
        if( !hasPathTo(v) ) return null;

        Stack<Integer> path = new Stack<>();
        for( int x=v; x != s; x = edgeTo[x] ){ //go backwards from v to s
            path.push(x);
        }
        path.push(s);
        return path;
    }


    //tester/client/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch dfs = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}

