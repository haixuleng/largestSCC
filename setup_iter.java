package stronglyConnectedComponents;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
public class setup_iter {
	public static void main(String[] args) throws FileNotFoundException{
		// This class called whatever the classses used to compute SCC
		LoadGraph tst = new LoadGraph();
		ArrayList<ArrayList<Integer>> adj = tst.get();
		reverseGraph rv = new reverseGraph(adj);
		ArrayList<ArrayList<Integer>> adjrev = rv.get();
		
		// First loop
		
		Boolean[] visited = new Boolean[adj.size()];
		Boolean[] finished = new Boolean[adj.size()];
		Stack<Integer> unlabeled = new Stack<Integer>();
		int length = adj.size();
		while(length > 0){
			visited[length - 1] = false;
			finished[length - 1] = false;
			length--;
		}
		DFS_iter dps = new DFS_iter(adjrev, visited, finished);
		dps.setF(new int[adj.size()]);
		dps.setLoopTime(0);
		for(int source = adj.size(); source >= 1; source--){
			if(visited[source - 1]){
				continue;
			}
			else{
				dps.DFS(source);
				visited = dps.getVisited(); // updated what nodes have been visited
				finished = dps.getFinished();
				unlabeled = dps.getUnlabeled(); // next node to get labeled
				while(unlabeled.empty() == false){ // deal with nodes that are visited but not finished/labeled
					source = unlabeled.peek();
					dps.DFS(source);
					visited = dps.getVisited(); // updated what nodes have been visited
					finished = dps.getFinished();
					unlabeled = dps.getUnlabeled(); // next node to get labeled
				}
			}
		}
		int[] finishingTime = dps.getF();
		// Print the magical ordering
		/*
		int index = 1;
		for(int t : finishingTime){
			System.out.print("Index " + index + ":");
			System.out.println(t);
			index++;
		}
		*/
		
		// Second loop
		ArrayList<Integer> top5 = new ArrayList<Integer>();
		length = adj.size();
		while(length > 0){ // reset visited array
			visited[length - 1] = false;
			finished[length - 1] = false;
			length--;
		}
		unlabeled = new Stack<Integer>(); // reset the unlabeled stack
		orderedGraph og = new orderedGraph(finishingTime);
		int[] secondDPSLoopOrder = og.get(); // makes sure the 2nd loop is using the order from the first loop
		
		DFS_iter dps2 = new DFS_iter(adj, visited, finished);
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
				dps2.DFS(source);
				visited = dps2.getVisited(); // updated what nodes have been visited
				finished = dps2.getFinished();
				unlabeled = dps2.getUnlabeled(); // next node to get labeled
				while(unlabeled.empty() == false){
					source = unlabeled.peek();
					dps2.DFS(source);
					visited = dps2.getVisited(); // updated what nodes have been visited
					finished = dps2.getFinished();
					unlabeled = dps2.getUnlabeled(); // next node to get labeled
				}
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
