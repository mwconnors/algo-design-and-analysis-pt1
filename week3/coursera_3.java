package coursera_3;

import java.io.*;
import java.util.*;

//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;

public class coursera_3 {

	public static void main(String[] args) {
		String fileName = "kargerMinCut.txt";
		int V = 201; //kargerMinCut.txt
		
//		String fileName = "test1.txt";
//		int V = 9; //test1.txt
				
		
		
		String line = null;
		
		
		Graph adjList = new Graph(V);
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Scanner s;
			while ((line = bufferedReader.readLine()) != null) {
				s = new Scanner(line);
				int v = s.nextInt();
				int w;
				while(s.hasNextInt())
				{
					w = s.nextInt();
					adjList.addEdge(v, w);
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
		
		int minCut = -1;
		int numRuns, i;
		numRuns = (V^2) * ((int) Math.log(V));
		for (i = 0; i < numRuns; i++) 
		{
			Graph adjList_copy = new Graph(adjList);
//			System.out.println("***************i: "+ i);
			int cut = contract(adjList_copy);
//			int cut = contract(adjList);	//just for testing
			if (minCut == -1) {
				minCut = cut;
			}
			if (minCut == 0) {
				break;
			}
			if (cut < minCut) {
				minCut = cut;
			}
//			System.out.println("this run's min cut: " + cut);
		}
		minCut = minCut/2;
		System.out.println("Min cut: " + minCut);
		System.out.println("num runs done: " + i);
		System.out.println("Number of runs: " + numRuns);
		
	}

	private static int contract(Graph adjList) {
		int v = adjList.V();
		
		while (v > 2)
		{
			//System.out.println("now, the number of nodes: " + v);
			int e = adjList.E();
			int edge;
			
			if (e > 0) {
				Random rand = new Random();
				edge = rand.nextInt(e);
//				System.out.println(edge);
			}
			else {
//				System.out.println(e);
				return 0;
			}
			int edgeStart = adjList.getEdgeStart(edge);
			int edgeEnd = adjList.getEdgeEnd(edge);
			adjList.merge(edgeStart, edgeEnd);
			v = adjList.V();
		}
		return adjList.E();
	}

}
