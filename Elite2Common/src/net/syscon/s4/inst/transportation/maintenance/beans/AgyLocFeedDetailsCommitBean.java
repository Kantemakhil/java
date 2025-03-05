package net.syscon.s4.inst.transportation.maintenance.beans;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;
public class AgyLocFeedDetailsCommitBean extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<AgyLocFeedDetails> insertList;
	
	@JsonProperty("deleteList")
	private List<AgyLocFeedDetails> deleteList;
	
	@JsonProperty("updateList")
	private List<AgyLocFeedDetails> updateList;
	
	public void setInsertList(List<AgyLocFeedDetails> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AgyLocFeedDetails> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AgyLocFeedDetails> deleteList){
		this.deleteList = deleteList;
	}

	public List<AgyLocFeedDetails> getInsertList(){
		return insertList;
	}

	public List<AgyLocFeedDetails> getUpdateList(){
		return updateList;
	}

	public List<AgyLocFeedDetails> getDeleteList(){
		return deleteList;
	}
}