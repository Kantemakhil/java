package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class PrintingAbilitiesCommitBean extends BaseBean
 */
@XmlRootElement
public class PrintingAbilitiesCommitBean extends BaseModel {
	private List<PrintingAbilities> insertList;
	private List<PrintingAbilities> deleteList;
	private List<PrintingAbilities> updateList;

	public void setInsertList(final List<PrintingAbilities> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<PrintingAbilities> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<PrintingAbilities> deleteList) {
		this.deleteList = deleteList;
	}

	public List<PrintingAbilities> getInsertList() {
		return insertList;
	}

	public List<PrintingAbilities> getUpdateList() {
		return updateList;
	}

	public List<PrintingAbilities> getDeleteList() {
		return deleteList;
	}

}
