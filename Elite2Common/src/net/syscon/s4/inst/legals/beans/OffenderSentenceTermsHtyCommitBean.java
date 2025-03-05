package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderSentenceTermsHtyCommitBean extends BaseBean

 */
@XmlRootElement
public class OffenderSentenceTermsHtyCommitBean extends BaseModel implements Serializable{
	private List<OffenderSentenceTermsHty> insertList;
	private List<OffenderSentenceTermsHty> deleteList;
	private List<OffenderSentenceTermsHty> updateList;

	public void setInsertList(List<OffenderSentenceTermsHty> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderSentenceTermsHty> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderSentenceTermsHty> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderSentenceTermsHty> getInsertList(){
		return insertList;
	}

	public List<OffenderSentenceTermsHty> getUpdateList(){
		return updateList;
	}

	public List<OffenderSentenceTermsHty> getDeleteList(){
		return deleteList;
	}

}
