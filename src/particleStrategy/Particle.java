package particleStrategy;

import java.util.ArrayList;
import java.util.List;

public class Particle {
	//history, factory, composition
	int xCos;
	int yCos;
	Particle[][] map;
	MoveStrategy moveStrategy;
	ParticlePath particlePath = new ParticlePath();
	
	int listCounter=0;
	
	public Particle(int xCos,int yCos,Particle[][] map,MoveStrategy moveStrategy){
		
		this.xCos=xCos;
		this.yCos=yCos;
		this.map=map;
		this.moveStrategy=moveStrategy;
		
		map[yCos][xCos]=this;
		
		particlePath.addToPath(xCos, yCos);
	}
	
	public ParticlePath getPath(){
		return particlePath;
	}
	
	public void setMoveStrategy(MoveStrategy moveStrategy){
		this.moveStrategy=moveStrategy;
	}
	
	public void setxCos(int xCos){
		this.xCos=xCos;
	}
	
	public void setyCos(int yCos){
		this.yCos=yCos;
	}
	
	public int getxCos(){
		return xCos;
	}
	
	public int getyCos(){
		return yCos;
	}
	
	public MoveStrategy getMoveStrategy(){
		return moveStrategy;
	}
	
	public Particle[][] getMap(){
		return map;
	}
	
	public void move(){
		moveStrategy.move(map, xCos, yCos,this);
	}
	
	public void addPath(){
		listCounter++;
		particlePath.addToPath(xCos, yCos);
	}
	public String toString(){
		return "x: " + xCos + ",y: " + yCos;
	}
}
