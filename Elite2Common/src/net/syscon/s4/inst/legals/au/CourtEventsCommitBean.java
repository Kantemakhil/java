package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.beans.CourtEvent;

public class CourtEventsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<CourtEvent> insertList;
	@JsonProperty("deleteList ")
	private List<CourtEvent> deleteList;
	@JsonProperty("updateList")
	private List<CourtEvent> updateList;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	public String getAgyLocId() {
		return this.agyLocId;
	}
	
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public void setInsertList(final List<CourtEvent> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CourtEvent> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CourtEvent> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CourtEvent> getInsertList() {
		return insertList;
	}

	public List<CourtEvent> getUpdateList() {
		return updateList;
	}

	public List<CourtEvent> getDeleteList() {
		return deleteList;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
