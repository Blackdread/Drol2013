package base.engine.entities.others.outputs;

import java.util.ArrayList;

/**
 * If the parent moves, its children move.
 * @author Yoann CAPLAIN
 *
 */
public interface IParent {

	public ArrayList<String> getChildren();
	public void addChild(InputsAndOutputs child);
	public void removeChild(InputsAndOutputs child);
	
}
