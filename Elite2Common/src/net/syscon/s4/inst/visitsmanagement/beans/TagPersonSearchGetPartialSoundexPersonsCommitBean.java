package net.syscon.s4.inst.visitsmanagement.beans;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * Class TagPersonSearchGetPartialSoundexPersonsCommitBean extends BaseBean
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagPersonSearchGetPartialSoundexPersonsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TagPersonSearchGetPartialSoundexPersons> insertList;
	private List<TagPersonSearchGetPartialSoundexPersons> deleteList;
	private List<TagPersonSearchGetPartialSoundexPersons> updateList;

	public void setInsertList(List<TagPersonSearchGetPartialSoundexPersons> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<TagPersonSearchGetPartialSoundexPersons> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<TagPersonSearchGetPartialSoundexPersons> deleteList){
		this.deleteList = deleteList;
	}

	public List<TagPersonSearchGetPartialSoundexPersons> getInsertList(){
		return insertList;
	}

	public List<TagPersonSearchGetPartialSoundexPersons> getUpdateList(){
		return updateList;
	}

	public List<TagPersonSearchGetPartialSoundexPersons> getDeleteList(){
		return deleteList;
	}

}
