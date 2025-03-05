package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderMilitaryWarZonesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderMilitaryWarZones> insertList;

	@JsonProperty("deleteList")
	private List<OffenderMilitaryWarZones> deleteList;

	@JsonProperty("updateList")
	private List<OffenderMilitaryWarZones> updateList;

	/**
	 * Creates new OffenderMilitaryWarZonesCommitBean class Object
	 */
	public OffenderMilitaryWarZonesCommitBean() {
		// OffenderMilitaryWarZonesCommitBean
	}

	public void setInsertList(final List<OffenderMilitaryWarZones> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderMilitaryWarZones> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderMilitaryWarZones> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderMilitaryWarZones> getInsertList() {
		return insertList;
	}

	public List<OffenderMilitaryWarZones> getUpdateList() {
		return updateList;
	}

	public List<OffenderMilitaryWarZones> getDeleteList() {
		return deleteList;
	}

}
