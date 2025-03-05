package net.syscon.s4.inst.movementexternal.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class RpOtherOccupantsCommitBean extends BaseModel {
	private List<RpOtherOccupants> insertList;
	private List<RpOtherOccupants> deleteList;
	private List<RpOtherOccupants> updateList;
	private Addresses addressesBean;

	public void setInsertList(List<RpOtherOccupants> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<RpOtherOccupants> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<RpOtherOccupants> deleteList) {
		this.deleteList = deleteList;
	}

	public List<RpOtherOccupants> getInsertList() {
		return insertList;
	}

	public List<RpOtherOccupants> getUpdateList() {
		return updateList;
	}

	public List<RpOtherOccupants> getDeleteList() {
		return deleteList;
	}

	/**
	 * @return the addressesBean
	 */
	public Addresses getAddressesBean() {
		return addressesBean;
	}

	/**
	 * @param addressesBean the addressesBean to set
	 */
	public void setAddressesBean(Addresses addressesBean) {
		this.addressesBean = addressesBean;
	}
	

}
