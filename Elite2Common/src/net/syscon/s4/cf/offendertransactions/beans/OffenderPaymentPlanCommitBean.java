package net.syscon.s4.cf.offendertransactions.beans;


import java.util.List;

import net.syscon.s4.common.beans.BaseModel;


public class OffenderPaymentPlanCommitBean  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderPaymentPlan> insertList;
	private List<OffenderPaymentPlan> deleteList;
	private List<OffenderPaymentPlan> updateList;

	public void setInsertList(List<OffenderPaymentPlan> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderPaymentPlan> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderPaymentPlan> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderPaymentPlan> getInsertList(){
		return insertList;
	}

	public List<OffenderPaymentPlan> getUpdateList(){
		return updateList;
	}

	public List<OffenderPaymentPlan> getDeleteList(){
		return deleteList;
	}


}
