/** *************************************************
 * Algorithm to check if a unweighted digraph has a cycle.
 *
 * Is some digraph G a Directed Acyclic Graph (DAG)?
 * If not a DAG, then return the first cycle it finds.
 *
 * Runtime: O(E+V)
 *
 * ************************************************** */

class DirectedCycle{
    private boolean[]       marked;
    private int[]           edgeTo;
    private boolean[]       onStack;
    private Stack<Integer>  cycle;

    public DirectedCycle(Digraph G){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];

        for( int s=0; s < G.V(); ++s ){
            if( !marked[s] ){
                dfs(G, s);
            }
        }
    }

    private void dfs(Digraph G, int v){
        onStack[v] = true;
        marked[v] = true;

        for( int w: G.adj(v) ){
            if      (cycle != null){    //short circuit if cycle found
                return;
            }
            //found new node, so run regular dfs
            else if (!marked[w]){       
                edgeTo[w] = v;
                dfs(G, w);
            }
            //seen this node before, trace back cycle
            else if (onStack[w] ){      
                cycle = new Stack<>();
                for( int x=v; x != w; x = edgeTo[x] ){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;     //done dfs'ing on v, so pop off the stack
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

    //tester/driver
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No cycle");
        }
    }

}


