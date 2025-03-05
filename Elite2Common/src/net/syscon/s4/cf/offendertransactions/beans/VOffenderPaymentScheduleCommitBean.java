package net.syscon.s4.cf.offendertransactions.beans;


import java.util.List;

import net.syscon.s4.common.beans.BaseModel;


public class VOffenderPaymentScheduleCommitBean  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VOffenderPaymentSchedule> insertList;
	private List<VOffenderPaymentSchedule> deleteList;
	private List<VOffenderPaymentSchedule> updateList;

	public void setInsertList(List<VOffenderPaymentSchedule> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VOffenderPaymentSchedule> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VOffenderPaymentSchedule> deleteList){
		this.deleteList = deleteList;
	}

	public List<VOffenderPaymentSchedule> getInsertList(){
		return insertList;
	}

	public List<VOffenderPaymentSchedule> getUpdateList(){
		return updateList;
	}

	public List<VOffenderPaymentSchedule> getDeleteList(){
		return deleteList;
	}


}
