package net.syscon.s4.inst.casemanagement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffApV1CommitBean extends BaseModel {
	private List<OffApV1> insertList;
	private List<OffApV1> deleteList;
	private List<OffApV1> updateList;

	public void setInsertList(List<OffApV1> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffApV1> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffApV1> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffApV1> getInsertList() {
		return insertList;
	}

	public List<OffApV1> getUpdateList() {
		return updateList;
	}

	public List<OffApV1> getDeleteList() {
		return deleteList;
	}

}
