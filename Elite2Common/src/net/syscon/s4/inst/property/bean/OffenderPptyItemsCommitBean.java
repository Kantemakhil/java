package net.syscon.s4.inst.property.bean;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderPptyItemsCommitBean extends BaseBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderPptyItemsCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderPptyItems> insertList;
	
	@JsonProperty("deleteList")
	private List<OffenderPptyItems> deleteList;
	
	@JsonProperty("updateList")
	private List<OffenderPptyItems> updateList;
	
	

	public void setInsertList(final List<OffenderPptyItems> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderPptyItems> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderPptyItems> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderPptyItems> getInsertList(){
		return insertList;
	}

	public List<OffenderPptyItems> getUpdateList(){
		return updateList;
	}

	public List<OffenderPptyItems> getDeleteList(){
		return deleteList;
	}

}
