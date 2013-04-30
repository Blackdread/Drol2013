package base.engine.entities.others.triggers;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.InputsAndOutputs;

public class TriggerChangeLevel extends TriggerObjectInZone {

	
	private static final long serialVersionUID = -1102108675443718249L;

	public TriggerChangeLevel(EngineManager e, String name, int xx, int yy, int w, int h) {
		super(e, name, xx, yy, w, h);
	}
	
	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
	}

}
