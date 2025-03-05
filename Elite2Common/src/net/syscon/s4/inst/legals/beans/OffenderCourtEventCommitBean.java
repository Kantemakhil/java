package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderCourtEventCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CourtEvent> insertList;

	@JsonProperty("updateList")
	private List<CourtEvent> updateList;
	
	@JsonProperty("selectedCourtcase")
	private CourtCases selectedCourtcase;

	
	/**
	 * Creates new OffenderCasesCommitBean class Object
	 */
	public OffenderCourtEventCommitBean() {
		// OffenderAlertsCommitBean
	}

	public void setInsertList(final List<CourtEvent> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CourtEvent> updateList) {
		this.updateList = updateList;
	}

	public List<CourtEvent> getInsertList() {
		return insertList;
	}

	public List<CourtEvent> getUpdateList() {
		return updateList;
	}
	
	public CourtCases getSelectedCourtcase() {
		return selectedCourtcase;
	}

	public void setSelectedCourtcase(CourtCases selectedCourtcase) {
		this.selectedCourtcase = selectedCourtcase;
	}
	
	

}

