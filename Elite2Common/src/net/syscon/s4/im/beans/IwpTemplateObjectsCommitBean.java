package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class IwpTemplateObjectsCommitBean extends BaseBean
 */
@XmlRootElement
public class IwpTemplateObjectsCommitBean extends BaseModel {
	private List<IwpTemplateObjects> insertList;
	private List<IwpTemplateObjects> deleteList;
	private List<IwpTemplateObjects> updateList;

	public void setInsertList(final List<IwpTemplateObjects> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<IwpTemplateObjects> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<IwpTemplateObjects> deleteList) {
		this.deleteList = deleteList;
	}

	public List<IwpTemplateObjects> getInsertList() {
		return insertList;
	}

	public List<IwpTemplateObjects> getUpdateList() {
		return updateList;
	}

	public List<IwpTemplateObjects> getDeleteList() {
		return deleteList;
	}

}
