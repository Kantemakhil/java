package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VStgLocationMembersCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private List<VStgLocationMembers> insertList;
	private List<VStgLocationMembers> deleteList;
	private List<VStgLocationMembers> updateList;

	public void setInsertList(final List<VStgLocationMembers> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VStgLocationMembers> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VStgLocationMembers> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VStgLocationMembers> getInsertList() {
		return insertList;
	}

	public List<VStgLocationMembers> getUpdateList() {
		return updateList;
	}

	public List<VStgLocationMembers> getDeleteList() {
		return deleteList;
	}

}
