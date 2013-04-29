/**
 * 
 */
package base.engine.entities.others.logics;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.InputsAndOutputs;

/**
 * It compares an input to up to 16 configured values, firing a corresponding output if there is a match (on InValue), 
 * or fires a random output (on PickRandom)
 * Does not recognize floats with trailing zeroes (like 0.5000 or 1.00). Always remove any.
 * @author Yoann CAPLAIN
 *
 */
public class LogicCase extends Logic {

	private static final long serialVersionUID = -1057373904626305090L;

	private final static int NB_CASE = 16;
	
	/**
	 * Peut contenir tout -> true, false, 10, 2345, blabla, player1, 2.340
	 * exemple: Case16 is valueOfCase[15]
	 */
	private String valueOfCase[];	
	
	private boolean shuffleCaseReminder[];
	
	public LogicCase(EngineManager e,String name) {
		super(e,name);
		shuffleCaseReminder = new boolean[NB_CASE+1 +1];
		for(int i=0;i < NB_CASE + 1;i++)
			shuffleCaseReminder[i] = false;
		valueOfCase = new String[NB_CASE];
	}

	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		valueOfCase = ((LogicCase)objetACopier).valueOfCase;
		shuffleCaseReminder = ((LogicCase)objetACopier).shuffleCaseReminder;
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		super.render("LogicCase", g, x, y);
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnCase01");
		list_outputs.add("OnCase02");
		list_outputs.add("OnCase03");
		list_outputs.add("OnCase04");
		list_outputs.add("OnCase05");
		list_outputs.add("OnCase06");
		list_outputs.add("OnCase07");
		list_outputs.add("OnCase08");
		list_outputs.add("OnCase09");
		list_outputs.add("OnCase10");
		list_outputs.add("OnCase11");
		list_outputs.add("OnCase12");
		list_outputs.add("OnCase13");
		list_outputs.add("OnCase14");
		list_outputs.add("OnCase15");
		list_outputs.add("OnCase16");
		list_outputs.add("OnDefault");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("InValue");
		list_inputs.add("PickRandom");
		list_inputs.add("PickRandomShuffle");
		
