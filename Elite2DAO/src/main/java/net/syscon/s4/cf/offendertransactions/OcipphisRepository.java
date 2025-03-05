package net.syscon.s4.cf.offendertransactions;

import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.VPaymentPlanHistories;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OcipphisRepository
 */
public interface OcipphisRepository {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<VPaymentPlanHistories> vPaymentPlanHistoriesExecuteQuery(VPaymentPlanHistories object);

}
