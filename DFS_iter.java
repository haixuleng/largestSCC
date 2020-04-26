package stronglyConnectedComponents;

import java.util.ArrayList;
import java.util.Stack;

public class DFS_iter {
	// This class implements the method used in the video
	int s;
	Stack<Integer> unlabeled;
	ArrayList<ArrayList<Integer>> G;
	Boolean[] visited;
	Boolean[] finished;
	int loopTime; // this value is used to determine running order in SCC
	int[] f; // this array used to store the loopTime for each Node
	
	public DFS_iter(ArrayList<ArrayList<Integer>> graph, Boolean[] visitedArray, Boolean[] finishedArray){
		G = graph;
		visited = visitedArray;
		finished = finishedArray;
		loopTime = 0;
		f = new int[G.size()];
		unlabeled = new Stack<Integer>();
	}
	
	public void setLoopTime(int t){
		loopTime = t;
	}
	
	public int getLoopTime(){
		return loopTime;
	}
	
	public void setF(int[] ft){
		f = ft;
	}
	
	public int[] getF(){
		return f;
	}
	
	public Boolean[] getVisited(){
		return visited;
	}
	
	public Boolean[] getFinished(){
		return finished;
	}
	
	public Stack<Integer> getUnlabeled(){
		return unlabeled;
	}
	
	public void DFS(int i){
		Stack<Integer> stack = new Stack<>();
		if(!visited[i - 1]){
			stack.push(i);
			unlabeled.push(i);
		}
		else if(!finished[i - 1]){
			stack.push(i); // node visited yet finished
		}
		//System.out.println(stack.peek());
		int node = 0;
		while(stack.empty() == false){
			// Pop a vertex from stack and print it 
            node = stack.peek(); 
            //System.out.println(node);
            stack.pop();
            if(!visited[node - 1]){
            	visited[node - 1] = true;
            }
            for(int v : G.get(node -1)){
            	if(!visited[v - 1]){ 
                    stack.push(v);
                    unlabeled.push(v);
                }
            }
		}
		node = unlabeled.peek();
		unlabeled.pop();
		if(!finished[node - 1]){ // makes sure the time will not be over counted
			loopTime++;
			f[node - 1] = loopTime;
			finished[node - 1] = true;
		}
	}
}
