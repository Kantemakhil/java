package net.syscon.s4.inst.property.bean;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderPptyContainersCommitBean extends BaseBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderPptyContainersCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderPptyContainers> insertList;
	
	@JsonProperty("deleteList")
	private List<OffenderPptyContainers> deleteList;
	
	@JsonProperty("updateList")
	private List<OffenderPptyContainers> updateList;
	
	public OffenderPptyContainersCommitBean() {
		// OffenderPptyContainersCommitBean
	}

	public void setInsertList(final List<OffenderPptyContainers> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderPptyContainers> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderPptyContainers> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderPptyContainers> getInsertList(){
		return insertList;
	}

	public List<OffenderPptyContainers> getUpdateList(){
		return updateList;
	}

	public List<OffenderPptyContainers> getDeleteList(){
		return deleteList;
	}

}
