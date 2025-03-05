package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class SystemProfilesCommitBean extends BaseBean
 */
@XmlRootElement
public class SystemProfilesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SystemProfiles> insertList;
	private List<SystemProfiles> deleteList;
	private List<SystemProfiles> updateList;

	public void setInsertList(final List<SystemProfiles> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SystemProfiles> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SystemProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SystemProfiles> getInsertList() {
		return insertList;
	}

	public List<SystemProfiles> getUpdateList() {
		return updateList;
	}

	public List<SystemProfiles> getDeleteList() {
		return deleteList;
	}

}
