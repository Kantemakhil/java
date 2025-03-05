package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.beans.Sentences;

public class SentencesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<Sentences> insertList;
	@JsonProperty("deleteList ")
	private List<Sentences> deleteList;
	@JsonProperty("updateList")
	private List<Sentences> updateList;
	@JsonProperty("sealFlag")
	private String sealFlag;

	public void setInsertList(final List<Sentences> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<Sentences> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<Sentences> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Sentences> getInsertList() {
		return insertList;
	}

	public List<Sentences> getUpdateList() {
		return updateList;
	}

	public List<Sentences> getDeleteList() {
		return deleteList;
	}
	
	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
