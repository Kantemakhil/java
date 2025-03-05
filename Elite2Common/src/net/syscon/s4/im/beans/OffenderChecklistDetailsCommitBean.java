package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderChecklistDetailsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderChecklistDetails> insertList;

	@JsonProperty("deleteList")
	private List<OffenderChecklistDetails> deleteList;

	@JsonProperty("updateList")
	private List<OffenderChecklistDetails> updateList;

	/**
	 * Creates new OffenderChecklistDetailsCommitBean class Object
	 */
	public OffenderChecklistDetailsCommitBean() {
		// OffenderChecklistDetailsCommitBean
	}

	public void setInsertList(final List<OffenderChecklistDetails> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderChecklistDetails> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderChecklistDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderChecklistDetails> getInsertList() {
		return insertList;
	}

	public List<OffenderChecklistDetails> getUpdateList() {
		return updateList;
	}

	public List<OffenderChecklistDetails> getDeleteList() {
		return deleteList;
	}

}
