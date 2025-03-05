package net.syscon.s4.inst.booking.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffenderContactPersonsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderContactPersons> insertList;
	private List<OffenderContactPersons> deleteList;
	private List<OffenderContactPersons> updateList;

	public void setInsertList(List<OffenderContactPersons> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderContactPersons> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderContactPersons> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderContactPersons> getInsertList() {
		return insertList;
	}

	public List<OffenderContactPersons> getUpdateList() {
		return updateList;
	}

	public List<OffenderContactPersons> getDeleteList() {
		return deleteList;
	}

}
