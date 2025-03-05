package net.syscon.s4.inst.booking.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderSubstanceUsesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class OffenderSubstanceUsesCommitBean extends BaseModel implements Serializable{
	private List<OffenderSubstanceUses> insertList;
	private List<OffenderSubstanceUses> deleteList;
	private List<OffenderSubstanceUses> updateList;

	public void setInsertList(List<OffenderSubstanceUses> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderSubstanceUses> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderSubstanceUses> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderSubstanceUses> getInsertList(){
		return insertList;
	}

	public List<OffenderSubstanceUses> getUpdateList(){
		return updateList;
	}

	public List<OffenderSubstanceUses> getDeleteList(){
		return deleteList;
	}

}
