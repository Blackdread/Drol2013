package base.engine.entities;

/**
 * Inutile depuis la serialization
 * @author Yoann CAPLAIN
 *
 */
@Deprecated
public interface ISave {
	
	public String save();
	
	public Object load(String s);
	
}
