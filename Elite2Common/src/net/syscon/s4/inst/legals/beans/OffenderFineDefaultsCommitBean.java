package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderFineDefaultsCommitBean extends BaseBean
 */
@XmlRootElement
public class OffenderFineDefaultsCommitBean extends BaseModel implements Serializable{
	private List<OffenderFineDefaults> insertList;
	private List<OffenderFineDefaults> deleteList;
	private List<OffenderFineDefaults> updateList;
	private OffenderSentencesCommitBean offenderSentencesCommitBean;
	public void setInsertList(List<OffenderFineDefaults> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderFineDefaults> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderFineDefaults> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderFineDefaults> getInsertList(){
		return insertList;
	}

	public List<OffenderFineDefaults> getUpdateList(){
		return updateList;
	}

	public List<OffenderFineDefaults> getDeleteList(){
		return deleteList;
	}

	public OffenderSentencesCommitBean getOffenderSentencesCommitBean() {
		return offenderSentencesCommitBean;
	}

	public void setOffenderSentencesCommitBean(OffenderSentencesCommitBean offenderSentencesCommitBean) {
		this.offenderSentencesCommitBean = offenderSentencesCommitBean;
	}

	

}
