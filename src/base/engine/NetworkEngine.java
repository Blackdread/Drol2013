/**
 * 
 */
package base.engine;

/**
 * @author Blackdread
 *
 */
public class NetworkEngine extends Engine {

	private boolean serveur;
	
	public NetworkEngine(EngineManager engineManager)
	{
		super(engineManager);
		this.serveur = false;
	}
	
	
	@Override
	public boolean processMessage() {
		// TODO Auto-generated method stub

		return false;
	}


	public boolean isServeur() {
		return serveur;
	}


	public void setServeur(boolean serveur) {
		this.serveur = serveur;
	}

}
