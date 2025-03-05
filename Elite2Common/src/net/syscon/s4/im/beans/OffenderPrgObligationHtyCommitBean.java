package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderPrgObligationHtyCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<OffenderPrgObligationHty> insertList;
	private List<OffenderPrgObligationHty> deleteList;
	private List<OffenderPrgObligationHty> updateList;
	public List<OffenderPrgObligationHty> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderPrgObligationHty> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderPrgObligationHty> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderPrgObligationHty> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderPrgObligationHty> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderPrgObligationHty> updateList) {
		this.updateList = updateList;
	}
	
	

}
