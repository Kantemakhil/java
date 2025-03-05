package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GrievanceTypesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<GrievanceTypes> insertList;

	@JsonProperty("deleteList")
	private List<GrievanceTypes> deleteList;

	@JsonProperty("updateList")
	private List<GrievanceTypes> updateList;

	public List<GrievanceTypes> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<GrievanceTypes> insertList) {
		this.insertList = insertList;
	}

	public List<GrievanceTypes> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<GrievanceTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<GrievanceTypes> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<GrievanceTypes> updateList) {
		this.updateList = updateList;
	}
	
}