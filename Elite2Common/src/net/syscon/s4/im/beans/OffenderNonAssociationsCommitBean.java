package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderNonAssociationsCommitBean extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<OffenderNonAssociations> insertList;
	private List<OffenderNonAssociations> deleteList;
	private List<OffenderNonAssociations> updateList;
	
	@JsonProperty("offNadInsertList")
	private List<OffenderNaDetails> offNadInsertList;
	@JsonProperty("offNadUdateList")
    private List<OffenderNaDetails> offNadUdateList;

	public List<OffenderNaDetails> getOffNadInsertList() {
		return offNadInsertList;
	}

	public void setOffNadInsertList(List<OffenderNaDetails> offNadInsertList) {
		this.offNadInsertList = offNadInsertList;
	}

	public List<OffenderNaDetails> getOffNadUdateList() {
		return offNadUdateList;
	}

	public void setOffNadUdateList(List<OffenderNaDetails> offNadUdateList) {
		this.offNadUdateList = offNadUdateList;
	}

	public void setInsertList(final List<OffenderNonAssociations> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderNonAssociations> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderNonAssociations> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderNonAssociations> getInsertList(){
		return insertList;
	}

	public List<OffenderNonAssociations> getUpdateList(){
		return updateList;
	}

	public List<OffenderNonAssociations> getDeleteList(){
		return deleteList;
	}
}
