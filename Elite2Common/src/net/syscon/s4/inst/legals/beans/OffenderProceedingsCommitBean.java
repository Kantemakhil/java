package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderProceedingsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private List<OffenderProceedings> insertList;
	private List<OffenderProceedings> deleteList;
	private List<OffenderProceedings> updateList;
	private VOffenderProceedingSents vOffenderScheduleModel;
	public List<OffenderProceedings> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderProceedings> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderProceedings> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderProceedings> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderProceedings> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderProceedings> updateList) {
		this.updateList = updateList;
	}
	public VOffenderProceedingSents getvOffenderScheduleModel() {
		return vOffenderScheduleModel;
	}
	public void setvOffenderScheduleModel(VOffenderProceedingSents vOffenderScheduleModel) {
		this.vOffenderScheduleModel = vOffenderScheduleModel;
	}

	
	
}
