package net.syscon.s4.common.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyIncidentAssoTostgCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<AgencyIncidentAssoTostg> insertList;
	@JsonProperty("deleteList")
	private List<AgencyIncidentAssoTostg> deleteList;
	@JsonProperty("updateList")
	private List<AgencyIncidentAssoTostg> updateList;
	
	/**
	 * Creates new OffenderAlerts class Object
	 */
	public AgencyIncidentAssoTostgCommitBean() {
		// AgencyIncidentAssoTostgCommitBean
	}

	public void setInsertList(List<AgencyIncidentAssoTostg> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AgencyIncidentAssoTostg> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AgencyIncidentAssoTostg> deleteList){
		this.deleteList = deleteList;
	}

	public List<AgencyIncidentAssoTostg> getInsertList(){
		return insertList;
	}
	
	public List<AgencyIncidentAssoTostg> getUpdateList(){
		return updateList;
	}

	public List<AgencyIncidentAssoTostg> getDeleteList(){
		return deleteList;
	}

}
