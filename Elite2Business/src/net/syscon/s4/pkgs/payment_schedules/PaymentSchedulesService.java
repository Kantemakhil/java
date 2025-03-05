package net.syscon.s4.pkgs.payment_schedules;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;

public interface PaymentSchedulesService {

	String getGroupCode(final BigDecimal groupId);

	String chkRecursive(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId);

	BigDecimal getRecursiveAmt(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId);

	void updateOwingAmount(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId,
			final String userName);

	Boolean adjustForRoundoffs(final BigDecimal niOffenderId, final Long niPlanId, final Long niPlanSeq,
			final String viInfo, final BigDecimal niGrpId, final String userName);

	OffenderPaymentPlan getPaymentPlans(final Long niPlanId, final Long niPlanSeq);

	Map<String, Object> getOrgAmountOwing(BigDecimal niOffenderId, String viInfo, BigDecimal niGrpId);

	Map<String, Object> getPlanSummary(final Long niPlanId);

	void deleteDummySchedules(final Long niPlanId, final Long niPlanSeq,String modifyUserId);

	void setPaymentSchedules(final long paymentPlanId, final long paymentPlanSeq, final Boolean biAutoAdjust,
			final String userName);

	Long numberOfPayments(final OffenderPaymentPlan bean);

	Map<String, Object> mgetNextDate(final Date dioDate, final OffenderPaymentPlan riPln, final String viWeekday,
			final Date dioDate2, final Long nioToggle2, final Boolean bioFlag, final Boolean biExtend);

	Date getDateValidated(final String startDate, final BigDecimal monthly, final Boolean bFlag) throws ParseException;

	void insertPaymentSchedules(Long niPlanId, Long niPlanSeq, String viInfo, BigDecimal niGrpId, BigDecimal niDedId,
			Date diDate, BigDecimal niFixedAmt, final String userName);

	void setPlanEnddate(final Long niPlanId, final Long niPlanSeq, final Date diEndDate, final String userName);

	void setRecurAmts(final Long niPlanId, final Long niPlanSeq, final Date diPlnStart, final Date diPlnEnd,
			final BigDecimal niRecurAmt, final String userName);

	void setLeniencyFlag(final OffenderPaymentPlan riPln, final String userName);

	String getSystemProfile(final String viProfileType, final String viProfileCode, final Integer niProfileValueInd);

	String getWeekday(final String viWkCode);

	Map<String, Object> mgetFirstDate(final Date dioDate, final OffenderPaymentPlan riPln, final String vioWeekday,
			final Date dioDate2, final Long nioToggle2, final Boolean bioFlag);

	void reschPaymentSchedules(final long paymentPlanId, final long paymentPlanSeq, final Boolean biAutoAdjust,
			final String userName);

	Integer numberOfPaymentsLeft(final Long niPlanId, final Long niPlanSeq);

}