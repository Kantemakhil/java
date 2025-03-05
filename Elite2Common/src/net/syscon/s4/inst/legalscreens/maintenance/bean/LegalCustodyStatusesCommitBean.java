package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class LegalCustodyStatusesCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<LegalCustodyStatuses> insertList;

	@JsonProperty("deleteList")
	private List<LegalCustodyStatuses> deleteList;

	@JsonProperty("updateList")
	private List<LegalCustodyStatuses> updateList;

	public List<LegalCustodyStatuses> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<LegalCustodyStatuses> insertList) {
		this.insertList = insertList;
	}

	public List<LegalCustodyStatuses> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<LegalCustodyStatuses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<LegalCustodyStatuses> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<LegalCustodyStatuses> updateList) {
		this.updateList = updateList;
	}

	

}
