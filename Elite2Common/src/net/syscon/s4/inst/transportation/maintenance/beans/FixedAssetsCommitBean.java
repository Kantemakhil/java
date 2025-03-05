package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class FixedAssetsCommitBean extends BaseBean
 * 
 * @author Vrnda Software Technologies
 * @version 1.0
 */
public class FixedAssetsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<FixedAssets> insertList;
	private List<FixedAssets> deleteList;
	private List<FixedAssets> updateList;

	public void setInsertList(List<FixedAssets> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<FixedAssets> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<FixedAssets> deleteList) {
		this.deleteList = deleteList;
	}

	public List<FixedAssets> getInsertList() {
		return insertList;
	}

	public List<FixedAssets> getUpdateList() {
		return updateList;
	}

	public List<FixedAssets> getDeleteList() {
		return deleteList;
	}

}
