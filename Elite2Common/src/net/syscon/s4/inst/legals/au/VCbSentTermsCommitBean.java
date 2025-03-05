package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VCbSentTermsCommitBean extends BaseModel  implements Serializable{ 
	private static final long serialVersionUID = 1L;

	private List<VCbSentTerms> insertList;
	private List<VCbSentTerms> updateList;
	private List<VCbSentTerms> deleteList;
	
	public List<VCbSentTerms> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VCbSentTerms> insertList) {
		this.insertList = insertList;
	}
	public List<VCbSentTerms> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VCbSentTerms> updateList) {
		this.updateList = updateList;
	}
	public List<VCbSentTerms> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VCbSentTerms> deleteList) {
		this.deleteList = deleteList;
	}

}