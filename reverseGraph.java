/**
 * 
 */
package stronglyConnectedComponents;
import java.util.*;
/**
 * @author lengh
 * Reverse the graph for the SCC algorithms
 */
public class reverseGraph {
	ArrayList<ArrayList<Integer>> G;
	ArrayList<ArrayList<Integer>> Grev;
	public reverseGraph(ArrayList<ArrayList<Integer>> graph){
		G = graph;
		Grev = new ArrayList<ArrayList<Integer>>(G.size()); // initialize the reverse graph
		init();
		reverse();
	}
	
	private void reverse(){
		int vertex = 1; // start with vertex 1
		for(ArrayList<Integer> edges : G){
			for(int node: edges){
				Grev.get(node - 1).add(vertex); // this node should connect to vertex 
			}
			vertex++;
		}
	}
	
	private void init(){
		int length = G.size();
		while(length > 0){
			Grev.add(new ArrayList<Integer>());
			length--;
		}
	}
	
	public ArrayList<ArrayList<Integer>> get(){
		return Grev;
	}
	
}
