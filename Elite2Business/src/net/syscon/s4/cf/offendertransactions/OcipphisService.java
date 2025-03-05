package net.syscon.s4.cf.offendertransactions;

import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.VPaymentPlanHistories;

/**
 * Interface OcipphisService
 */
public interface OcipphisService {
	List<VPaymentPlanHistories> vPaymentPlanHistoriesExecuteQuery(VPaymentPlanHistories object);

}
