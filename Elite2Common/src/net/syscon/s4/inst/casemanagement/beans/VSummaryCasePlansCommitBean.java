package net.syscon.s4.inst.casemanagement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class VSummaryCasePlansCommitBean extends BaseModel {
	private List<VSummaryCasePlans> insertList;
	private List<VSummaryCasePlans> deleteList;
	private List<VSummaryCasePlans> updateList;

	public void setInsertList(List<VSummaryCasePlans> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<VSummaryCasePlans> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<VSummaryCasePlans> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VSummaryCasePlans> getInsertList() {
		return insertList;
	}

	public List<VSummaryCasePlans> getUpdateList() {
		return updateList;
	}

	public List<VSummaryCasePlans> getDeleteList() {
		return deleteList;
	}

}
