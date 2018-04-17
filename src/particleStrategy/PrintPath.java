package particleStrategy;

import java.util.ArrayList;
import java.util.List;

public class PrintPath {

	private String path="";
	private int[] passer;
	
	public PrintPath(List<int[]> path){
		this.path += "X  " + "  Y\n";
		for(int i=0;i<path.size();i++){
			for(int j=0;j<path.get(i).length;j++){
				this.path+=Integer.toString(path.get(i)[j]) + " ";
			}
			this.path+="\n";
		}
		this.path+="\n";
	}
	
	public String toString(){
		return path;
	}
	
}
