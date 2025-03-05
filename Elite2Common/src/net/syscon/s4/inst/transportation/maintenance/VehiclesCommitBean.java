package net.syscon.s4.inst.transportation.maintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VehiclesCommitBean extends BaseBean
 * 
 * @author Vrnda Software Technologies
 * @version 1.0
 */
public class VehiclesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Vehicles> insertList;
	private List<Vehicles> deleteList;
	private List<Vehicles> updateList;

	public void setInsertList(List<Vehicles> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<Vehicles> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<Vehicles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Vehicles> getInsertList() {
		return insertList;
	}

	public List<Vehicles> getUpdateList() {
		return updateList;
	}

	public List<Vehicles> getDeleteList() {
		return deleteList;
	}

}
