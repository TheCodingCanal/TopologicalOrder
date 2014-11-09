/**
 * Implements the specific adjacency matrix in order to create
 * a topological order.
 * 
 * CS 340, Fall 2014, Assignment 01
 * 
 * @author Jesse Dahir-Kanehl
 *
 */

public class AdjMatrixGraph extends DirectedGraph {
	protected int g[][];

	public AdjMatrixGraph(String filename) { 
		super(filename);
		g = new int[numNodes][numNodes];
		buildGraph();
	}

	public void addEdge(int v1, int v2) {
		g[v1][v2] = 1;
		inDegree[v2]++;
	}
	
	
	// Creates a topological ordering of the adjacency matrix
	// unless it is found to be acyclic
	public String topo() {
		int value = 0;
		while (value == 0) {
			value = 0;
			for (int i = 0; i < numNodes; i++) {
				if(inDegree[i] == 0){
					for (int j = 0; j < numNodes; j++) {
						if (g[i][j] == 1)
							inDegree[j]--;
					}
					queue.enqueue(i);
					inDegree[i] = -1;
					value = 0;
					break;
				}
				else {
					value += inDegree[i];
					if (value == 0)
						value = 99;
				}
				
			}
		}
		if (value == -1*numNodes) {
			String str = " ";
			while (!queue.empty()) {
				int vertex = queue.serve();
				String vertexName = vertexNames[vertex];
				str = str + vertexName + " ";
			}
			return str;
		}
		else {
			return " No topological order found";
		}
	}
}
