package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@GlobalValidation(message = "atleast.one.mandatory")
public class OdynfrmCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<FormBuilderBean> insertList;
	@JsonProperty("deleteList")
	private List<FormBuilderBean> deleteList;
	@JsonProperty("updateList")
	private List<FormBuilderBean> updateList;

	
	/**
	 * Creates new OdynfrmCommitBean class Object
	 */
	public OdynfrmCommitBean() {
		// TODO: CaseloadsCommitBean
	}
	
	public void setInsertList(final List<FormBuilderBean> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<FormBuilderBean> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<FormBuilderBean> deleteList) {
		this.deleteList = deleteList;
	}

	public List<FormBuilderBean> getInsertList() {
		return insertList;
	}

	public List<FormBuilderBean> getUpdateList() {
		return updateList;
	}

	public List<FormBuilderBean> getDeleteList() {
		return deleteList;
	}

}