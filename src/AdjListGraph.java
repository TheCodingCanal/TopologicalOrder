/**
 * Implements the specific adjacency list in order to create
 * a topological order.
 * 
 * CS 340, Fall 2014, Assignment 01
 * 
 * @author Jesse Dahir-Kanehl
 *
 */

import java.util.ArrayList;


public class AdjListGraph extends DirectedGraph {
	protected ArrayList<DoubleLinkedList<Integer>> g;
	

	public AdjListGraph(String filename) {
		super(filename);
		g = new ArrayList<DoubleLinkedList<Integer>>();
		for (int i = 0; i < numNodes; i++)
			g.add(new DoubleLinkedList<Integer>());
		buildGraph();
		
	}


	public void addEdge(int v1, int v2) {
		g.get(v1).insertLast(v2);
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
					while(g.get(i).getSize() != 0) {
						inDegree[g.get(i).deleteFirst()]--;
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
