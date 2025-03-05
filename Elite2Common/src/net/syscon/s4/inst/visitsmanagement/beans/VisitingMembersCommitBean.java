package net.syscon.s4.inst.visitsmanagement.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VisitingMembersCommitBean extends BaseModel
 */
@XmlRootElement
public class VisitingMembersCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VisitingMembers> insertList;
	private List<VisitingMembers> deleteList;
	private List<VisitingMembers> updateList;

	public void setInsertList(final List<VisitingMembers> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VisitingMembers> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VisitingMembers> deleteList){
		this.deleteList = deleteList;
	}

	public List<VisitingMembers> getInsertList(){
		return insertList;
	}

	public List<VisitingMembers> getUpdateList(){
		return updateList;
	}

	public List<VisitingMembers> getDeleteList(){
		return deleteList;
	}

}
