/**
 * 
 */
package stronglyConnectedComponents;

import java.io.FileNotFoundException;
/**
 * @author lengh
 * It loads the text file into a graph
 */
import java.util.*;
public class LoadGraph {
	ArrayList<ArrayList<Integer>> adj;
	public LoadGraph() throws FileNotFoundException{
		//LoadText ld = new LoadText("data/SCC.txt");
		//String fileName = "class";
		//String fileName = "input_mostlyCycles_65_320000";
		String fileName = "SCC";
		//String fileName = "test1";
		System.out.println("SCC input file: " + fileName);
		LoadText ld = new LoadText("data/" + fileName + ".txt");
		adj = ld.get();
	}
	
	public ArrayList<ArrayList<Integer>> get(){
		return adj;
	}
}
