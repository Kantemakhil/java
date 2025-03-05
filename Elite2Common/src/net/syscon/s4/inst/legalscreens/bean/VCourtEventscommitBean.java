package net.syscon.s4.inst.legalscreens.bean;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VCourtEventscommitBean
 */
public class VCourtEventscommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VCourtEvents> insertList;
	private List<VCourtEvents> deleteList;
	private List<VCourtEvents> updateList;

	public void setInsertList(List<VCourtEvents> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<VCourtEvents> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<VCourtEvents> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VCourtEvents> getInsertList() {
		return insertList;
	}

	public List<VCourtEvents> getUpdateList() {
		return updateList;
	}

	public List<VCourtEvents> getDeleteList() {
		return deleteList;
	}

}
