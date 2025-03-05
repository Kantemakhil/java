package net.syscon.s4.inst.visitsmanagement.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VisitingGroupsCommitBean extends BaseModel
 */
@XmlRootElement
public class VisitingGroupsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VisitingGroups> insertList;
	private List<VisitingGroups> deleteList;
	private List<VisitingGroups> updateList;

	public void setInsertList(final List<VisitingGroups> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VisitingGroups> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VisitingGroups> deleteList){
		this.deleteList = deleteList;
	}

	public List<VisitingGroups> getInsertList(){
		return insertList;
	}

	public List<VisitingGroups> getUpdateList(){
		return updateList;
	}

	public List<VisitingGroups> getDeleteList(){
		return deleteList;
	}

}
