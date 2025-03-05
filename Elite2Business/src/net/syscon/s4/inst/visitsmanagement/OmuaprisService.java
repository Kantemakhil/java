package net.syscon.s4.inst.visitsmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitorsCommitBean;

/**
 * Interface OmuaprisService
 */
public interface OmuaprisService {
	Integer vOffAuthVisCommit(VOffenderAuthorisedVisitorsCommitBean commitBean);

	List<VOffenderAuthorisedVisitors> vOffAuthVisExecuteQuery(VOffenderAuthorisedVisitors obj);

	List<OffenderRestrictions> getOffenderRestrcitions(String vstOffIdDisplay, Date visitdDate, String userName);

	BigDecimal getOffenderBookId(String vstOffIdDisplay, String userName);

}
