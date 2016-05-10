/* StrongConnectivity.java
   CSC 225 - Spring 2016
   Template for a Strong Connectivity Test
   
   Modify the code below to tests whether the provided graph is strongly connected. 
	Your implementation must run in O(n^2) time on a graph with n vertices.

	This template includes some testing code to help verify the implementation.
   Input graphs can be provided with standard input or read from a file.

   
   To provide test inputs with standard input, run the program with
	java StrongConnectivity
   To terminate the input, use Ctrl-D (which signals EOF).
   
   To read test inputs from a file (e.g. graphs.txt), run the program with
    java StrongConnectivity graphs.txt
	
   The input format for both methods is the same. Input consists
   of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>   
   
   An input file can contain an unlimited number of graphs; each will be processed separately.
   
   B. Bird - 10/30/2012    A. Webster 11/03/2016
*/
//Kenny Chen
//V00825715
import java.util.Scanner;
import java.io.File;

public class StrongConnectivity{


	/* strongly_Connected(G)
		If G[i][j] == 0, there is no edge between vertex i and vertex j

		If G[i][j] == 1, there is a directed edge from i to j.

		No entry of G will have a value other than 0 or 1.

	*/
	static boolean strongly_Connected(int[][] G){

		//Get the number of vertices in G
		int n = G.length;

		
		/* ... Your code here ... */
		
		boolean [] covered = new boolean[n];
		int v = 1;
		if(DFS(G,covered,v,n)<n){
			return false;
		}
		covered = new boolean[n];
		if(DFSTranspose(G,covered,v,n)<n){
			return false;
		}
		return true;
		
	}
	static int DFS(int[][] G, boolean[] covered, int current, int n){

		covered[current] = true;
		int totalCovered = 1;
		for(int i = 0; i <n; i++){
			if(G[current][i] == 1){
				if(covered[i]==true){
					continue;
				}
				totalCovered = totalCovered + DFS(G,covered,i,n);
			}
		}
		return totalCovered;
		
	}
	static int DFSTranspose(int[][] G, boolean[] covered, int current, int n){

		covered[current] = true;
		int totalCovered = 1;
		for(int i = 0; i <n; i++){
			if(G[i][current] == 1){
				if(covered[i]==true){
					continue;
				}
				totalCovered = totalCovered + DFSTranspose(G,covered,i,n);
			}
		}
		return totalCovered;
		
	}


	public static void main(String[] args){
		/* Code to test your implementation */
		/* You may modify this, but nothing in this function will be marked */

		int graphNum = 0;
		Scanner s;

		if (args.length > 0){
			//If a file argument was provided on the command line, read from the file
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			//Otherwise, read from standard input
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(!s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				G[i] = new int[n];
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}

			if (strongly_Connected(G))

				System.out.printf("Graph %d: Strongly Connected.\n",graphNum);

			else

				System.out.printf("Graph %d: Not strongly connected.\n",graphNum);
		}
	}

}