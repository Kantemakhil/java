package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.OffenderTransactions;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderTransactionsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderTransactions> insertList;
	@JsonProperty("deleteList")
	private List<OffenderTransactions> deleteList;
	@JsonProperty("updateList")
	private List<OffenderTransactions> updateList;
	@JsonProperty("printRcptInsertList")
	private List<PrintReceiptsTmp> printRcptInsertList;
	@JsonProperty("omsReqBean")
	private OmsRequests omsReqBean;
	@JsonProperty("offFeeUpdateList")
	private List<OffFeeBillTransactions> offFeeUpdateList;
	@JsonProperty("prepaidAcntInsertList")
	private List<OffenderTransactions> prepaidAcntInsertList;
	@JsonProperty("stmtInsertList")
	private List<offBillingStatements> stmtInsertList;

	/**
	 * Creates new OffenderTransactionsCommitBean class Object
	 */
	public OffenderTransactionsCommitBean() {
		// OffenderTransactionsCommitBean
	}

	public void setInsertList(final List<OffenderTransactions> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderTransactions> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderTransactions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderTransactions> getInsertList() {
		return insertList;
	}

	public List<OffenderTransactions> getUpdateList() {
		return updateList;
	}

	public List<OffenderTransactions> getDeleteList() {
		return deleteList;
	}

	public List<PrintReceiptsTmp> getPrintRcptInsertList() {
		return printRcptInsertList;
	}

	public void setPrintRcptInsertList(List<PrintReceiptsTmp> printRcptInsertList) {
		this.printRcptInsertList = printRcptInsertList;
	}

	public OmsRequests getOmsReqBean() {
		return omsReqBean;
	}

	public void setOmsReqBean(OmsRequests omsReqBean) {
		this.omsReqBean = omsReqBean;
	}

	public List<OffFeeBillTransactions> getOffFeeUpdateList() {
		return offFeeUpdateList;
	}

	public void setOffFeeUpdateList(List<OffFeeBillTransactions> offFeeUpdateList) {
		this.offFeeUpdateList = offFeeUpdateList;
	}

	public List<OffenderTransactions> getPrepaidAcntInsertList() {
		return prepaidAcntInsertList;
	}

	public void setPrepaidAcntInsertList(List<OffenderTransactions> prepaidAcntInsertList) {
		this.prepaidAcntInsertList = prepaidAcntInsertList;
	}

	public List<offBillingStatements> getStmtInsertList() {
		return stmtInsertList;
	}

	public void setStmtInsertList(List<offBillingStatements> stmtInsertList) {
		this.stmtInsertList = stmtInsertList;
	}
	
	

}
