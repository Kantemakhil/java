package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.common.beans.BaseModel;

/**
 * Class SystemProfilesCommitBean extends BaseBean
 */
@XmlRootElement
public class OffenderBeneficiariesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<OffenderBeneficiaries> insertList;
	private List<OffenderBeneficiaries> deleteList;
	private List<OffenderBeneficiaries> updateList;
	private List<OffFeeBillTransactions> offFeeBillupdateList;
	private List<offBillingStatements> stmtInsertList;

	public void setInsertList(final List<OffenderBeneficiaries> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderBeneficiaries> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderBeneficiaries> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderBeneficiaries> getInsertList() {
		return insertList;
	}

	public List<OffenderBeneficiaries> getUpdateList() {
		return updateList;
	}

	public List<OffenderBeneficiaries> getDeleteList() {
		return deleteList;
	}

	public List<OffFeeBillTransactions> getOffFeeBillupdateList() {
		return offFeeBillupdateList;
	}

	public List<offBillingStatements> getStmtInsertList() {
		return stmtInsertList;
	}

	public void setStmtInsertList(List<offBillingStatements> stmtInsertList) {
		this.stmtInsertList = stmtInsertList;
	}

	public void setOffFeeBillupdateList(List<OffFeeBillTransactions> offFeeBillupdateList) {
		this.offFeeBillupdateList = offFeeBillupdateList;
	}

}