package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OrderTypeCommitBean extends BaseBean
 */
@XmlRootElement
public class OrderTypesCommitBean extends BaseModel {
	private List<OrderTypes> insertList;
	private List<OrderTypes> deleteList;
	private List<OrderTypes> updateList;

	public void setInsertList(final List<OrderTypes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OrderTypes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OrderTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OrderTypes> getInsertList() {
		return insertList;
	}

	public List<OrderTypes> getUpdateList() {
		return updateList;
	}

	public List<OrderTypes> getDeleteList() {
		return deleteList;
	}

}
