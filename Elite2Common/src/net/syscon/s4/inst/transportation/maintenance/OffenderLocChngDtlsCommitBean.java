package net.syscon.s4.inst.transportation.maintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderLocChngDtlsCommitBean extends BaseModel implements Serializable {
	
	private List<OffenderLocChngDtls> insertList;
	private List<OffenderLocChngDtls> deleteList;
	private List<OffenderLocChngDtls> updateList;
	
	public List<OffenderLocChngDtls> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderLocChngDtls> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderLocChngDtls> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderLocChngDtls> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderLocChngDtls> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderLocChngDtls> updateList) {
		this.updateList = updateList;
	}
}
