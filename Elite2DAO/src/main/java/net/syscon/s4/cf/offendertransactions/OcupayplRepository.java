package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanReportBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.cf.offendertransactions.beans.VOffenderPaymentSchedule;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcupayplRepository
 */
public interface OcupayplRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<OffenderDeductions> cgfkPayPlnInformationNumbeRecordGroup(Long offenderId, String caseloadId);

	Integer payPlnUpdateOffenderPaymentPlans(List<OffenderPaymentPlan> obj);

	List<VOffenderPaymentSchedule> paySchExecuteQuery(VOffenderPaymentSchedule obj);

	List<PaymentPlanTransaction> ppTxnExecuteQuery(PaymentPlanTransaction obj) throws ParseException;

	List<ReferenceCodes> cgfkPayPlnDspDescriptionRecordGroup();

	List<OffenderPaymentPlan> payPlnExecuteQuery(OffenderPaymentPlan obj);

	List<ReferenceCodes> cgfkPayPlnDspDescription4RecordGroup();

	Integer payPlnInsertOffenderPaymentPlans(List<OffenderPaymentPlan> obj);

	Object payPlnPreInsert(SysDual paramBean);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<OffenderPaymentPlan> payPlnPreInsert(OffenderPaymentPlan paramBean);

	List<PaymentPlanTransaction> paySchOnCheckDeleteMaster(PaymentPlanTransaction paramBean);

	List<VOffenderPaymentSchedule> payPlnOnCheckDeleteMaster(VOffenderPaymentSchedule paramBean);

	Integer payPlnDeleteOffenderPaymentPlans(List<OffenderPaymentPlan> obj);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<ReferenceCodes> cgfkPayPlnDspDescription3RecordGroup();

	Long preInsertPlanId(String informationNumber, BigDecimal groupId, BigDecimal offenderId);

	long preInsertPlanSeq(long paymentPlanId);

	Long preInsertSequence();

	BigDecimal calculatePaymentAmount(OffenderPaymentPlan searchBean);

	Date getDateValidated(String startDate, BigDecimal monthly, Boolean bFlag) throws ParseException;

	String getGroupCode(BigDecimal groupId);

	String chkRecursive(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	String recurtExists(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	BigDecimal getRecursiveAmt(BigDecimal bigDecimal, String informationNumber, BigDecimal groupId);

	OffenderPaymentPlan getMaxPlanIdAndSeq(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	BigDecimal getPaymentAmount(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	BigDecimal getPaidAmount(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	BigDecimal originalOwingaAmount(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	OffenderDeductions getTotDedAmount(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	BigDecimal getTotalArrears(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	String payPlnPostQuery(BigDecimal groupId);

	String getJSStatus(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	VOffenderPaymentSchedule postBlockPlan(OffenderPaymentPlan searchBean);

	int whenNewBlockInstanceSch(long paymentPlanId);

	BigDecimal checkEverPaid(long paymentPlanId, long paymentPlanSeq);

	Integer modifyDelSchdule(long paymentPlanId, long paymentPlanSeq);

	Integer deleteSchdule(long paymentPlanId, long paymentPlanSeq, String modifyUserId);

	void updateOwingAmount(BigDecimal offenderId, String informationNumber, BigDecimal groupId);

	Integer deletePayPlan(long paymentPlanId, long paymentPlanSeq, String modifyUserId);

	void reschPaymentSchedules(long paymentPlanId, long paymentPlanSeq, Boolean biAutoAdjust);

	void setPaymentSchedules(long l, long paymentPlanSeq, Boolean biAutoAdjust);

	Boolean adjustForRoundoffs(BigDecimal offenderId, String informationNumber, BigDecimal groupId, long paymentPlanId,
			long paymentPlanSeq);

	OffenderDeductions getTotalAmt(BigDecimal offenderId, String informationNumber);

	Integer validateDelRow(List<Long> returnObj);


	VTrustHeader offenderDisplay(OffenderPaymentPlan searchBean);

	OffenderPaymentPlan paymtSummary(String informNumber, BigDecimal groupId, BigDecimal offenderId);

	List<PaymentPlanBean> schedulePymt(String informNumber, BigDecimal groupId, String paymentClosedFlag,
			long paymentPlanId, long paymentPlanSeq, BigDecimal paidAmount);

	List<PaymentPlanReportBean> mainHeaderQuery(PaymentPlanReportBean paramBean);

	BigDecimal totalDueAmount(OffenderPaymentPlan searchBean);

	String getCaseLoad(String caseLoad);

	String getProfileDesc(String profileCode);

	Integer getDayOfMonth(String dateIfo);

	Long gettingGroupId(BigDecimal offenderId, String groupId, String caseLoadId);


}
