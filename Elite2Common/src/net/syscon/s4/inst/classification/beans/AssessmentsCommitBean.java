package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderAssessments;

@XmlRootElement
public class AssessmentsCommitBean extends BaseModel  implements Serializable  {
	private List<Assessments> insertList;
	private List<Assessments> deleteList;
	private List<Assessments> updateList;
	private List<Assessments> assesList;
	private List<Assessments> assesQuestList;
	private List<Assessments> assesAnsList;
	private OffenderAssessments offAssesModel;
	private Boolean enforceFlag;
	public void setInsertList(List<Assessments> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<Assessments> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<Assessments> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Assessments> getInsertList() {
		return insertList;
	}

	public List<Assessments> getUpdateList() {
		return updateList;
	}

	public List<Assessments> getDeleteList() {
		return deleteList;
	}

	/**
	 * @return the assesList
	 */
	public List<Assessments> getAssesList() {
		return assesList;
	}

	/**
	 * @param assesList the assesList to set
	 */
	public void setAssesList(List<Assessments> assesList) {
		this.assesList = assesList;
	}

	/**
	 * @return the assesQuestList
	 */
	public List<Assessments> getAssesQuestList() {
		return assesQuestList;
	}

	/**
	 * @param assesQuestList the assesQuestList to set
	 */
	public void setAssesQuestList(List<Assessments> assesQuestList) {
		this.assesQuestList = assesQuestList;
	}

	/**
	 * @return the assesAnsList
	 */
	public List<Assessments> getAssesAnsList() {
		return assesAnsList;
	}

	/**
	 * @param assesAnsList the assesAnsList to set
	 */
	public void setAssesAnsList(List<Assessments> assesAnsList) {
		this.assesAnsList = assesAnsList;
	}

	/**
	 * @return the offAssesModel
	 */
	public OffenderAssessments getOffAssesModel() {
		return offAssesModel;
	}

	/**
	 * @param offAssesModel the offAssesModel to set
	 */
	public void setOffAssesModel(OffenderAssessments offAssesModel) {
		this.offAssesModel = offAssesModel;
	}
	public Boolean getEnforceFlag() {
		return enforceFlag;
	}
	public void setEnforceFlag(Boolean enforceFlag) {
		this.enforceFlag = enforceFlag;
	}

}
