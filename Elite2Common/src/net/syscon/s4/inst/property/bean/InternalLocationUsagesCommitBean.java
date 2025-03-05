package net.syscon.s4.inst.property.bean;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class InternalLocationUsagesCommitBean extends BaseBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalLocationUsagesCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<InternalLocationUsages> insertList;
	
	@JsonProperty("deleteList")
	private List<InternalLocationUsages> deleteList;
	
	@JsonProperty("updateList")
	private List<InternalLocationUsages> updateList;
	
	public InternalLocationUsagesCommitBean() {
		// InternalLocationUsagesCommitBean
	}

	public void setInsertList(final List<InternalLocationUsages> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<InternalLocationUsages> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<InternalLocationUsages> deleteList){
		this.deleteList = deleteList;
	}

	public List<InternalLocationUsages> getInsertList(){
		return insertList;
	}

	public List<InternalLocationUsages> getUpdateList(){
		return updateList;
	}

	public List<InternalLocationUsages> getDeleteList(){
		return deleteList;
	}

}
