package net.syscon.s4.inst.booking.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class PersonEmploymentsCommitBean extends BaseModel {
	private List<PersonEmployments> insertList;
	private List<PersonEmployments> deleteList;
	private List<PersonEmployments> updateList;

	public void setInsertList(List<PersonEmployments> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<PersonEmployments> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<PersonEmployments> deleteList) {
		this.deleteList = deleteList;
	}

	public List<PersonEmployments> getInsertList() {
		return insertList;
	}

	public List<PersonEmployments> getUpdateList() {
		return updateList;
	}

	public List<PersonEmployments> getDeleteList() {
		return deleteList;
	}

}
