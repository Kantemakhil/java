package net.syscon.s4.common.beans;
import java.io.Serializable;
import java.util.List;
/**
 * Class OffenderIdentifyingMarksCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */

public class OffenderIdentifyingMarksCommitBean extends BaseModel implements Serializable{
	private List<OffenderIdentifyingMark> insertList;
	private List<OffenderIdentifyingMark> deleteList;
	private List<OffenderIdentifyingMark> updateList;

	public void setInsertList(final List<OffenderIdentifyingMark> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderIdentifyingMark> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderIdentifyingMark> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderIdentifyingMark> getInsertList(){
		return insertList;
	}

	public List<OffenderIdentifyingMark> getUpdateList(){
		return updateList;
	}

	public List<OffenderIdentifyingMark> getDeleteList(){
		return deleteList;
	}

}
