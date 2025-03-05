package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class OffenderOicSanctionsCommitBean extends BaseBean
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderOicSanctionsCommitBean extends BaseModel {

	@JsonProperty("insertList")
	private List<OffenderOicSanctions> insertList;

	@JsonProperty("deleteList")
	private List<OffenderOicSanctions> deleteList;

	@JsonProperty("updateList")
	private List<OffenderOicSanctions> updateList;

	public void setInsertList(List<OffenderOicSanctions> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderOicSanctions> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderOicSanctions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderOicSanctions> getInsertList() {
		return insertList;
	}

	public List<OffenderOicSanctions> getUpdateList() {
		return updateList;
	}

	public List<OffenderOicSanctions> getDeleteList() {
		return deleteList;
	}

}
