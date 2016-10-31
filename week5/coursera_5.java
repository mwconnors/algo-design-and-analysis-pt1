import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class coursera_5 {
	
	public static EdgeWeightedGraph createGraphFromTxt(String fileName) {
		EdgeWeightedGraph g = new EdgeWeightedGraph(200);
		
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Scanner s;
			while ((line = bufferedReader.readLine()) != null) {
				s = new Scanner(line);
				int start = s.nextInt();
				int dest, weight;
				while(s.hasNext())
				{
					String temp = s.next();
					String delims = "[,]";
					String[] tokens = temp.split(delims);
					dest = Integer.parseInt(tokens[0]);
					weight = Integer.parseInt(tokens[1]);
					g.addEdge(start, dest, weight);
				}
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file "+fileName);
		}
		catch (IOException ex) {
			System.out.println("Error reading file");
		}
		return g;
	}

	public static void main(String[] args) {
		EdgeWeightedGraph g = createGraphFromTxt("dijkstraData.txt");
		//EdgeWeightedGraph g = createGraphFromTxt("test1.txt");
		System.out.println("vertices, edges: " + g.getV() + ", " + g.getE());
		
		//Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes.
		//Set the initial node as current. Mark all other nodes unvisited. Create a set of all the unvisited nodes called the unvisited set.	
		
		EdgeWeightedGraph visited = new EdgeWeightedGraph(g.getV());
		
		int s = 1; //source
		
		int A[] = new int[g.getV() + 1];
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.MAX_VALUE;
		}
		A[s] = 0;
//		int[] min = new int[10];
//		for (int i = 0; i < min.length; i++) {
//			A[i] = 0;
//		}
		
		LinkedList<Edge> curr = g.getVertex(s);
//		visited.addVertex(curr);
		
		//For the current node, consider all of its unvisited neighbors and calculate their tentative distances. 
		//Compare the newly calculated tentative distance to the current assigned value and assign the smaller one. 
		//For example, if the current node A is marked with a distance of 6, and the edge connecting it with a neighbor 
		//B has length 2, then the distance to B (through A) will be 6 + 2 = 8. 
		//If B was previously marked with a distance greater than 8 then change it to 8. 
		//Otherwise, keep the current value.
		int next = 1;
		while (next != 0) {
			for (Edge e : curr) {
				int dest = e.getDest();
				int weight = e.getWeight();
				if (!visited.contains(dest) && (A[dest] == Integer.MAX_VALUE || A[dest] > (A[s] + weight))) {
					A[dest] = A[s] + weight;
				}
			}
			//When we are done considering all of the neighbors of the current node, 
			//mark the current node as visited and remove it from the unvisited set. 
			//A visited node will never be checked again.
			visited.addVertex(s, curr);
			
			//Otherwise, select the unvisited node that is marked with the smallest tentative 
			//distance, set it as the new "current node", and go back to step 3.
			next = 0;
			for (int i = 0; i < A.length; i++) {
				if (!visited.contains(i) && (next == 0 || A[i] < A[next]))
					next = i;
			}
			s = next;
			curr = g.getVertex(s);
		}
//7,37,59,82,99,115,133,165,188,197
		System.out.println(A[7]);
		System.out.println(A[37]);
		System.out.println(A[59]);
		System.out.println(A[82]);
		System.out.println(A[99]);
		System.out.println(A[115]);
		System.out.println(A[133]);
		System.out.println(A[165]);
		System.out.println(A[188]);
		System.out.println(A[197]);
		
		//If the destination node has been marked visited 
		//(when planning a route between two specific nodes) 
		//or if the smallest tentative distance among the nodes in the unvisited set is infinity
		//(when planning a complete traversal; 
		//occurs when there is no connection between the initial node and remaining unvisited nodes),
		//then stop. The algorithm has finished.

	}

}
