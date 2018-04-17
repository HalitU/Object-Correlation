package particleStrategy.moveStrategy;

import java.util.List;

import particleStrategy.MoveStrategy;
import particleStrategy.Particle;

public class CompositeStrategy implements MoveStrategy{

	private List<MoveStrategy> strategies;
	
	public CompositeStrategy(List<MoveStrategy> strategies){
		this.strategies = strategies;
	}
	
	public List<MoveStrategy> getStrategies(){
		return strategies; 
	}

	@Override
	public void move(Particle[][] map, int xCos, int yCos, Particle par) {
		for(int i=0;i<strategies.size();i++){
			par.setMoveStrategy(strategies.get(i));
			par.move();

		}
		par.addPath();
		par.setMoveStrategy(this);
	}
}
