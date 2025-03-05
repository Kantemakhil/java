package net.syscon.s4.cf.deductions.beans;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class FeeAccountProfilesCommitBean extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<FeeAccountProfiles> insertList;
	@JsonProperty("deleteList")
	private List<FeeAccountProfiles> deleteList;
	@JsonProperty("updateList")
	private List<FeeAccountProfiles> updateList;
	
	public List<FeeAccountProfiles> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<FeeAccountProfiles> insertList) {
		this.insertList = insertList;
	}
	public List<FeeAccountProfiles> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<FeeAccountProfiles> deleteList) {
		this.deleteList = deleteList;
	}
	public List<FeeAccountProfiles> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<FeeAccountProfiles> updateList) {
		this.updateList = updateList;
	}
	
	

}
