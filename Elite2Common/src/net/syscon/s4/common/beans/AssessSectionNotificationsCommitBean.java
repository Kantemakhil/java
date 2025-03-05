package net.syscon.s4.common.beans;

import java.util.List;


public class AssessSectionNotificationsCommitBean  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AssessSectionNotifications> insertList;
	private List<AssessSectionNotifications> deleteList;
	private List<AssessSectionNotifications> updateList;

	public void setInsertList(List<AssessSectionNotifications> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AssessSectionNotifications> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AssessSectionNotifications> deleteList){
		this.deleteList = deleteList;
	}

	public List<AssessSectionNotifications> getInsertList(){
		return insertList;
	}

	public List<AssessSectionNotifications> getUpdateList(){
		return updateList;
	}

	public List<AssessSectionNotifications> getDeleteList(){
		return deleteList;
	}


}

