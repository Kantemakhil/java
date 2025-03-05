package net.syscon.s4.inst.shiftlogs.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffendersShiftLogCommitBean extends BaseModel implements Serializable {
	
	@JsonProperty("insertList")
	private List<OffendersShiftLog> insertList;

	@JsonProperty("updateList")
	private List<OffendersShiftLog> updateList;
	
	public List<OffendersShiftLog> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffendersShiftLog> insertList) {
		this.insertList = insertList;
	}

	public List<OffendersShiftLog> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffendersShiftLog> updateList) {
		this.updateList = updateList;
	}
	

}
