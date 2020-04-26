package stronglyConnectedComponents;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
public class LoadText {
	String fileToLoad;
	int length = 0;
	//int[] data;
	public LoadText(String fileName) throws FileNotFoundException {
		fileToLoad = fileName;
		size();
	}
	
	public void size() throws FileNotFoundException {
		int size = 0;
		File myObj = new File(fileToLoad);
		Scanner myReader = new Scanner(myObj);
		int largestIndex = -9999;
		while(myReader.hasNextLine()) {
			String[] data = myReader.nextLine().split("\\s");
			//System.out.println(data);
			int node = Integer.parseInt(data[0]); // first element is always the node index
			if(node > largestIndex){
				largestIndex = node;
			}
		}
		length = largestIndex;
		System.out.println("Length of Input: " + length);
	}
	
	public ArrayList<ArrayList<Integer>> get() throws FileNotFoundException {
		// return an array of arrays
		//size();
		ArrayList<ArrayList<Integer>> initData = new ArrayList<ArrayList<Integer>>(length);
		//System.out.println(length);
		File myObj = new File(fileToLoad);
		Scanner myReader = new Scanner(myObj);
		while(length > 0){ // initialize the graph
			initData.add(new ArrayList<Integer>());
			length--;
		}
		while(myReader.hasNextLine()) {
			String[] data = myReader.nextLine().split("\\s");
			int node = Integer.parseInt(data[0]);
			int pointedToNode = Integer.parseInt(data[1]);
			//System.out.println(node - 1);
			initData.get(node - 1).add(pointedToNode);
		}
		return initData;
	}
}
