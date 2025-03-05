package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VOffenderVisitsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VOffenderVisits> insertList;
	
	@JsonProperty("updateList")
	private List<VOffenderVisits> updateList;
	
	@JsonProperty("deleteList")
	private List<VOffenderVisits> deleteList;
	
	@JsonProperty("vOffenderVisitVisitorsList")
	private List<VOffenderVisitVisitors> vOffenderVisitVisitorsList;
	
	@JsonProperty("offenderVisitVisitorsList")
	private List<OffenderVisitVisitors> offenderVisitVisitorsList;
	
	public List<VOffenderVisits> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VOffenderVisits> insertList) {
		this.insertList = insertList;
	}

	public List<VOffenderVisits> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VOffenderVisits> updateList) {
		this.updateList = updateList;
	}

	public List<VOffenderVisits> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<VOffenderVisits> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the vOffenderVisitVisitorsList
	 */
	public List<VOffenderVisitVisitors> getvOffenderVisitVisitorsList() {
		return vOffenderVisitVisitorsList;
	}

	/**
	 * @param vOffenderVisitVisitorsList the vOffenderVisitVisitorsList to set
	 */
	public void setvOffenderVisitVisitorsList(List<VOffenderVisitVisitors> vOffenderVisitVisitorsList) {
		this.vOffenderVisitVisitorsList = vOffenderVisitVisitorsList;
	}

	/**
	 * @return the offenderVisitVisitorsList
	 */
	public List<OffenderVisitVisitors> getOffenderVisitVisitorsList() {
		return offenderVisitVisitorsList;
	}

	/**
	 * @param offenderVisitVisitorsList the offenderVisitVisitorsList to set
	 */
	public void setOffenderVisitVisitorsList(List<OffenderVisitVisitors> offenderVisitVisitorsList) {
		this.offenderVisitVisitorsList = offenderVisitVisitorsList;
	}

}
