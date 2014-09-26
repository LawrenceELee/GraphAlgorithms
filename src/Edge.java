/** *************************************************
 * Edge objects to be stored in adj list for edge weighted graphs.
 *
 * ************************************************** */

class Edge implements Comparable<Edge>{
    //the impl Com<Edge> part means that Edge objects can only be 
    //compared with other Edge objects (bound genereics)

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either(){
        return v;
    }

    public int other(int vertex){
        if      (vertex == v)   return w;
        else if (vertex == w)   return v;
        else    throw new IllegalArgumentException("Illegal endpoint");
    }

    public double weight(){
        return weight;
    }

    public int compareTo(Edge that){
        if      (this.weight() < that.weight())     return -1;
        else if (this.weight() > that.weight())     return  1;
        else                                        return  0;
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    //tester/driver
    public static void main(String[] args) {
        Edge e = new Edge(12, 23, 3.14);
        StdOut.println(e);
    }

}
