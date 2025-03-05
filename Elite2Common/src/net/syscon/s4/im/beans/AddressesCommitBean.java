package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement; 
import net.syscon.s4.common.beans.BaseModel;


/**
 * Class AddressesCommitBean
 */
@XmlRootElement
public class AddressesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Addresses> insertList;
	private List<Addresses> deleteList;
	private List<Addresses> updateList;

	public List<Addresses> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Addresses> insertList) {
		this.insertList = insertList;
	}

	public List<Addresses> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Addresses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Addresses> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Addresses> updateList) {
		this.updateList = updateList;
	}

}
