/**
 * Creates a general directed graph which can be inherited from
 * to create more specific adjacency structure implementations.
 * 
 * CS 340, Fall 2014, Assignment 01
 * 
 * @author Jesse Dahir-Kanehl
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public abstract class DirectedGraph {

	protected String vertexNames[];
    protected BufferedReader b;
	protected int numNodes;
	protected int inDegree[];
	protected MyQueue<Integer> queue;

	public DirectedGraph(String filename) {
		try {
			b = new BufferedReader(new FileReader(filename));
			numNodes = Integer.parseInt(b.readLine());
		}
		catch (Exception e) {
			System.out.println("Error in file contents or file not found");
		}
		vertexNames = new String[numNodes];
		inDegree = new int[numNodes];
		queue = new MyQueue<>();
	}
		
	// Parses the input to add the nodes and convert 
	// the edges into the appropriate adjacency structure
	protected void buildGraph() {
		try{
			
			for (int i = 0; i < numNodes; i++) {
				vertexNames[i] = b.readLine();
			}
			String edges = b.readLine();
			while(edges != null) {
				String[] split = edges.split("\\s+");
				addEdge(getNodeNum(split[0]), getNodeNum(split[1]));
				edges = b.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Error in file contents or file not found");
		}
	}

	protected int getNodeNum(String v) {
		for (int i = 0; i < numNodes; i++)
			if (vertexNames[i].equals(v))
				return i;
		return -1;
	}
			

	public abstract void addEdge(int v1, int v2);

	public abstract String topo();
}