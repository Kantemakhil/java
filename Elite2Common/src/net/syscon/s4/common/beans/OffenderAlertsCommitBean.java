package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.OffenderAlerts;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderAlertsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderAlerts> insertList;

	@JsonProperty("deleteList")
	private List<OffenderAlerts> deleteList;

	@JsonProperty("updateList")
	private List<OffenderAlerts> updateList;

	/**
	 * Creates new OffenderAlertsCommitBean class Object
	 */
	public OffenderAlertsCommitBean() {
		// OffenderAlertsCommitBean
	}

	public void setInsertList(final List<OffenderAlerts> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderAlerts> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderAlerts> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderAlerts> getInsertList() {
		return insertList;
	}

	public List<OffenderAlerts> getUpdateList() {
		return updateList;
	}

	public List<OffenderAlerts> getDeleteList() {
		return deleteList;
	}

}
