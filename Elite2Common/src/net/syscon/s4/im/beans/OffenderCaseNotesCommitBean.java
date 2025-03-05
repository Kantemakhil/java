package net.syscon.s4.im.beans;
import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.util.List;
/**
 * Class OffenderCaseNotesCommitBean extends BaseBean
 */
@XmlRootElement
public class OffenderCaseNotesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderCaseNotes> insertList;
	private List<OffenderCaseNotes> deleteList;
	private List<OffenderCaseNotes> updateList;

	public void setInsertList(final List<OffenderCaseNotes> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderCaseNotes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderCaseNotes> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderCaseNotes> getInsertList(){
		return insertList;
	}

	public List<OffenderCaseNotes> getUpdateList(){
		return updateList;
	}

	public List<OffenderCaseNotes> getDeleteList(){
		return deleteList;
	}

}
