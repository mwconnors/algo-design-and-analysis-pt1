import java.util.*;

public class EdgeWeightedGraph {
	private LinkedList<Edge>[] graph;
	private int V;
	private int E;
//	private int currentV;

	public EdgeWeightedGraph(int V) {
		this.V = V;
		E = 0;
//		currentV = 1;
		graph = (LinkedList<Edge>[]) new LinkedList[V + 1];
		for (int i = 0; i <= V; i++) {
			graph[i] = new LinkedList<Edge>();
		}
	}

	public int getV() {
		return this.V;
	}
	
	public int getE() {
		return this.E;
	}
	
	public LinkedList<Edge> getVertex(int start) {
		return graph[start];
	}
	public boolean contains(int start) {
		if (graph[start].size() == 0) {
			return false;
		}
		return true;
	}
	
	public void addEdge(int start, int dest, int weight) {
		E++;
		Edge e = new Edge(dest, weight);
		graph[start].add(e);
//		if (currentV > start)
//			currentV = start;
	}
	public void addVertex(int start, LinkedList<Edge> vertex) {
		graph[start] = vertex;
//		currentV++;
	}
	
}
