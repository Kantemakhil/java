package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderNaDetailsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<OffenderNaDetails> insertList;
	private List<OffenderNaDetails> deleteList;
	private List<OffenderNaDetails> updateList;

	public void setInsertList(final List<OffenderNaDetails> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderNaDetails> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderNaDetails> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderNaDetails> getInsertList(){
		return insertList;
	}

	public List<OffenderNaDetails> getUpdateList(){
		return updateList;
	}

	public List<OffenderNaDetails> getDeleteList(){
		return deleteList;
	}
}
