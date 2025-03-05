package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VCbCustodyPeriodCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<VCbCustodyPeriod> insertList;
	private List<VCbCustodyPeriod> updateList;
	private List<VCbCustodyPeriod> deleteList;
	
	public List<VCbCustodyPeriod> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VCbCustodyPeriod> insertList) {
		this.insertList = insertList;
	}
	public List<VCbCustodyPeriod> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VCbCustodyPeriod> updateList) {
		this.updateList = updateList;
	}
	public List<VCbCustodyPeriod> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VCbCustodyPeriod> deleteList) {
		this.deleteList = deleteList;
	}
}
