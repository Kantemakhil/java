package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class CaseloadFormatPrintersCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	private List<CaseloadFormatPrinters> insertList;
	private List<CaseloadFormatPrinters> deleteList;
	private List<CaseloadFormatPrinters> updateList;
	public List<CaseloadFormatPrinters> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<CaseloadFormatPrinters> insertList) {
		this.insertList = insertList;
	}
	public List<CaseloadFormatPrinters> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<CaseloadFormatPrinters> deleteList) {
		this.deleteList = deleteList;
	}
	public List<CaseloadFormatPrinters> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<CaseloadFormatPrinters> updateList) {
		this.updateList = updateList;
	}
	
}
