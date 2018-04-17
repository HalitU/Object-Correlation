package particleStrategy;

import particleStrategy.moveStrategy.DownStrategy;
import particleStrategy.moveStrategy.LeftStrategy;
import particleStrategy.moveStrategy.RightStrategy;
import particleStrategy.moveStrategy.UpStrategy;
import particleStrategy.moveType.MoveType;

public class MoveFactory {
	private MoveType type;
	public MoveStrategy getMoveStrategy(MoveType moveType){
		if(moveType==null)
			return null;
		if(moveType == type.UP)
			return new UpStrategy();
		if(moveType == type.DOWN)
			return new DownStrategy();
		if(moveType == type.LEFT)
			return new LeftStrategy();
		if(moveType == type.RIGHT)
			return new RightStrategy();
		return null;
	}
};