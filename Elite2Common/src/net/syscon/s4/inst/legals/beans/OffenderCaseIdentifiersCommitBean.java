package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderCaseIdentifiersCommitBean extends BaseBean
 
 */
public class OffenderCaseIdentifiersCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderCaseIdentifiers> insertList;
	private List<OffenderCaseIdentifiers> deleteList;
	private List<OffenderCaseIdentifiers> updateList;

	public void setInsertList(List<OffenderCaseIdentifiers> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderCaseIdentifiers> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderCaseIdentifiers> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderCaseIdentifiers> getInsertList(){
		return insertList;
	}

	public List<OffenderCaseIdentifiers> getUpdateList(){
		return updateList;
	}

	public List<OffenderCaseIdentifiers> getDeleteList(){
		return deleteList;
	}

}
