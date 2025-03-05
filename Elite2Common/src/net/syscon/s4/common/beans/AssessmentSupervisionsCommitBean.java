package net.syscon.s4.common.beans;

import java.util.List;

import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;

public class AssessmentSupervisionsCommitBean  extends BaseModel{
	private List<AssessmentSupervisions> insertList;
	private List<AssessmentSupervisions> deleteList;
	private List<AssessmentSupervisions> updateList;

	public void setInsertList(List<AssessmentSupervisions> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AssessmentSupervisions> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AssessmentSupervisions> deleteList){
		this.deleteList = deleteList;
	}

	public List<AssessmentSupervisions> getInsertList(){
		return insertList;
	}

	public List<AssessmentSupervisions> getUpdateList(){
		return updateList;
	}

	public List<AssessmentSupervisions> getDeleteList(){
		return deleteList;
	}


}
