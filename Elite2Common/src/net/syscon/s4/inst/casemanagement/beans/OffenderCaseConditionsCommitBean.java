package net.syscon.s4.inst.casemanagement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffenderCaseConditionsCommitBean extends BaseModel {
	private List<OffenderCaseConditions> insertList;
	private List<OffenderCaseConditions> deleteList;
	private List<OffenderCaseConditions> updateList;

	public void setInsertList(List<OffenderCaseConditions> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderCaseConditions> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderCaseConditions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderCaseConditions> getInsertList() {
		return insertList;
	}

	public List<OffenderCaseConditions> getUpdateList() {
		return updateList;
	}

	public List<OffenderCaseConditions> getDeleteList() {
		return deleteList;
	}

}
