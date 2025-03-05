package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

public class VOffObservationPeriodsRescommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<VOffObservationPeriodsRes> insertList;
	private List<VOffObservationPeriodsRes> deleteList;
	private List<VOffObservationPeriodsRes> updateList;
	public List<VOffObservationPeriodsRes> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VOffObservationPeriodsRes> insertList) {
		this.insertList = insertList;
	}
	public List<VOffObservationPeriodsRes> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VOffObservationPeriodsRes> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VOffObservationPeriodsRes> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VOffObservationPeriodsRes> updateList) {
		this.updateList = updateList;
	}

	
}
