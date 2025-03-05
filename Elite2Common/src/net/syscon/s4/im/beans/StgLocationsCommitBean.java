package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class StgLocationsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<StgLocations> insertList;
	private List<StgLocations> deleteList;
	private List<StgLocations> updateList;

	public void setInsertList(final List<StgLocations> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StgLocations> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StgLocations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<StgLocations> getInsertList() {
		return insertList;
	}

	public List<StgLocations> getUpdateList() {
		return updateList;
	}

	public List<StgLocations> getDeleteList() {
		return deleteList;
	}

}
