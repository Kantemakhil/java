package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsCharacteristicsCommitBean  extends BaseModel implements Serializable {

	/**	
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffObsCharacteristics> insertList;
	
	@JsonProperty("deleteList")
	private List<OffObsCharacteristics> deleteList;

	@JsonProperty("updateList")
	private List<OffObsCharacteristics> updateList;

	public List<OffObsCharacteristics> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<OffObsCharacteristics> insertList) {
		this.insertList = insertList;
	}

	public List<OffObsCharacteristics> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<OffObsCharacteristics> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffObsCharacteristics> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<OffObsCharacteristics> updateList) {
		this.updateList = updateList;
	}
}
