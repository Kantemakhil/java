package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.IwpTemplates;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class IwpTemplatesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<IwpTemplates> insertList;
	@JsonProperty("deleteList")
	private List<IwpTemplates> deleteList;
	@JsonProperty("updateList")
	private List<IwpTemplates> updateList;
	@JsonProperty("IwpTemplatesList")
	private List<IwpTemplates> IwpTemplatesList;

	public void setInsertList(List<IwpTemplates> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<IwpTemplates> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<IwpTemplates> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the IwpTemplatesList
	 */
	public List<IwpTemplates> getIwpTemplatesList() {
		return IwpTemplatesList;
	}

	/**
	 * @param IwpTemplatesList
	 *            the IwpTemplatesList to set
	 */
	public void setIwpTemplatesList(List<IwpTemplates> IwpTemplatesList) {
		this.IwpTemplatesList = IwpTemplatesList;
	}

	public List<IwpTemplates> getInsertList() {
		return insertList;
	}

	public List<IwpTemplates> getUpdateList() {
		return updateList;
	}

	public List<IwpTemplates> getDeleteList() {
		return deleteList;
	}

}
