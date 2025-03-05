package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class CaseloadLimitsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CaseloadLimits> insertList;
	private List<CaseloadLimits> deleteList;
	private List<CaseloadLimits> updateList;

	public void setInsertList(List<CaseloadLimits> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<CaseloadLimits> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<CaseloadLimits> deleteList){
		this.deleteList = deleteList;
	}

	public List<CaseloadLimits> getInsertList(){
		return insertList;
	}

	public List<CaseloadLimits> getUpdateList(){
		return updateList;
	}

	public List<CaseloadLimits> getDeleteList(){
		return deleteList;
	}

}
