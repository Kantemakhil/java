package net.syscon.s4.common.beans;

import java.util.List;

import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;

public class MovementReasonsCommitBean extends BaseModel{
	
	private List<MovementReasons> insertList;
	private List<MovementReasons> deleteList;
	private List<MovementReasons> updateList;
	
	public List<MovementReasons> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<MovementReasons> insertList) {
		this.insertList = insertList;
	}
	public List<MovementReasons> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<MovementReasons> deleteList) {
		this.deleteList = deleteList;
	}
	public List<MovementReasons> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<MovementReasons> updateList) {
		this.updateList = updateList;
	}


}
