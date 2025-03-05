package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class NameSynonymsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<NameSynonyms> insertList;
	private List<NameSynonyms> deleteList;
	private List<NameSynonyms> updateList;

	public void setInsertList(List<NameSynonyms> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<NameSynonyms> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<NameSynonyms> deleteList) {
		this.deleteList = deleteList;
	}

	public List<NameSynonyms> getInsertList() {
		return insertList;
	}

	public List<NameSynonyms> getUpdateList() {
		return updateList;
	}

	public List<NameSynonyms> getDeleteList() {
		return deleteList;
	}

}
