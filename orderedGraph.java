package stronglyConnectedComponents;
import java.util.*;
public class orderedGraph {
	// this class is used after the first loop
	// the graph is organized in reversing order
	// node with the higher value will be called first
	
	int[] newOrder;
	int[] o;
	public orderedGraph(int[] order){
		o = order;
		newOrder = new int[o.length];
		organize();
	}
	
	private void organize(){
		for(int i = 0; i < o.length; i++){
			int finishingTime = o[i]; // finishing time for node i
			//System.out.println("i:" + i + ", length: " + o.length + ", finishing time: " + finishingTime);
			newOrder[o.length - finishingTime] = i + 1; // small finishing time will be moved to the back
		}
	}
	
	public int[] get(){
		//for(int i = 0; i < newOrder.length; i++){
		//	System.out.println(newOrder[i]);
		//}
		return newOrder;
	}
}
