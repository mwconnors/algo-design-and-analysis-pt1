import java.util.*;
import java.util.zip.ZipFile;
import java.io.*;

public class coursera_4 {

	public static int t; // finishing times
	public static int s; // leaders
	public static Stack<Integer> DFS_stack;
	public static Stack<Integer> f_stack;
	public static int pass;
	public static ArrayList<Integer> scc;
	public static int currSCCsize;
	
	public static void SCC(Graph g) {
		System.out.println("Currenty on pass " + pass);
		t = 0;
		DFS_stack = new Stack<Integer>();
//		pass = 0;
		scc = new ArrayList<Integer>();
		currSCCsize = 0;
		int V = g.V();
		LinkedList<Integer>[] edgeList;
		
		if (pass == 0) {
			edgeList = g.getReverse();
		}
		else {
			g.reset();
			edgeList = g.getFTime();
		}
		// Run first DFS loop to determine the visit order
		for (int i = V; i > 0; i--){
			if (!g.isExplored(i)) {
				DFS_stack.push(i);
				g.setExplored(i);				
			}
			while (!DFS_stack.isEmpty()) {
				int vertex = DFS_stack.peek();
				DFS(g, edgeList, vertex);
			}
			if (pass == 1) {
				scc.add(currSCCsize);
				currSCCsize = 0;
			}
		}
		pass++;

	}
	
	private static void DFS(Graph g, LinkedList<Integer>[] edgeList, int vertex) {
		LinkedList<Integer>[] forwardList = g.getForward();
		int[] f = g.getFinishingTimes();
		int edgeFromVertex = 0;
		
		// put unexplored child node on DFS_stack
		for (int i = 0; i < edgeList[vertex].size(); i++) {
			if (pass == 0) {
				edgeFromVertex = edgeList[vertex].get(i);
			}
			else if (pass == 1) {
				edgeFromVertex = f[edgeList[vertex].get(i)];
			}
			if (!g.isExplored(edgeFromVertex)) {
				g.setExplored(edgeFromVertex);
				DFS_stack.push(edgeFromVertex);
				return;
			}
				
			//WORKING
//			if (!g.isExplored(edgeList[vertex].get(i))) {
//				g.setExplored(edgeList[vertex].get(i));
//				DFS_stack.push(edgeList[vertex].get(i));
//				return;
//			}
				
				//NOT WORKING
//			if (pass == 1) {
//				int edgeFromVertex = f[edgeList[vertex].get(i)];
//				System.out.println("original edge end: " + edgeList[vertex].get(i));
//				System.out.println("new edge end: " + f[edgeList[vertex].get(i)]);
//				if (!g.isExplored(edgeFromVertex)) {
//					g.setExplored(edgeList[vertex].get(i));
//					DFS_stack.push(edgeList[vertex].get(i));
//					return;
//				}
//			}
		}
		// if no child nodes, set finishing time and increment finishing time counter t
		// then pop off DFS_stack
		if (pass == 0) {
//			g.addToFTime(edgeList[vertex], t);
			g.setf(vertex, (t+1));
			g.addToFTime(forwardList[vertex], t);
			t++;
		}
		if (pass == 1) {
			currSCCsize++;
		}
		DFS_stack.pop();
		
	}

	public static void main(String[] args) throws IOException {
		
//		String fileName = "test1.txt";
//		Graph g = new Graph(9);
//		
		String fileName = "scc.txt";
		Graph g = new Graph(875714);
		
		String line = null;

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
					g.addEdge(v, w);
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
		pass = 0;
		SCC(g); //pass 1
		SCC(g); //pass 2
		
		// sort array of scc sizes
		Collections.sort(scc);
		//print largest 5
		int count = 0;
		for( int i = scc.size()-1; i >= 0; i-- ) {
			if( count >= 5 ) break;
	        System.out.println("ssc:" + scc.get( i ));
	        count++;
		}

	}
	

}
