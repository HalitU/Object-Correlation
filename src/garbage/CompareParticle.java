/*
 * 
 * 
 * NOT USED *
 * 
 * 
 */

package garbage;

import java.util.ArrayList;
import java.util.List;

public class CompareParticle {
	List<int[]> path1 = new ArrayList<int[]>();
	List<int[]> path2 = new ArrayList<int[]>();
	double[][] xMatrix;
	double[][] yMatrix;
	double[][] pasMatrix;
	int[][] oneMatrix;
	double[][] finalMatrix;
	double[][] yFinalMatrix;
	
	public CompareParticle(List<int[]> path1,List<int[]> path2){
		this.path1=path1;
		this.path2=path2;
	}
	
	public void comparePath(){
		xMatrix = new double[path1.size()][2];
		yMatrix = new double[path2.size()][2];
		oneMatrix = new int[path1.size()][path1.size()];
		pasMatrix = new double[path1.size()][2];
		finalMatrix = new double[2][2];
		yFinalMatrix = new double[2][2];
		
		//initialize matrices
		for(int i=0;i<path1.size();i++){
			xMatrix[i][0]=path1.get(i)[0];
		}
		for(int i=0;i<path2.size();i++){
			xMatrix[i][1]=path2.get(i)[0];
		}
		for(int i=0;i<path1.size();i++){
			yMatrix[i][0]=path1.get(i)[1];
		}
		for(int i=0;i<path2.size();i++){
			yMatrix[i][1]=path2.get(i)[1];
		}
		for(int i=0;i<oneMatrix.length;i++){
			for(int j=0;j<oneMatrix.length;j++){
				oneMatrix[i][j]=1;
			}
		}
		
		//get covatiance matrix for y variables
		for (int i=0 ; i < oneMatrix.length; i++ ){
	           for (int j=0 ; j < yMatrix[0].length ; j++ ){   
	              for (int k=0 ; k < oneMatrix[0].length ; k++ ){
	            	  pasMatrix[i][j]=pasMatrix[i][j]+oneMatrix[i][k] * yMatrix[k][j]/oneMatrix.length;
	              }
	           }
	    }
		
		for(int i=0;i<yMatrix[0].length;i++){
			for(int j=0;j<yMatrix.length;j++){
				pasMatrix[j][i]=yMatrix[j][i]-pasMatrix[j][i];
			}
		}
		
		for(int i=0;i < pasMatrix[0].length;i++){
			for(int j=0;j< pasMatrix[0].length;j++){
				for(int k=0; k < pasMatrix.length;k++){
					yFinalMatrix[i][j] = yFinalMatrix[i][j] + pasMatrix[k][i] * pasMatrix[k][j]/oneMatrix.length; 
				}
			}
		}
		
		//get covariance matrix for x variables
		for (int i=0 ; i < oneMatrix.length; i++ ){
           for (int j=0 ; j < xMatrix[0].length ; j++ ){   
              for (int k=0 ; k < oneMatrix[0].length ; k++ ){
            	  pasMatrix[i][j]=pasMatrix[i][j]+oneMatrix[i][k] * xMatrix[k][j]/oneMatrix.length;
              }
           }
        }
		
		for(int i=0;i<xMatrix[0].length;i++){
			for(int j=0;j<xMatrix.length;j++){
				pasMatrix[j][i]=xMatrix[j][i]-pasMatrix[j][i];
			}
		}
		
		for(int i=0;i < pasMatrix[0].length;i++){
			for(int j=0;j< pasMatrix[0].length;j++){
				for(int k=0; k < pasMatrix.length;k++){
					finalMatrix[i][j] = finalMatrix[i][j] + pasMatrix[k][i] * pasMatrix[k][j]/oneMatrix.length; 
				}
			}
		}
		
	}
	
	@Override
	public String toString(){
		String matrix="";
		for(int i=0;i<finalMatrix.length;i++){
			for(int j=0;j<finalMatrix[0].length;j++){
				matrix+=finalMatrix[i][j]+" " ;
			}
			matrix+="\n";
		}
		matrix+="\n";
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
				matrix+=yFinalMatrix[i][j]+" ";
			}
			matrix+="\n";
		}
		return matrix;
	}
	
}
