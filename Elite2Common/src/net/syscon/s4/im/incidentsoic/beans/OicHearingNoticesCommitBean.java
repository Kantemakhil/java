package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OicHearingNoticesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OicHearingNotices> insertList;

	@JsonProperty("deleteList")
	private List<OicHearingNotices> deleteList;

	@JsonProperty("updateList")
	private List<OicHearingNotices> updateList;

	/**
	 * Creates new OicHearingNoticesCommitBean class Object
	 */
	public OicHearingNoticesCommitBean() {
		// Super();
	}

	public void setInsertList(final List<OicHearingNotices> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OicHearingNotices> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OicHearingNotices> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OicHearingNotices> getInsertList() {
		return insertList;
	}

	public List<OicHearingNotices> getUpdateList() {
		return updateList;
	}

	public List<OicHearingNotices> getDeleteList() {
		return deleteList;
	}

}
