package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderProfileDetailsCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */

public class OffenderProfileDetailsCommitBean extends BaseModel implements Serializable{
	private List<OffenderProfileDetails> insertList;
	private List<OffenderProfileDetails> deleteList;
	private List<OffenderProfileDetails> updateList;

	public void setInsertList(final List<OffenderProfileDetails> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderProfileDetails> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderProfileDetails> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderProfileDetails> getInsertList(){
		return insertList;
	}

	public List<OffenderProfileDetails> getUpdateList(){
		return updateList;
	}

	public List<OffenderProfileDetails> getDeleteList(){
		return deleteList;
	}

}
