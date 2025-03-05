package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrievanceReasonsCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<GrievanceReasons> insertList;

	@JsonProperty("deleteList")
	private List<GrievanceReasons> deleteList;

	@JsonProperty("updateList")
	private List<GrievanceReasons> updateList;

	public List<GrievanceReasons> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<GrievanceReasons> insertList) {
		this.insertList = insertList;
	}

	public List<GrievanceReasons> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<GrievanceReasons> deleteList) {
		this.deleteList = deleteList;
	}

	public List<GrievanceReasons> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<GrievanceReasons> updateList) {
		this.updateList = updateList;
	}
	
}
