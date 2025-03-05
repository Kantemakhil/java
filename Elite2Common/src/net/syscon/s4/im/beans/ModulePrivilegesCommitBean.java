package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class ModulePrivilegesCommitBean extends BaseBean
 */
@XmlRootElement
public class ModulePrivilegesCommitBean extends BaseModel implements Serializable {
	private List<ModulePrivileges> insertList;
	private List<ModulePrivileges> deleteList;
	private List<ModulePrivileges> updateList;

	public void setInsertList(List<ModulePrivileges> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<ModulePrivileges> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<ModulePrivileges> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ModulePrivileges> getInsertList() {
		return insertList;
	}

	public List<ModulePrivileges> getUpdateList() {
		return updateList;
	}

	public List<ModulePrivileges> getDeleteList() {
		return deleteList;
	}

}
