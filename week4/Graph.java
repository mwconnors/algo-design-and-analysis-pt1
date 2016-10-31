import java.util.*;
import java.io.*;

public class Graph {

	private LinkedList<Integer>[] forward, reverse, fTime;
	private int[] f;
	private boolean[] explored;
	private int V;
	
	public Graph(int V) throws IOException {
		this.V = V;
		explored = new boolean[V + 1];
		f = new int[V + 1];
		forward = (LinkedList<Integer>[]) new LinkedList[V + 1];
		reverse = (LinkedList<Integer>[]) new LinkedList[V + 1];
		fTime = (LinkedList<Integer>[]) new LinkedList[V + 1];
		for (int i = 0; i < (V + 1); i++) 
		{
			explored[i] = false;
			forward[i] = new LinkedList<Integer>();
			reverse[i] = new LinkedList<Integer>();
		}
	}
	
	int V(){
		return this.V;
	}
	
	void addEdge(int v, int w)
	{
			forward[v].add(w);
			reverse[w].add(v);
	}
	
	boolean isExplored(int n) {
		return explored[n];
	}

	void setExplored(int n) {
		explored[n] = true;
	}
	
	void setf(int n, int t) {
		f[n] = t;
	}
	
	int getf(int n) {
		return f[n];
	}
	
	int[] getFinishingTimes() {
		return this.f;
	}

	LinkedList<Integer>[] getReverse() {
		return this.reverse;
	}

	LinkedList<Integer>[] getForward() {
		return this.forward;
	}

	void addToFTime(LinkedList<Integer> vertex, int t) {
		fTime[t + 1] = vertex;
		
	}

	LinkedList<Integer>[] getFTime() {		
		return this.fTime;
	}

	void reset() {
		explored = new boolean[V + 1];
		
	}
	
}
