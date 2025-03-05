package net.syscon.s4.common.beans;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Class VNameSearchCommitBean extends BaseBean
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VNameSearchCommitBean extends BaseModel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@JsonProperty("insertList")
	private List<VNameSearch> insertList;
	@JsonProperty("deleteList")
	private List<VNameSearch> deleteList;
	@JsonProperty("updateList")
	private List<VNameSearch> updateList;

	public void setInsertList(List<VNameSearch> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VNameSearch> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VNameSearch> deleteList){
		this.deleteList = deleteList;
	}

	public List<VNameSearch> getInsertList(){
		return insertList;
	}

	public List<VNameSearch> getUpdateList(){
		return updateList;
	}

	public List<VNameSearch> getDeleteList(){
		return deleteList;
	}

}
