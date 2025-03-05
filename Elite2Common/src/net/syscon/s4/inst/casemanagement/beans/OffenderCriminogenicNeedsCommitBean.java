package net.syscon.s4.inst.casemanagement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffenderCriminogenicNeedsCommitBean extends BaseModel {
	private List<OffenderCriminogenicNeeds> insertList;
	private List<OffenderCriminogenicNeeds> deleteList;
	private List<OffenderCriminogenicNeeds> updateList;

	public void setInsertList(List<OffenderCriminogenicNeeds> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderCriminogenicNeeds> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderCriminogenicNeeds> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderCriminogenicNeeds> getInsertList() {
		return insertList;
	}

	public List<OffenderCriminogenicNeeds> getUpdateList() {
		return updateList;
	}

	public List<OffenderCriminogenicNeeds> getDeleteList() {
		return deleteList;
	}

}
