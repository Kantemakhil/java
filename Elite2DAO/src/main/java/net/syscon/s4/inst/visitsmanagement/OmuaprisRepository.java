package net.syscon.s4.inst.visitsmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;

/**
 * Interface OmuaprisRepository
 */
public interface OmuaprisRepository {
	List<VHeaderBlock> getOffenderNames(VHeaderBlock paramBean);

	List<VOffenderAuthorisedVisitors> vOffAuthVisExecuteQuery(VOffenderAuthorisedVisitors obj);

	Integer vOffAuthVisUpdateVOffenderAuthorisedVisitors(List<VOffenderAuthorisedVisitors> obj);

	VOffenderAuthorisedVisitors getContactOffenderDetails(VOffenderAuthorisedVisitors bean);

	List<OffenderRestrictions> getOffenderRestrcitions(Integer offBookId, Date visitdDate);

	BigDecimal getOffenderId(String vstOffIdDisplay);

	BigDecimal getOffenderBookId(String vstOffIdDisplay);

}
