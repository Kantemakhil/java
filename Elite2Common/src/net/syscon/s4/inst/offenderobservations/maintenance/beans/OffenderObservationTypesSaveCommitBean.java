package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationTypesSaveCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @JsonProperty("offednerObservationCommitList")
	 private OffenderObservationTypesCommitBean offednerObservationCommitList;
	 
	 @JsonProperty("offednerObservationDetailCommitList")
	 private OffObsCharacteristicsCommitBean offednerObservationDetailCommitList;

	public OffenderObservationTypesCommitBean getOffednerObservationCommitList() {
		return offednerObservationCommitList;
	}

	public void setOffednerObservationCommitList(OffenderObservationTypesCommitBean offednerObservationCommitList) {
		this.offednerObservationCommitList = offednerObservationCommitList;
	}

	public OffObsCharacteristicsCommitBean getOffednerObservationDetailCommitList() {
		return offednerObservationDetailCommitList;
	}

	public void setOffednerObservationDetailCommitList(
			OffObsCharacteristicsCommitBean offednerObservationDetailCommitList) {
		this.offednerObservationDetailCommitList = offednerObservationDetailCommitList;
	}

	 
}
