package stronglyConnectedComponents;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Setup {
	public static void main(String[] args) throws FileNotFoundException{
		// This class called whatever the classses used to compute SCC
		LoadGraph tst = new LoadGraph();
		ArrayList<ArrayList<Integer>> adj = tst.get();
		reverseGraph rv = new reverseGraph(adj);
		ArrayList<ArrayList<Integer>> adjrev = rv.get();
		
		// First loop
		
		Boolean[] visited = new Boolean[adj.size()];
		int length = adj.size();
		while(length > 0){
			visited[length - 1] = false;
			length--;
		}
		DepthFirstSearch dps = new DepthFirstSearch(adjrev, visited);
		dps.setF(new int[adj.size()]);
		dps.setLoopTime(0);
		for(int source = adj.size(); source >= 1; source--){
			if(visited[source - 1]){
				continue;
			}
			else{
				dps.DFS_recursive(source);
				visited = dps.getVisited(); // updated what nodes have been visited
			}
		}
		int[] finishingTime = dps.getF();
		// Print the magical ordering
		int index = 1;
		for(int t : finishingTime){
			System.out.print("Index " + index + ":");
			System.out.println(t);
			index++;
		}
		
		
		// Second loop
		ArrayList<Integer> top5 = new ArrayList<Integer>();
		length = adj.size();
		while(length > 0){ // reset visited array
			visited[length - 1] = false;
			length--;
		}
		orderedGraph og = new orderedGraph(finishingTime);
		int[] secondDPSLoopOrder = og.get(); // makes sure the 2nd loop is using the order from the first loop
		DepthFirstSearch dps2 = new DepthFirstSearch(adj, visited);
		dps2.setF(new int[adj.size()]);
		dps2.setLoopTime(0);
		int size = 0;
		int previousT = 0;
		for(int i = 0; i < adj.size(); i++){
			int source = secondDPSLoopOrder[i]; // new order
			if(visited[source - 1]){
				continue;
			}
			else{
				dps2.DFS_recursive(source);
				visited = dps2.getVisited(); // updated what nodes have been visited
				size = dps2.getLoopTime() - previousT;
				previousT = dps2.getLoopTime();
				top5.add(size);
			}
		}
		Collections.sort(top5);
		for(int i : top5){
			System.out.println("SCC size = " + i);
		}
		
	}
}
