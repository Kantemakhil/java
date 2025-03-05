package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class PersonsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Persons> insertList;
	private List<Persons> deleteList;
	private List<Persons> updateList;

	public void setInsertList(List<Persons> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<Persons> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<Persons> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Persons> getInsertList() {
		return insertList;
	}

	public List<Persons> getUpdateList() {
		return updateList;
	}

	public List<Persons> getDeleteList() {
		return deleteList;
	}

}
