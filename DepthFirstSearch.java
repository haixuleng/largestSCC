package stronglyConnectedComponents;
import java.util.*;
public class DepthFirstSearch {
	// This class implements the method used in the video
	int s;
	ArrayList<ArrayList<Integer>> G;
	Boolean[] visited;
	int loopTime; // this value is used to determine running order in SCC
	int[] f; // this array used to store the loopTime for each Node
	
	public DepthFirstSearch(ArrayList<ArrayList<Integer>> graph, Boolean[] visitedArray){
		G = graph;
		visited = visitedArray;
		loopTime = 0;
		f = new int[G.size()];
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
	
	public void DFS_recursive(int i){
		visited[i - 1] = true; // mark s as visited
		ArrayList<Integer> connectedNodes = G.get(i - 1);
		//System.out.println(i);
		for(int j : connectedNodes){
			if(visited[j - 1]){
				continue;
			}
			else{
				DFS_recursive(j);
			}
		}
		// when nodes have been exhausted
		loopTime++;
		f[i - 1] = loopTime;
	}
	
	public void DFS_iter(int i){
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> finishingTime = new Stack<>();
		if(!visited[i - 1]){
			stack.push(i);
			finishingTime.push(i);
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
                    finishingTime.push(v);
                }
            }
		}
		loopTime++;
		f[node - 1] = loopTime;
		finishingTime.pop();
		
		while(finishingTime.empty() == false){
			node = finishingTime.peek();
			for(int v : G.get(node -1)){
            	if(!visited[v - 1]){
            		visited[node - 1] = false;
            		DFS_iter(node);
            	}
            }
			loopTime++;
			f[node - 1] = loopTime;
			finishingTime.pop();
		}
	}
}
