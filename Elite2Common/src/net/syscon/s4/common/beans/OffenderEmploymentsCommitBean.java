package net.syscon.s4.common.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OffenderEmploymentsCommitBean extends BaseModel {
	private List<OffenderEmployments> insertList;
	private List<OffenderEmployments> deleteList;
	private List<OffenderEmployments> updateList;

	public void setInsertList(List<OffenderEmployments> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderEmployments> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderEmployments> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderEmployments> getInsertList() {
		return insertList;
	}

	public List<OffenderEmployments> getUpdateList() {
		return updateList;
	}

	public List<OffenderEmployments> getDeleteList() {
		return deleteList;
	}

}
