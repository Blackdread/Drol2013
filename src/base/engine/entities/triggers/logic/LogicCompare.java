package base.engine.entities.triggers.logic;

/**
 * It examines the relationship between two numbers ("value" and "compare value"), and fires appropriate output(s).
 * The equation is (value - compare value).
 * @author Yoann CAPLAIN
 *
 */
public class LogicCompare extends Logic {

	/**
	 * intial value
	 */
	protected int initialValue;
	
	/**
	 * Value that will be compared to initialValue
	 */
	protected int compareValue;
	
	/**
	 * @see Logic
	 * @param Integer initial value
	 */
	public LogicCompare(String name, int initialValue){
		super(name);
	}
	/**
	 * @see Logic
	 * @param Integer initial value
	 * @param Integer compare value
	 */
	public LogicCompare(String name, int initialValue, int compareValue){
		super(name);
	}
	
	@Override
	public void fireOutputs() {
		// TODO Auto-generated method stub

	}
	
	public void OnEqualTo(){
		
	}
	public void OnGreaterThan(){
			
	}
	public void OnLessThan(){
		
	}
	public void OnNotEqual(){
		
	}
	
	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}
	public void setCompareValue(int compareValue) {
		this.compareValue = compareValue;
	}
	public int getInitialValue() {
		return this.initialValue;
	}
	public int getCompareValue() {
		return this.compareValue;
	}

}
