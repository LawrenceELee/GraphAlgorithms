/** *************************************************
 * Compute topological ordering of a Directed Acylic Graph
 * (Digraph and EWDigraph).
 *
 * Runtime: O(E+V) time.
 *
 * example run: java Topological jobs.txt "/"
 *
 * Applications:
 * analyze graphs with dependencies (course prerequesites, recipe for making a cake)
 * scheduling
 *
 * from Wikipedia:
 * instruction scheduling,
 * ordering of formula cell evaluation when recomputing formula values in spreadsheets,
 * logic synthesis,
 * determining the order of compilation tasks to perform in makefiles,
 * data serialization,
 * and resolving symbol dependencies in linkers.
 *
 * ************************************************** */

class Topological{

    private Iterable<Integer> order;    //topological order

    public Topological(Digraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);

        if( !cyclefinder.hasCycle() ){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean hasOrder(){
        return order != null;
    }

    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Topological topological = new Topological(sg.G());
        for (int v : topological.order()) {
            StdOut.println(sg.name(v));
        }
    }

}
