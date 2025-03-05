package net.syscon.s4.cm.assesments.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.classification.beans.AssessmentResults;

public class AssessmentResultsCommitBean extends BaseModel  implements Serializable {

	private List<AssessmentResults> insertList;
	private List<AssessmentResults> deleteList;
	private List<AssessmentResults> updateList;

	public void setInsertList(List<AssessmentResults> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<AssessmentResults> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<AssessmentResults> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AssessmentResults> getInsertList() {
		return insertList;
	}

	public List<AssessmentResults> getUpdateList() {
		return updateList;
	}

	public List<AssessmentResults> getDeleteList() {
		return deleteList;
	}

}
