package base.engine.entities.triggers.outputs;

/**
 * Toute classe qui peut recevoir des input et envoyer des output doit implementer cette classe.
 * Les classes doivent avoir des : public final static int NOM_DE_LA_FONCTION_A_ACTIVER;
 * @author Yoann CAPLAIN
 *
 */
public interface IInputsAndOutputs {
	
	/**
	 * Non fini je sais pas trop comment le faire pour le faire
	 * @param function
	 */
	public void getInput(final int function);
	public void declencheOutput(final int function);
}
