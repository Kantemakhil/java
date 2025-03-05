package net.syscon.s4.cm.programsservices;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class VOffenderPrgObligationsCommitBean extends BaseModel {

	private static final long serialVersionUID = 1L;

	private List<VOffenderPrgObligations> insertList;
	private List<VOffenderPrgObligations> deleteList;
	private List<VOffenderPrgObligations> updateList;

	public List<VOffenderPrgObligations> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VOffenderPrgObligations> insertList) {
		this.insertList = insertList;
	}

	public List<VOffenderPrgObligations> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<VOffenderPrgObligations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VOffenderPrgObligations> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VOffenderPrgObligations> updateList) {
		this.updateList = updateList;
	}

}
