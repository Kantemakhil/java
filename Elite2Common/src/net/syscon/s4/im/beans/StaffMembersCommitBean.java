package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.StaffMembers;

/**
 * Class StaffMembersCommitBean extends BaseBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffMembersCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<StaffMembers> insertList;
	private List<StaffMembers> deleteList;
	private List<StaffMembers> updateList;

	public void setInsertList(final List<StaffMembers> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StaffMembers> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StaffMembers> deleteList) {
		this.deleteList = deleteList;
	}

	public List<StaffMembers> getInsertList() {
		return insertList;
	}

	public List<StaffMembers> getUpdateList() {
		return updateList;
	}

	public List<StaffMembers> getDeleteList() {
		return deleteList;
	}

}
