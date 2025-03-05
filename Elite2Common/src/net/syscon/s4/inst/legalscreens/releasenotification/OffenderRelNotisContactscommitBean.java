package net.syscon.s4.inst.legalscreens.releasenotification;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderRelNotisContactscommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OffenderRelNotisContacts> insertList;
	private List<OffenderRelNotisContacts> deleteList;
	private List<OffenderRelNotisContacts> updateList;
	
	public List<OffenderRelNotisContacts> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<OffenderRelNotisContacts> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderRelNotisContacts> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<OffenderRelNotisContacts> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderRelNotisContacts> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<OffenderRelNotisContacts> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
