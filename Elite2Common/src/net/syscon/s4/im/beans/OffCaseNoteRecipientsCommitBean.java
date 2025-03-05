package net.syscon.s4.im.beans;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffCaseNoteRecipientsCommitBean extends BaseBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffCaseNoteRecipientsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffCaseNoteRecipients> insertList;
	private List<OffCaseNoteRecipients> deleteList;
	private List<OffCaseNoteRecipients> updateList;

	public void setInsertList(final List<OffCaseNoteRecipients> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffCaseNoteRecipients> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffCaseNoteRecipients> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffCaseNoteRecipients> getInsertList(){
		return insertList;
	}

	public List<OffCaseNoteRecipients> getUpdateList(){
		return updateList;
	}

	public List<OffCaseNoteRecipients> getDeleteList(){
		return deleteList;
	}

}
