/**
 * 
 */
package base.engine.entities.triggers.logic;

import base.engine.entities.triggers.outputs.Outputs;

/**
 * It compares an input to up to 16 configured values, firing a corresponding output if there is a match (on InValue), 
 * or fires a random output (on PickRandom)
 * Does not recognize floats with trailing zeroes (like 0.5000 or 1.00). Always remove any, leave at least one.
 * @author Yoann CAPLAIN
 *
 */
public class logicCase extends Logic {

	private final static int NB_CASE = 16;
	
	/**
	 * Peut contenir tout -> true, false, 10, 2345, blabla, player1, 2.340
	 * exemple: Case16 is valueOfCase[15]
	 */
	private String valueOfCase[];	
	
	private boolean shuffleCaseReminder[];
	
	public logicCase(String name) {
		super(name);
		shuffleCaseReminder = new boolean[NB_CASE+1 +1];
		for(int i=0;i < NB_CASE + 1;i++)
			shuffleCaseReminder[i] = false;
		valueOfCase = new String[NB_CASE];
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#addInListInputsInputsOfTheClass()
	 */
	@Override
	public void addInListInputsInputsOfTheClass(){
		this.list_inputs.add("InValue");
		this.list_inputs.add("PickRandom");
		this.list_inputs.add("PickRandomShuffle");
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#addInListOutputsOutputsOfTheClass()
	 */
	@Override
	public void addInListOutputsOutputsOfTheClass() {
		this.list_outputs.add("OnCase01");
		this.list_outputs.add("OnCase02");
		this.list_outputs.add("OnCase03");
		this.list_outputs.add("OnCase04");
		this.list_outputs.add("OnCase05");
		this.list_outputs.add("OnCase06");
		this.list_outputs.add("OnCase07");
		this.list_outputs.add("OnCase08");
		this.list_outputs.add("OnCase09");
		this.list_outputs.add("OnCase10");
		this.list_outputs.add("OnCase11");
		this.list_outputs.add("OnCase12");
		this.list_outputs.add("OnCase13");
		this.list_outputs.add("OnCase14");
		this.list_outputs.add("OnCase15");
		this.list_outputs.add("OnCase16");
		this.list_outputs.add("OnDefault");
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
			else
				OnDefault();
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
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase01"))
					v.fireOutput();
	}
	private void OnCase02(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase02"))
					v.fireOutput();
	}
	private void OnCase03(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase03"))
					v.fireOutput();
	}
	private void OnCase04(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase04"))
					v.fireOutput();
	}
	private void OnCase05(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase05"))
					v.fireOutput();
	}
	private void OnCase06(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase06"))
					v.fireOutput();
	}
	private void OnCase07(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase07"))
					v.fireOutput();
	}
	private void OnCase08(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase08"))
					v.fireOutput();
	}
	private void OnCase09(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase09"))
					v.fireOutput();
	}
	private void OnCase10(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase10"))
					v.fireOutput();
	}
	private void OnCase11(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase11"))
					v.fireOutput();
	}
	private void OnCase12(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase12"))
					v.fireOutput();
	}
	private void OnCase13(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase13"))
					v.fireOutput();
	}
	private void OnCase14(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase14"))
					v.fireOutput();
	}
	private void OnCase15(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase15"))
					v.fireOutput();
	}
	private void OnCase16(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnCase16"))
					v.fireOutput();
	}
	/**
	 * Fired when the input value does not equal any of the Case values
	 */
	private void OnDefault(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnDefault"))
					v.fireOutput();
	}

}
