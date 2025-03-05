package net.syscon.s4.common.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class MenuSecuritiesCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MenuSecurities> insertList;
	private List<MenuSecurities> deleteList;
	private List<MenuSecurities> updateList;

	public void setInsertList(final List<MenuSecurities> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<MenuSecurities> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<MenuSecurities> deleteList) {
		this.deleteList = deleteList;
	}

	public List<MenuSecurities> getInsertList() {
		return insertList;
	}

	public List<MenuSecurities> getUpdateList() {
		return updateList;
	}

	public List<MenuSecurities> getDeleteList() {
		return deleteList;
	}

}
