package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class StatutesCommitBean extends BaseBean
 * 
 */
@XmlRootElement
public class StatutesCommitBean extends BaseModel {
	private List<Statutes> insertList;
	private List<Statutes> deleteList;
	private List<Statutes> updateList;

	public void setInsertList(final List<Statutes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<Statutes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<Statutes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Statutes> getInsertList() {
		return insertList;
	}

	public List<Statutes> getUpdateList() {
		return updateList;
	}

	public List<Statutes> getDeleteList() {
		return deleteList;
	}

}
