package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderMilitaryRecordsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderMilitaryRecords> insertList;

	@JsonProperty("deleteList")
	private List<OffenderMilitaryRecords> deleteList;

	@JsonProperty("updateList")
	private List<OffenderMilitaryRecords> updateList;

	/**
	 * Creates new OffenderMilitaryRecordsCommitBean class Object
	 */
	public OffenderMilitaryRecordsCommitBean() {
		// OffenderMilitaryRecordsCommitBean
	}

	public void setInsertList(final List<OffenderMilitaryRecords> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderMilitaryRecords> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderMilitaryRecords> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderMilitaryRecords> getInsertList() {
		return insertList;
	}

	public List<OffenderMilitaryRecords> getUpdateList() {
		return updateList;
	}

	public List<OffenderMilitaryRecords> getDeleteList() {
		return deleteList;
	}

}
