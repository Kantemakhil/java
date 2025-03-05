package net.syscon.s4.common.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.im.beans.BedAssignmentHistories;
/**
 * Class BedAssignmentHistoriesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class BedAssignmentHistoriesCommitBean extends BaseModel{
	private List<BedAssignmentHistories> insertList;
	private List<BedAssignmentHistories> deleteList;
	private List<BedAssignmentHistories> updateList;

	public void setInsertList(List<BedAssignmentHistories> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<BedAssignmentHistories> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<BedAssignmentHistories> deleteList){
		this.deleteList = deleteList;
	}

	public List<BedAssignmentHistories> getInsertList(){
		return insertList;
	}

	public List<BedAssignmentHistories> getUpdateList(){
		return updateList;
	}

	public List<BedAssignmentHistories> getDeleteList(){
		return deleteList;
	}

}
