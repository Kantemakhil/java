package net.syscon.s4.cf.offendertransactions.beans;


import java.util.List;

import net.syscon.s4.common.beans.BaseModel;


public class PaymentPlanTransactionCommitBean  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PaymentPlanTransaction> insertList;
	private List<PaymentPlanTransaction> deleteList;
	private List<PaymentPlanTransaction> updateList;

	public void setInsertList(List<PaymentPlanTransaction> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<PaymentPlanTransaction> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<PaymentPlanTransaction> deleteList){
		this.deleteList = deleteList;
	}

	public List<PaymentPlanTransaction> getInsertList(){
		return insertList;
	}

	public List<PaymentPlanTransaction> getUpdateList(){
		return updateList;
	}

	public List<PaymentPlanTransaction> getDeleteList(){
		return deleteList;
	}


}
