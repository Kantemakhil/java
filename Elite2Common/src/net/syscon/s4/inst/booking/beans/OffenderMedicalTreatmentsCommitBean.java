package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderMedicalTreatmentsCommitBean extends BaseModel implements Serializable{
	private List<OffenderMedicalTreatments> insertList;
	private List<OffenderMedicalTreatments> deleteList;
	private List<OffenderMedicalTreatments> updateList;
	public List<OffenderMedicalTreatments> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderMedicalTreatments> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderMedicalTreatments> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderMedicalTreatments> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderMedicalTreatments> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderMedicalTreatments> updateList) {
		this.updateList = updateList;
	}
	
}
