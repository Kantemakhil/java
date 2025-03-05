package net.syscon.s4.cf.offendertransactions;

import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.VOffGroupedPaymentPlans;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OcidoaccService
 */
public interface OcidoaccService {

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts object);

	List<OffenderDeductions> offBncExecuteQuery(OffenderDeductions object);

	List<VOffGroupedPaymentPlans> paySchExecuteQuery(VOffGroupedPaymentPlans object);

	Integer sysPflExecuteQuery(Integer rootOffenderid);

	String getActClosedFlag(Integer offenderId, String caseloadid);
}
