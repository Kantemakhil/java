/**
 * 
 */
package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageOriginalsCommitBean extends BaseModel {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<ImageOriginals> insertList;

	@JsonProperty("deleteList")
	private List<ImageOriginals> deleteList;

	@JsonProperty("updateList")
	private List<ImageOriginals> updateList;
	

	/**
	 * @return the insertList
	 */
	public List<ImageOriginals> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<ImageOriginals> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<ImageOriginals> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<ImageOriginals> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<ImageOriginals> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<ImageOriginals> updateList) {
		this.updateList = updateList;
	}

	

}
