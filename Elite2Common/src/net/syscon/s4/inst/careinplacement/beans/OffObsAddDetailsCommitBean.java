package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsAddDetailsCommitBean extends BaseModel implements Serializable {

	/**	
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffObsAddDetails> insertList;
	
	@JsonProperty("deleteList")
	private List<OffObsAddDetails> deleteList;

	@JsonProperty("updateList")
	private List<OffObsAddDetails> updateList;

	public List<OffObsAddDetails> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<OffObsAddDetails> insertList) {
		this.insertList = insertList;
	}

	public List<OffObsAddDetails> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<OffObsAddDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffObsAddDetails> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<OffObsAddDetails> updateList) {
		this.updateList = updateList;
	}

}
