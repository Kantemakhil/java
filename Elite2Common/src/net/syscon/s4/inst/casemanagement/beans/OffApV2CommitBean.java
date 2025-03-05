package net.syscon.s4.inst.casemanagement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffApV2CommitBean extends BaseModel {
	private List<OffApV2> insertList;
	private List<OffApV2> deleteList;
	private List<OffApV2> updateList;

	public void setInsertList(List<OffApV2> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffApV2> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffApV2> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffApV2> getInsertList() {
		return insertList;
	}

	public List<OffApV2> getUpdateList() {
		return updateList;
	}

	public List<OffApV2> getDeleteList() {
		return deleteList;
	}

}
