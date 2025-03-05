package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OicHearingsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OicHearings> insertList;

	@JsonProperty("deleteList")
	private List<OicHearings> deleteList;

	@JsonProperty("updateList")
	private List<OicHearings> updateList;

	/**
	 * Creates new OicHearingsCommitBean class Object
	 */
	public OicHearingsCommitBean() {

		// OicHearingsCommitBean();
	}

	public void setInsertList(final List<OicHearings> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OicHearings> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OicHearings> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OicHearings> getInsertList() {
		return insertList;
	}

	public List<OicHearings> getUpdateList() {
		return updateList;
	}

	public List<OicHearings> getDeleteList() {
		return deleteList;
	}

	@Override
	public String toString() {
		return "OicHearingsCommitBean [insertList=" + insertList + ", deleteList=" + deleteList + ", updateList="
				+ updateList + "]";
	}
	

}
