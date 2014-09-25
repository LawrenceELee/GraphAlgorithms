/** *************************************************
 * Directed edge objects for edge weighted digraphs.
 *
 * ************************************************** */

class DirectedEdge{
    private final int         v;
    private final int         w;
    private final double      weight;

    public DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }

    //tester/driver
    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 23, 3.14);
        StdOut.println(e);
    }

}
