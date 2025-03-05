package net.syscon.s4.inst.schedules.bean;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VCorporateAddressesCommitBean extends BaseBean
 */
@XmlRootElement
public class VCorporateAddressesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<VCorporateAddresses> insertList;
	private List<VCorporateAddresses> deleteList;
	private List<VCorporateAddresses> updateList;

	public void setInsertList(List<VCorporateAddresses> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<VCorporateAddresses> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<VCorporateAddresses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VCorporateAddresses> getInsertList() {
		return insertList;
	}

	public List<VCorporateAddresses> getUpdateList() {
		return updateList;
	}

	public List<VCorporateAddresses> getDeleteList() {
		return deleteList;
	}

}
