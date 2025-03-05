package net.syscon.s4.pkgs.payment_schedules;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface PaymentSchedulesRepository {

	String getGroupCode(final BigDecimal groupId);

	String chkRecursive(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId);

	BigDecimal getRecursiveAmt(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId);

	List<OffenderDeductions> paymentSchedulesSelect(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId);

	Integer offenderPaymentPlansUpdate(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId, final Integer owningAmount, final String userName);

	List<OffenderPaymentPlan> fetOffPayPlan(final Long niPlanId, final Long niPlanSeq);

	List<OffenderDeductions> fetDedCur(final BigDecimal niOffenderId, final String viInfo, final BigDecimal niGrpId);

	List<OffenderPaymentSchedules> fetSchCur(final Long niPlanId);

	Long getNoMonth(final Date dLastDate, final Date dFirstDate);

	BigDecimal getNMaxTotalAmount(final BigDecimal niOffId, final String vInfo, final BigDecimal niGrpId);

	Integer updateOffPaymentSchedule(final BigDecimal nTotalDiff, final Long niPlanId, final Long niPlanSeq,
			final String userName);

	Integer deteleOffPaymentSchedule(final Long niPlanId, final Long niPlanSeq,String modifyUserId);

	Date getLastDay(final Date diDate);

	Date getDateValidateddDateReturn(Integer nmonth, BigDecimal niDay, Integer year);

	Integer insertPaymentSchedules(final Long niPlanId, final Long niPlanSeq, final BigDecimal niGrpId,
			final BigDecimal niDedId, final Date diDate, final BigDecimal niFixedAmt, final String userName);

	Integer setPlanEnddate(final Long niPlanId, final Long niPlanSeq, final Date diEndDate, final String userName);

	Integer setRecurAmts(final Long niPlanId, final Long niPlanSeq, final BigDecimal niRecurAmt, final String userName);

	Integer setRecurAmtsOne(final Long niPlanId, final Long niPlanSeq, final String userName);

	Date setLeniencyFlagGetPaymentDate(final Long niPlanId, final Long niPlanSeq);

	Date setLeniencyFlagGetEndDate(final Long niPlanId, final Long niPlanSeq);

	List<SystemProfiles> getSystemProfile(String viProfileType, String viProfileCode);

	Integer setLeniencyFlagUpdate(final Long paymentPlanId, final String userName);

	String getWeekday(final String viWkCode);

	List<OffenderPaymentSchedules> getPaymentSchCur(long paymentPlanId, long paymentPlanSeq);

	Integer numberOfPaymentsLeft(final Long niPlanId, final Long niPlanSeq);

	Integer updateReschPaymentSchedules(final Long paymentPlanId, final Long paymentPlanSeq, final BigDecimal nFixedAmt,
			final Date paymentDate, final String userName);

	BigDecimal getMaxRecursiveAmount(final BigDecimal offenderid, final String informationNumber,
			final BigDecimal groupId);

	Integer updateReschPaymentSchedulesSecond(final Long paymentPlanId, final Long paymentPlanSeq,
			final BigDecimal maxRecurAmount, final String userName);
	Long paymentPlanSeq(final Long paymentPlanId);

}