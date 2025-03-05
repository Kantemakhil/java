package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class LegalUpdateUsagesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<LegalUpdateUsages> insertList;

	@JsonProperty("deleteList")
	private List<LegalUpdateUsages> deleteList;

	@JsonProperty("updateList")
	private List<LegalUpdateUsages> updateList;

	/**
	 * Creates new LegalUpdateUsagesCommitBean class Object
	 */
	public LegalUpdateUsagesCommitBean() {
		// LegalUpdateUsagesCommitBean
	}

	public void setInsertList(final List<LegalUpdateUsages> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<LegalUpdateUsages> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<LegalUpdateUsages> deleteList) {
		this.deleteList = deleteList;
	}

	public List<LegalUpdateUsages> getInsertList() {
		return insertList;
	}

	public List<LegalUpdateUsages> getUpdateList() {
		return updateList;
	}

	public List<LegalUpdateUsages> getDeleteList() {
		return deleteList;
	}

}
