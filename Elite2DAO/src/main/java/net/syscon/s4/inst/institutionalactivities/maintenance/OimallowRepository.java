package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;


public interface OimallowRepository {
	
	List<Allowances> getAllAllowances();

	Integer insertAllowances(List<Allowances> insertList);
	
	Integer updateAllowances(List<Allowances> updateList);
	
	List<ReferenceCodes> getUnit();
	
	List<OffenderAllowances> getOffenderAllowences();

	List<Allowances> getAllowances();
	
	Integer saveOffAllowPayDetValues(List<OffAllowPayDetails> offAllowPayDetList);

	Integer checkOffAllowPay(BigDecimal offenderBookId, BigDecimal offAllowanceId);
	
}