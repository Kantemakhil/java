package net.syscon.s4.inst.legalscreens.bean;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OcdclistCourtListQueryCommitBean extends BaseBean
 */
@XmlRootElement
public class OcdclistCourtListQueryCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OcdclistCourtListQuery> insertList;
	private List<OcdclistCourtListQuery> deleteList;
	private List<OcdclistCourtListQuery> updateList;

	public void setInsertList(List<OcdclistCourtListQuery> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OcdclistCourtListQuery> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OcdclistCourtListQuery> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OcdclistCourtListQuery> getInsertList() {
		return insertList;
	}

	public List<OcdclistCourtListQuery> getUpdateList() {
		return updateList;
	}

	public List<OcdclistCourtListQuery> getDeleteList() {
		return deleteList;
	}

}
