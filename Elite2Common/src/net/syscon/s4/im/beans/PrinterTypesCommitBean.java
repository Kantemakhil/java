package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class PrinterTypesCommitBean extends BaseBean
 */

public class PrinterTypesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<PrinterTypes> insertList;
	private List<PrinterTypes> deleteList;
	private List<PrinterTypes> updateList;

	public void setInsertList(final List<PrinterTypes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<PrinterTypes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<PrinterTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<PrinterTypes> getInsertList() {
		return insertList;
	}

	public List<PrinterTypes> getUpdateList() {
		return updateList;
	}

	public List<PrinterTypes> getDeleteList() {
		return deleteList;
	}

}
