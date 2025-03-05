package net.syscon.s4.iwp.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OrderProposals;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VAcpSchedulesCommitBean extends BaseModel{
	@JsonProperty("insertList")
	private List<VAcpSchedules> insertList;
	@JsonProperty("updateList")
	private List<VAcpSchedules> updateList;
	public List<VAcpSchedules> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VAcpSchedules> insertList) {
		this.insertList = insertList;
	}
	public List<VAcpSchedules> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VAcpSchedules> updateList) {
		this.updateList = updateList;
	}
	
	
	
}
