package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderLanguagesCommitBean extends BaseModel
 */
@XmlRootElement
public class OffenderLanguagesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<OffenderLanguages> insertList;
	private List<OffenderLanguages> deleteList;
	private List<OffenderLanguages> updateList;

	/**
	 * Creates new OffenderLanguagesCommitBean class Object
	 */
	public OffenderLanguagesCommitBean() {
		// Super();
	}

	public void setInsertList(final List<OffenderLanguages> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderLanguages> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderLanguages> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderLanguages> getInsertList() {
		return insertList;
	}

	public List<OffenderLanguages> getUpdateList() {
		return updateList;
	}

	public List<OffenderLanguages> getDeleteList() {
		return deleteList;
	}

}
