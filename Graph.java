package coursera_3;

import java.util.*;

public class Graph {
	
	private LinkedList<Integer>[] adj;
	private Integer V, E;
	
	public Graph(int V)
	{
		this.V = V - 1;
		this.E = 0;
		adj = (LinkedList<Integer>[]) new LinkedList[V];
		for (int i = 0; i < V; i++) 
		{
			adj[i] = new LinkedList<Integer>();
		}
	}
	

	public Graph(Graph G) {
		this.V = G.V;
		this.E = G.E;
		this.adj = (LinkedList<Integer>[]) new LinkedList[V + 1];
		for (int i = 0; i <= V; i++) 
		{
			adj[i] = new LinkedList<Integer>();
		}
		for (int v = 0; v <= G.V(); v++) 
		{
//			System.out.println("outer loop");
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) 
			{
//				System.out.println("inner loop 1");
				reverse.push(w);
			}
			for (int w : reverse) 
			{
//				System.out.println("inner loop 2");
				this.adj[v].add(w);
			}
		}
	}
	
	Integer V() 
	{
		return V;
	}
	Integer E() 
	{
		return E;
	}
	void addEdge(int v, int w)
	{
			E++;
			adj[v].add(w);
	}

	Integer getEdgeStart(int edge) {
		int edgeCounter = 0;
		int v = -1;
		for (int i = 1; i < adj.length; i++) {
//			System.out.println(adj[i]);
			edgeCounter += adj[i].size();
//			System.out.println("edge, and edgeCounter: " + edge+", "+ edgeCounter);
			if (edgeCounter > edge)
			{
				v = i;
//				System.out.println("edge start should be: " + v);
				break;
			}
		}
		return v;
	}
	Integer getEdgeEnd(int edge) {
		int edgeCounter = 0;
		int w = -1;
		for (int i = 1; i < adj.length; i++) {
			edgeCounter += adj[i].size();
			if (edgeCounter > edge)
			{
				w = adj[i].get(adj[i].size() - (edgeCounter - edge));
				break;
			}
		}
		return w;
	}

	void merge(int edgeStart, int edgeEnd) {
		V--;
//		System.out.println("Edge Start -> Edge End: " + edgeStart + "->"+edgeEnd);
		// delete all instances of edgeEnd from edgeStart's list
		for (int i = 0; i < adj[edgeStart].size(); i++)
		{
			if (adj[edgeStart].get(i) == edgeEnd) 
			{
				adj[edgeStart].remove(i);
				E--;
				i--;
			}
		}
		// delete all instances of edgeStart from edgeEnd's list
		for (int i = 0; i < adj[edgeEnd].size(); i++) {
			int temp = adj[edgeEnd].get(i);
			if (temp == edgeStart)
			{
				adj[edgeEnd].remove(i);
				E--;
				i--;
			}
			else 
			{
				adj[edgeEnd].remove(i);
				adj[edgeStart].add(temp);
				for (int j = 0; j < adj[temp].size(); j++)
				{
					if (adj[temp].get(j) == edgeEnd) {
						adj[temp].set(j, edgeStart);
						break;
					}
				}
				i--;
			}
		}
		// append edgeEnd's list one by one, replace the other vertice's reference to 
		// edgeEnd with edgeStart
	}

	void delete(int edgeEnd) {
		// re-adjust the number of edges
		E -= adj[edgeEnd].size();
		System.out.println("re-adjusting num edges: " + E);
		// re-adjust the number of vertices (just subtract 1)
		V -= 1;
		// delete the node edgeEnd in the linked list
		adj[edgeEnd].remove();
		// re-adjust node numbers
	}
	
}
