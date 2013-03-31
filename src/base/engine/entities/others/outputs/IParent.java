package base.engine.entities.others.outputs;

import java.util.ArrayList;

/**
 * If the parent moves, its children move.
 * @author Yoann CAPLAIN
 *
 */
public interface IParent {

	public ArrayList<String> getChildren();
	public void addChild(InputsAndOutputs child);	// TODO Le nom ? (oui normalement)
	public void removeChild(InputsAndOutputs child);	// TODO Le nom ?? (oui normalement)
	
	/**
	 * @return true if array of children size >= 1
	 */
	public boolean hasChildren();
	
	/**
	 * @return true if parentName != null
	 */
	public boolean hasParent();
	
	/**
	 * Move with this entity. See Entity Hierarchy (parenting)
	 * If null then this entity has no parent
	 */
	public void SetParent(String parent);
	/**
	 * Change this entity to attach to a specific attachment point on its parent
	 * The entity will teleport so that the position of its root bone matches that of the attachment
	 * Entities must be parented before being sent this input
	 */
	public void SetParentAttachment(String parent);
	/**
	 * As above, but without teleporting
	 * The entity retains its position relative to the attachment at the time of the input being received
	 * @param parent name of the parent
	 */
	public void SetParentAttachmentMaintainOffset(String parent);
	 
	/**
	 * Removes this entity from the the movement hierarchy, leaving it free to move independently
	 */
	public void ClearParent();
	
}
