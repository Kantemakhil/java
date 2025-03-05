package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.beans.Terms;

public class TermsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<Terms> insertList;
	@JsonProperty("deleteList ")
	private List<Terms> deleteList;
	@JsonProperty("updateList")
	private List<Terms> updateList;
	@JsonProperty("sealFlag")
	private String sealFlag;

	public void setInsertList(final List<Terms> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<Terms> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<Terms> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Terms> getInsertList() {
		return insertList;
	}

	public List<Terms> getUpdateList() {
		return updateList;
	}

	public List<Terms> getDeleteList() {
		return deleteList;
	}
	
	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
