package particleStrategy;

import java.util.ArrayList;

public class ParticlePath{
	
	private ArrayList<Double> originDistance = new ArrayList<Double>();
	private ArrayList<Integer> x = new ArrayList<Integer>();
	private ArrayList<Integer> y = new ArrayList<Integer>();
	
	public ParticlePath() {
	}

	public void addToPath(int x,int y){
		originDistance.add(Math.sqrt(x*x+y*y));
		this.x.add(x);
		this.y.add(y);
	}
	
	public void showOriginDistance(){
		for(int i=0;i<originDistance.size();i++){
			System.out.println(originDistance.get(i));
		}
	}
	
	public void showXAndYPath(){
		System.out.println("X   Y");
		for(int i=0;i<x.size();i++){
			System.out.println(x.get(i) + " " +y.get(i));
		}
	}
	
	public ArrayList<Double> getOriginDistance(){
		return originDistance;
	}
	
	public ArrayList<Integer> getX(){
		return x;
	}
	
	public ArrayList<Integer> getY(){
		return y;
	}
}
