package net.syscon.s4.inst.legalscreens.releasenotification;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderReleaseNotiscommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OffenderReleaseNotis> insertList;
	private List<OffenderReleaseNotis> deleteList;
	private List<OffenderReleaseNotis> updateList;
	private List<OffenderRelNotisContacts> insertRncList;
	private List<OffenderRelNotisContacts> deleteRncList;
	private List<OffenderRelNotisContacts> updateRncList;
	
	public List<OffenderReleaseNotis> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<OffenderReleaseNotis> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderReleaseNotis> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<OffenderReleaseNotis> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderReleaseNotis> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<OffenderReleaseNotis> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<OffenderRelNotisContacts> getInsertRncList() {
		return insertRncList;
	}
	public void setInsertRncList(List<OffenderRelNotisContacts> insertRncList) {
		this.insertRncList = insertRncList;
	}
	public List<OffenderRelNotisContacts> getDeleteRncList() {
		return deleteRncList;
	}
	public void setDeleteRncList(List<OffenderRelNotisContacts> deleteRncList) {
		this.deleteRncList = deleteRncList;
	}
	public List<OffenderRelNotisContacts> getUpdateRncList() {
		return updateRncList;
	}
	public void setUpdateRncList(List<OffenderRelNotisContacts> updateRncList) {
		this.updateRncList = updateRncList;
	}

}
