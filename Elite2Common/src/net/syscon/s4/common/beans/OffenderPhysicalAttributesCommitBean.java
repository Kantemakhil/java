package net.syscon.s4.common.beans;
import java.io.Serializable;
import java.util.List;

import net.syscon.s4.im.beans.OffenderPhysicalAttributes;

/**
 * Class OffenderPhysicalAttributesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */

public class OffenderPhysicalAttributesCommitBean extends BaseModel implements Serializable{
	private List<OffenderPhysicalAttributes> insertList;
	private List<OffenderPhysicalAttributes> deleteList;
	private List<OffenderPhysicalAttributes> updateList;

	public void setInsertList(final List<OffenderPhysicalAttributes> insertList){
		this.insertList = insertList;
	}
	
	public void setUpdateList(final List<OffenderPhysicalAttributes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderPhysicalAttributes> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderPhysicalAttributes> getInsertList(){
		return insertList;
	}

	public List<OffenderPhysicalAttributes> getUpdateList(){
		return updateList;
	}

	public List<OffenderPhysicalAttributes> getDeleteList(){
		return deleteList;
	}

}
