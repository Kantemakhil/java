package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class ImagePropertiesCommitBean extends BaseBean

 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagePropertiesCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<ImageProperties> insertList;

	@JsonProperty("deleteList")
	private List<ImageProperties> deleteList;

	@JsonProperty("updateList")
	private List<ImageProperties> updateList;

	public void setInsertList(final List<ImageProperties> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<ImageProperties> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<ImageProperties> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ImageProperties> getInsertList() {
		return insertList;
	}

	public List<ImageProperties> getUpdateList() {
		return updateList;
	}

	public List<ImageProperties> getDeleteList() {
		return deleteList;
	}

}
