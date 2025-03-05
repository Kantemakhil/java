package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowancesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;

public interface OidallowService {

	List<OffenderAllowances> getOffenderAllowenceExecuteQuery(OffenderAllowances searchBean);

	List<OffenderAllowances> offenderAllowenceDataCommit(OffenderAllowancesCommitBean commitBean);

	List<Allowances> getAllowenceLovData();

	List<ReferenceCodes> getUnitDataLov();

	List<Allowances> getRateVersionData(String allowanceType);
	
	Date getLastPaidDate(BigDecimal offenderBookId, BigDecimal offAllowanceId);
	
	Integer saveOffAllowPayDetails(OffenderAllowancesCommitBean commitBean);

}
