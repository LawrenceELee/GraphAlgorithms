/** *************************************************
 * Computes pre, in, and post ordering for a graph.
 *
 * ************************************************** */

class DepthFirstOrder{
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    private boolean[] marked;
    private int[]     edgeTo;

    //private Queue<Integer> in; 
    //in order makes sense for trees, but doesn't make sense for a graph.
    //usually inorder would be:
    //explore(node.left);
    //inorder(node);
    //explore(node.right);
    //but there isn't this symmetry for a graph.

    public DepthFirstOrder(Digraph G){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for( int s=0; s < G.V(); ++s ){
            if( !marked[s] ){
                dfs(G, s);
            }
        }
    }

    private void dfs(Digraph G, int v){
        pre.enqueue(v);          //pre-ordering is alwasy before rec call.
        marked[v] = true;

        for( int w: G.adj(v) ){
            if( !marked[w] ){
                dfs(G, w);
            }
        }

        post.enqueue(v);        //post-ordering is alwasy after rec call.
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){ return pre; }
    public Iterable<Integer> post(){ return post; }
    public Iterable<Integer> reversePost(){ return reversePost; }


}

