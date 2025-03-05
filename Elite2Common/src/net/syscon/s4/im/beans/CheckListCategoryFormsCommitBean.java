package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CheckListCategoryFormsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CheckListCategoryForms> insertList;

	@JsonProperty("deleteList")
	private List<CheckListCategoryForms> deleteList;

	@JsonProperty("updateList")
	private List<CheckListCategoryForms> updateList;

	/**
	 * Creates new CheckListCategoryFormsCommitBean class Object
	 */
	public CheckListCategoryFormsCommitBean() {
		// CheckListCategoryFormsCommitBean
	}

	public void setInsertList(final List<CheckListCategoryForms> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CheckListCategoryForms> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CheckListCategoryForms> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CheckListCategoryForms> getInsertList() {
		return insertList;
	}

	public List<CheckListCategoryForms> getUpdateList() {
		return updateList;
	}

	public List<CheckListCategoryForms> getDeleteList() {
		return deleteList;
	}

}
