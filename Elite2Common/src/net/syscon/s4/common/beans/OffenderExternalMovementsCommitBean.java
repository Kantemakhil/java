package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderExternalMovementsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderExternalMovements> insertList;
	@JsonProperty("deleteList")
	private List<OffenderExternalMovements> deleteList;
	@JsonProperty("updateList")
	private List<OffenderExternalMovements> updateList;
	
	private String authorization;
	
	

	/**
	 * Creates new OffenderExternalMovementsCommitBean class Object
	 */
	public OffenderExternalMovementsCommitBean() {
		// Super();
	}

	public void setInsertList(final List<OffenderExternalMovements> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderExternalMovements> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderExternalMovements> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderExternalMovements> getInsertList() {
		return insertList;
	}

	public List<OffenderExternalMovements> getUpdateList() {
		return updateList;
	}

	public List<OffenderExternalMovements> getDeleteList() {
		return deleteList;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}


}
