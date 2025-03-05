package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationZonesCommitBean extends BaseModel implements Serializable {

	/**	
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderObservationZones> insertList;
	
	@JsonProperty("deleteList")
	private List<OffenderObservationZones> deleteList;

	@JsonProperty("updateList")
	private List<OffenderObservationZones> updateList;

	public List<OffenderObservationZones> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<OffenderObservationZones> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderObservationZones> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<OffenderObservationZones> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderObservationZones> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<OffenderObservationZones> updateList) {
		this.updateList = updateList;
	}

}
