Graph data structures and algorithms

* Unweighted, undirected graphs (implicit edges)
  * Depth first search
    * Computes reachability from a source s to a target t.
    * Computes connectivity (connected components) of a graph.
    * Graph traversal/exploration
    * Can also be used to find shortest path between 2 nodes.
    * Bad for really big graphs (like the interest) because it will just 
      continue down the rabbit-hole (like clicking through wikipedia articles)
      (http://xkcd.com/214/)
  * Breadth first search, runtime O(|V|+|E|)
    * Can compute shortest path for unweight, undirected graphs
    * Testing for bipartitness
    * Good for if you know the target node is "nearby"
    * Like depth first search, BFS traverse a connected component 
      of a given graph and defines a spanning tree.
  * Connected componenet
* Unweighted, directed graphs (implicit edges)
  * Depth first search
  * Breadth first search
  * Strongly Connected componenet
  * Topological sort
  * Cycle detection
* Weighted, undirected graphs (explicit edges)
  * Minimum spanning tree
    * Prim
    * Kruskal
* Weighted, directed graphs (explicit edges)
  * Shortest path
    * Dijkstra (no negative weights)
    * Bellman-Ford (handles negative weights)

Note:

Typically, adj list are used more than adj matrix.

However:
  * For sparse graphs (few edges relative to nodes), use adj list.
  * For dense graphs (many edges), use adj matrix. Since # of edges will be
    close to the size of the matrix anyway.

If memory is a concern, use adj list.
If speed/fast lookup is a concern, use adj matrix.

If density (|E|/|V|^2) > 1/64, then use matrix.
(src: http://stackoverflow.com/a/5419933)