		return list_inputs;
	}
	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireOutputs(java.lang.String)
	 */
	@Override
	public void fireOutputs(final String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnCase01"))
				OnCase01();
			else if(nameOfOutput.equalsIgnoreCase("OnCase02"))
				OnCase02();
			else if(nameOfOutput.equalsIgnoreCase("OnCase03"))
				OnCase03();
			else if(nameOfOutput.equalsIgnoreCase("OnCase04"))
				OnCase04();
			else if(nameOfOutput.equalsIgnoreCase("OnCase05"))
				OnCase05();
			else if(nameOfOutput.equalsIgnoreCase("OnCase06"))
				OnCase06();
			else if(nameOfOutput.equalsIgnoreCase("OnCase07"))
				OnCase07();
			else if(nameOfOutput.equalsIgnoreCase("OnCase08"))
				OnCase08();
			else if(nameOfOutput.equalsIgnoreCase("OnCase09"))
				OnCase09();
			else if(nameOfOutput.equalsIgnoreCase("OnCase10"))
				OnCase10();
			else if(nameOfOutput.equalsIgnoreCase("OnCase11"))
				OnCase11();
			else if(nameOfOutput.equalsIgnoreCase("OnCase12"))
				OnCase12();
			else if(nameOfOutput.equalsIgnoreCase("OnCase13"))
				OnCase13();
			else if(nameOfOutput.equalsIgnoreCase("OnCase14"))
				OnCase14();
			else if(nameOfOutput.equalsIgnoreCase("OnCase15"))
				OnCase15();
			else if(nameOfOutput.equalsIgnoreCase("OnCase16"))
				OnCase16();
			else if(nameOfOutput.equalsIgnoreCase("OnDefault"))
				OnDefault();
			else
				super.fireOutputs(nameOfOutput);
		}
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String)
	 */
	@Override
	public void fireInputs(final String nameOfInput) {
		if(nameOfInput != null){
			if(nameOfInput.equalsIgnoreCase("PickRandom"))
				pickRandom();
			else if(nameOfInput.equalsIgnoreCase("PickRandomShuffle"))
				pickRandomShuffle();
		}
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public void fireInputs(final String nameOfInput, Object parameter){
		if(nameOfInput != null && parameter != null){
			if(nameOfInput.equalsIgnoreCase("InValue"))
				InValue(String.valueOf(parameter));
		}
	}
	
	/*
	 * Inputs
	 */
	/**
	 * Compares the Input value to the case values, and fires the appropriate output, if any
	 * @param value
	 */
	public void InValue(String value){
		int i;
		for(i=0;i<NB_CASE;i++)
			if(valueOfCase[i].equalsIgnoreCase(value)){
				if(i < 10)
					fireOutputs("OnCase0"+i);
				else
					fireOutputs("OnCase"+i);
				return;
			}
		fireOutputs("OnDefault");
	}
	/**
	 *  Fires a random OnCase output with at least one connection
	 */
	public void pickRandom(){
		int index = searchOnCaseToFireWithAtLeastOneConnection();
		
		if(index < 10)
			fireOutputs("OnCase0"+index);
		else if(index < NB_CASE + 1)
			fireOutputs("OnCase"+index);
		else
			fireOutputs("OnDefault");
	}
	/**
	 * Fires a random OnCase output with at least one connection, with no repeats until 
	 * all cases have been picked, at which point the shuffle starts over
	 */
	public void pickRandomShuffle(){
		for(int i=0;i<NB_CASE+1;i++){
			if(shuffleCaseReminder[i] == false)
				break;
			else if(i==NB_CASE)
				for(int j=0;j<NB_CASE+1;j++)
					shuffleCaseReminder[j] = false;
		}
		int index;
		
		do{
			index = searchOnCaseToFireWithAtLeastOneConnection();
		}while(shuffleCaseReminder[index]==true);
		
		shuffleCaseReminder[index]=true;
		
		if(index < 10)
			fireOutputs("OnCase0"+index);
		else if(index < NB_CASE + 1)
			fireOutputs("OnCase"+index);
		else
			fireOutputs("OnDefault");
	}
	
	private boolean isThereOutputForThatCase(int nbCase){
		if(nbCase < 10)
			return this.array_outputs.contains("OnCase0"+nbCase);
		else
			return this.array_outputs.contains("OnCase"+nbCase);
	}
	
	private int searchOnCaseToFireWithAtLeastOneConnection(){
		int caseFalse[] = new int[NB_CASE+1];
		
		int topVide=0,i;
		for(i=1;i<NB_CASE+1;i++){
			if(isThereOutputForThatCase(i)){
				caseFalse[topVide] = i;
				topVide++;
			}
		}
		if(topVide == 0)
			return NB_CASE+1;	// Default output
		
		return  caseFalse[(int)(Math.random()*topVide)];
	}
	
	/*
	 * Outputs
	 */
	/**
	 * Fired when the input value equals the corresponding Case value
	 */
	private void OnCase01(){
		fireOutput("OnCase01");
	}
	private void OnCase02(){
		fireOutput("OnCase02");
	}
	private void OnCase03(){
		fireOutput("OnCase03");
	}
	private void OnCase04(){
		fireOutput("OnCase04");
	}
	private void OnCase05(){
		fireOutput("OnCase05");
	}
	private void OnCase06(){
		fireOutput("OnCase06");
	}
	private void OnCase07(){
		fireOutput("OnCase07");
	}
	private void OnCase08(){
		fireOutput("OnCase08");
	}
	private void OnCase09(){
		fireOutput("OnCase09");
	}
	private void OnCase10(){
		fireOutput("OnCase10");
	}
	private void OnCase11(){
		fireOutput("OnCase11");
	}
	private void OnCase12(){
		fireOutput("OnCase12");
	}
	private void OnCase13(){
		fireOutput("OnCase13");
	}
	private void OnCase14(){
		fireOutput("OnCase14");
	}
	private void OnCase15(){
		fireOutput("OnCase15");
	}
	private void OnCase16(){
		fireOutput("OnCase16");
	}
	/**
	 * Fired when the input value does not equal any of the Case values
	 */
	private void OnDefault(){
		fireOutput("OnDefault");
	}

}
