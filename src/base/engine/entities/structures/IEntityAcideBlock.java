package base.engine.entities.structures;
/**
 * not up to date
 * @author Yoann CAPLAIN
 *
 */
@Deprecated 
public interface IEntityAcideBlock {

	public boolean isAcideBlock();
	public void setAcideBlock(boolean isAcide);
	/**
	 * Toute entite qui intersect le shape detruit une partie du shape
	 */
	public void detruireBlockFurAMesure();
	public void changerShapeEnPolygon();
	
}
