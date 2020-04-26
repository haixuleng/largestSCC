package stronglyConnectedComponents;

import java.io.FileNotFoundException;
import java.util.*;

public class testLoadGraph {
	public static void main(String[] args) throws FileNotFoundException{
		LoadGraph tst = new LoadGraph();
		ArrayList<ArrayList<Integer>> adj = tst.get();
		ArrayList<Integer> node0 = adj.get(0);
		System.out.println("Nodes connected to node 1");
		for(int number : node0){
			System.out.print(" " + number);
		}
		System.out.println(" ");
		node0 = adj.get(875714 - 1);
		System.out.println("Nodes connected to node 875714");
		for(int number : node0){
			System.out.print(" " + number);
		}
	}
}
