package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderSentConditionsHtyCommitBean extends BaseBean
 
 */
@XmlRootElement
public class OffenderSentConditionsHtyCommitBean extends BaseModel implements Serializable{
	private List<OffenderSentConditionsHty> insertList;
	private List<OffenderSentConditionsHty> deleteList;
	private List<OffenderSentConditionsHty> updateList;

	public void setInsertList(List<OffenderSentConditionsHty> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderSentConditionsHty> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderSentConditionsHty> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderSentConditionsHty> getInsertList(){
		return insertList;
	}

	public List<OffenderSentConditionsHty> getUpdateList(){
		return updateList;
	}

	public List<OffenderSentConditionsHty> getDeleteList(){
		return deleteList;
	}

}
