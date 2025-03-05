package net.syscon.s4.of.payroll.maintenance;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class SystemEventsCommitBean extends BaseBean
 * 
 */
@XmlRootElement
public class SystemEventsCommitBean extends BaseModel {
	private List<SystemEvents> insertList;
	private List<SystemEvents> deleteList;
	private List<SystemEvents> updateList;

	public void setInsertList(final List<SystemEvents> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SystemEvents> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SystemEvents> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SystemEvents> getInsertList() {
		return insertList;
	}

	public List<SystemEvents> getUpdateList() {
		return updateList;
	}

	public List<SystemEvents> getDeleteList() {
		return deleteList;
	}

}
