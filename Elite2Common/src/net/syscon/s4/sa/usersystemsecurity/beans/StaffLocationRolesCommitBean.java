package net.syscon.s4.sa.usersystemsecurity.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StaffLocationRolesCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<StaffLocationRoles> insertList;
	@JsonProperty("deleteList")
	private List<StaffLocationRoles> deleteList;
	@JsonProperty("updateList")
	private List<StaffLocationRoles> updateList;
	/**
	 * @return the insertList
	 */
	public List<StaffLocationRoles> getInsertList() {
		return insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<StaffLocationRoles> getDeleteList() {
		return deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<StaffLocationRoles> getUpdateList() {
		return updateList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<StaffLocationRoles> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<StaffLocationRoles> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<StaffLocationRoles> updateList) {
		this.updateList = updateList;
	}

}
