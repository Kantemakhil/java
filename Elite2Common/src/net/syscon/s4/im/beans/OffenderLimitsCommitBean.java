package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffenderLimitsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderLimits> insertList;
	private List<OffenderLimits> deleteList;
	private List<OffenderLimits> updateList;

	public void setInsertList(List<OffenderLimits> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderLimits> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderLimits> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderLimits> getInsertList() {
		return insertList;
	}

	public List<OffenderLimits> getUpdateList() {
		return updateList;
	}

	public List<OffenderLimits> getDeleteList() {
		return deleteList;
	}

}
