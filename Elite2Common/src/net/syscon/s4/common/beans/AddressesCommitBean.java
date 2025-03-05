package net.syscon.s4.common.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressesCommitBean extends BaseModel {
	private List<Addresses> insertList;
	private List<Addresses> deleteList;
	private List<Addresses> updateList;

	public void setInsertList(List<Addresses> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<Addresses> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<Addresses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Addresses> getInsertList() {
		return insertList;
	}

	public List<Addresses> getUpdateList() {
		return updateList;
	}

	public List<Addresses> getDeleteList() {
		return deleteList;
	}

}
