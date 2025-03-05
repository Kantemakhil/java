package net.syscon.s4.im.beans;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class StaffAccessibleCaseloadsCommitBean extends BaseBean
 */
@XmlRootElement
public class StaffAccessibleCaseloadsCommitBean extends BaseModel implements Serializable{
	private List<StaffAccessibleCaseloads> insertList;
	private List<StaffAccessibleCaseloads> deleteList;
	private List<StaffAccessibleCaseloads> updateList;

	public void setInsertList(final List<StaffAccessibleCaseloads> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StaffAccessibleCaseloads> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StaffAccessibleCaseloads> deleteList){
		this.deleteList = deleteList;
	}

	public List<StaffAccessibleCaseloads> getInsertList(){
		return insertList;
	}

	public List<StaffAccessibleCaseloads> getUpdateList(){
		return updateList;
	}

	public List<StaffAccessibleCaseloads> getDeleteList(){
		return deleteList;
	}

}
