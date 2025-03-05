package net.syscon.s4.inst.booking.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class PersonIdentifiersCommitBean extends BaseModel {
	private List<PersonIdentifiers> insertList;
	private List<PersonIdentifiers> deleteList;
	private List<PersonIdentifiers> updateList;

	public void setInsertList(List<PersonIdentifiers> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<PersonIdentifiers> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<PersonIdentifiers> deleteList) {
		this.deleteList = deleteList;
	}

	public List<PersonIdentifiers> getInsertList() {
		return insertList;
	}

	public List<PersonIdentifiers> getUpdateList() {
		return updateList;
	}

	public List<PersonIdentifiers> getDeleteList() {
		return deleteList;
	}

}
