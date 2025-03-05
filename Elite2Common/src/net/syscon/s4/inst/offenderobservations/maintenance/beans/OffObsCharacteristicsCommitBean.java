package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsCharacteristicsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffObsCharacteristics> insertList;

	@JsonProperty("deleteList")
	private List<OffObsCharacteristics> deleteList;

	@JsonProperty("updateList")
	private List<OffObsCharacteristics> updateList;

	@JsonProperty("observationCheckDetailTypeBean")
	private OffenderObservationTypes observationCheckDetailTypeBean;
	public List<OffObsCharacteristics> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffObsCharacteristics> insertList) {
		this.insertList = insertList;
	}

	public List<OffObsCharacteristics> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffObsCharacteristics> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffObsCharacteristics> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffObsCharacteristics> updateList) {
		this.updateList = updateList;
	}

	public OffenderObservationTypes getObservationCheckDetailTypeBean() {
		return observationCheckDetailTypeBean;
	}

	public void setObservationCheckDetailTypeBean(OffenderObservationTypes observationCheckDetailTypeBean) {
		this.observationCheckDetailTypeBean = observationCheckDetailTypeBean;
	}

}
