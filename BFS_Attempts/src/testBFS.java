import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class testBFS {

	
	 private static final int INFINITY = Integer.MAX_VALUE;
	    private boolean[] marked;  // marked[v] = is there an s-v path
	    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
	    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

	    /**
	     * Computes the shortest path between the source vertex <tt>s</tt>
	     * and every other vertex in the graph <tt>G</tt>.
	     * @param G the graph
	     * @param s the source vertex
	     */
	    public testBFS(Graph G, int s) {
	        marked = new boolean[G.V()];
	        distTo = new int[G.V()];
	        edgeTo = new int[G.V()];
	        bfs(G, s);

	        assert check(G, s);
	    }
	    
	    

	    // breadth-first search from a single source
	    private void bfs(Graph G, int s) {
	        Queue<Integer> q = new Queue<Integer>();
	        for (int v = 0; v < G.V(); v++)
	            distTo[v] = INFINITY;
	        distTo[s] = 0;
	        marked[s] = true;
	        q.enqueue(s);

	        while (!q.isEmpty()) {
	            int v = q.dequeue();
	            for (int w : G.adj(v)) {
	                if (!marked[w]) {
	                    edgeTo[w] = v;
	                    distTo[w] = distTo[v] + 1;
	                    marked[w] = true;
	                    q.enqueue(w);
	                }
	            }
	        }
	    }

	    // breadth-first search from multiple sources
	    private void bfs(Graph G, Iterable<Integer> sources) {
	        Queue<Integer> q = new Queue<Integer>();
	        for (int s : sources) {
	            marked[s] = true;
	            distTo[s] = 0;
	            q.enqueue(s);
	        }
	        while (!q.isEmpty()) {
	            int v = q.dequeue();
	            for (int w : G.adj(v)) {
	                if (!marked[w]) {
	                    edgeTo[w] = v;
	                    distTo[w] = distTo[v] + 1;
	                    marked[w] = true;
	                    q.enqueue(w);
	                }
	            }
	        }
	    }

	    /**
	     * Is there a path between the source vertex <tt>s</tt> (or sources) and vertex <tt>v</tt>?
	     * @param v the vertex
	     * @return <tt>true</tt> if there is a path, and <tt>false</tt> otherwise
	     */
	    public boolean hasPathTo(int v) {
	        return marked[v];
	    }

	    /**
	     * Returns the number of edges in a shortest path between the source vertex <tt>s</tt>
	     * (or sources) and vertex <tt>v</tt>?
	     * @param v the vertex
	     * @return the number of edges in a shortest path
	     */
	    public int distTo(int v) {
	        return distTo[v];
	    }

	    /**
	     * Returns a shortest path between the source vertex <tt>s</tt> (or sources)
	     * and <tt>v</tt>, or <tt>null</tt> if no such path.
	     * @param v the vertex
	     * @return the sequence of vertices on a shortest path, as an Iterable
	     */
	    public Iterable<Integer> pathTo(int v) {
	        if (!hasPathTo(v)) return null;
	        Stack<Integer> path = new Stack<Integer>();
	        int x;
	        for (x = v; distTo[x] != 0; x = edgeTo[x])
	            path.push(x);
	        path.push(x);
	        return path;
	    }
	    
	    // check optimality conditions for single source
	    private boolean check(Graph G, int s) {

	        // check that the distance of s = 0
	        if (distTo[s] != 0) {
	            StdOut.println("distance of source " + s + " to itself = " + distTo[s]);
	            return false;
	        }

	        // check that for each edge v-w dist[w] <= dist[v] + 1
	        // provided v is reachable from s
	        for (int v = 0; v < G.V(); v++) {
	            for (int w : G.adj(v)) {
	                if (hasPathTo(v) != hasPathTo(w)) {
	                    StdOut.println("edge " + v + "-" + w);
	                    StdOut.println("hasPathTo(" + v + ") = " + hasPathTo(v));
	                    StdOut.println("hasPathTo(" + w + ") = " + hasPathTo(w));
	                    return false;
	                }
	                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
	                    StdOut.println("edge " + v + "-" + w);
	                    StdOut.println("distTo[" + v + "] = " + distTo[v]);
	                    StdOut.println("distTo[" + w + "] = " + distTo[w]);
	                    return false;
	                }
	            }
	        }

	        // check that v = edgeTo[w] satisfies distTo[w] + distTo[v] + 1
	        // provided v is reachable from s
	        for (int w = 0; w < G.V(); w++) {
	            if (!hasPathTo(w) || w == s) continue;
	            int v = edgeTo[w];
	            if (distTo[w] != distTo[v] + 1) {
	                StdOut.println("shortest path edge " + v + "-" + w);
	                StdOut.println("distTo[" + v + "] = " + distTo[v]);
	                StdOut.println("distTo[" + w + "] = " + distTo[w]);
	                return false;
	            }
	        }

	        return true;
	    }

	public static void main(String[] args) {

		Graph g = new Graph(9);

		g.addEdge(1, 3);
		g.addEdge(1, 2);
		//g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 5);
		//g.addEdge(4, 5);
		g.addEdge(5, 6);
		//g.addEdge(5, 3);
		g.addEdge(3, 8);
		//g.addEdge(7, 8);
		g.addEdge(3, 7);
		
		
		int s = 1;

		   BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);

	        for (int v = 0; v < g.V(); v++) {
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