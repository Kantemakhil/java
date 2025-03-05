package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.VOffGroupedPaymentPlans;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OcidoaccRepository
 */
public interface OcidoaccRepository {

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts object);

	List<OffenderDeductions> offBncExecuteQuery(OffenderDeductions object);

	List<VOffGroupedPaymentPlans> paySchExecuteQuery(VOffGroupedPaymentPlans object);

	Integer sysPflExecuteQuery(Integer rootOffenderid);

	AccountCodes getAccountCodesOfTypeAndName(Long trustAccountCode);

	BigDecimal getMonAmt(OffenderDeductions bean);

	BigDecimal getRecMonth(OffenderDeductions bean);

	String getActClosedFlag(Integer offenderId, String caseloadid);

	Integer lvNonCompletedSch(VOffGroupedPaymentPlans bean);

	BigDecimal getAmount(VOffGroupedPaymentPlans bean);

	Date getDaysLate(BigDecimal paymentPlanId, Date minNextPaymentDateTwo);

	BigDecimal getArrears(VOffGroupedPaymentPlans bean);

	Integer getReason(VOffGroupedPaymentPlans bean);

	BigDecimal getDaysLateQuery(Date tempDate);

}
