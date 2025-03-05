package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

public interface OidallowRepository {

	List<OffenderAllowances> getOffenderAllowenceExecuteQuery(OffenderAllowances searchBean);

	Integer insertOffenderAllowances(List<OffenderAllowances> insertList);

	Integer updateOffenderAllowances(List<OffenderAllowances> updateList);

	Integer deleteOffenderAllowances(List<OffenderAllowances> deleteList);

	List<Allowances> getAllowenceLovData();

	List<Allowances> getRateVersionData(String allowanceType);

	Date getLastPaidDate(BigDecimal offenderBookId, BigDecimal offAllowanceId);
	
	Integer saveOffAllowPayDetails(List<OffAllowPayDetails> offAllowPayDetList);

	List<OffenderAllowances> getOffenderAllowences(BigDecimal offenderBookId);

	List<Allowances> getAllowances();
	
	Integer checkOffAllowPay(BigDecimal offenderBookId, BigDecimal offAllowanceId, Date OffAllowStartDt);
	
	Integer deleteOffAllowancePayDetails(List<OffenderAllowances> deleteList);

	Integer deleteRemainingOffAllowancePayDetails(OffenderAllowances deletObj);

}
