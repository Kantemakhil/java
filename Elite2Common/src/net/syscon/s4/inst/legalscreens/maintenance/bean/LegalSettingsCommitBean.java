package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class LegalSettingsCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<LegalSettings> insertList;

	@JsonProperty("deleteList")
	private List<LegalSettings> deleteList;

	@JsonProperty("updateList")
	private List<LegalSettings> updateList;

	public List<LegalSettings> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<LegalSettings> insertList) {
		this.insertList = insertList;
	}

	public List<LegalSettings> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<LegalSettings> deleteList) {
		this.deleteList = deleteList;
	}

	public List<LegalSettings> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<LegalSettings> updateList) {
		this.updateList = updateList;
	}

}
