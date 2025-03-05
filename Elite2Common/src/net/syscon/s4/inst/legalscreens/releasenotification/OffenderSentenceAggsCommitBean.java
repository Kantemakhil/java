package net.syscon.s4.inst.legalscreens.releasenotification;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderSentenceAggsCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OffenderSentenceAggs> insertList;
	private List<OffenderSentenceAggs> deleteList;
	private List<OffenderSentenceAggs> updateList;
	
	public List<OffenderSentenceAggs> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<OffenderSentenceAggs> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderSentenceAggs> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<OffenderSentenceAggs> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderSentenceAggs> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<OffenderSentenceAggs> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
