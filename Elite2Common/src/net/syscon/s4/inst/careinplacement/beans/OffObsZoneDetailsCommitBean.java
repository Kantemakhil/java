package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsZoneDetailsCommitBean extends BaseModel implements Serializable {

	/**	
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffObsZoneDetails> insertList;

	@JsonProperty("deleteList")
	private List<OffObsZoneDetails> deleteList;

	@JsonProperty("updateList")
	private List<OffObsZoneDetails> updateList;

	public List<OffObsZoneDetails> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<OffObsZoneDetails> insertList) {
		this.insertList = insertList;
	}

	public List<OffObsZoneDetails> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<OffObsZoneDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffObsZoneDetails> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<OffObsZoneDetails> updateList) {
		this.updateList = updateList;
	}

}
