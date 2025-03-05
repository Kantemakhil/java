package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlanCommitBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.cf.offendertransactions.beans.VOffenderPaymentSchedule;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
/**
 * Interface OcupayplService 
 */
public interface OcupayplService  {

	Integer sysPflCommit(SystemProfilesCommitBean commitBean) ;			

	List<OffenderDeductions> cgfkPayPlnInformationNumbeRecordGroup(Long offenderId, String caseloadId) ;

	List<VOffenderPaymentSchedule> paySchExecuteQuery(VOffenderPaymentSchedule schObj) ;

	List<PaymentPlanTransaction> ppTxnExecuteQuery(PaymentPlanTransaction txnObj) throws ParseException ;

	List<OffenderPaymentPlan> payPlnCommit(OffenderPaymentPlanCommitBean commitBean) ;

	List<ReferenceCodes> cgfkPayPlnDspDescriptionRecordGroup() ;

	List<OffenderPaymentPlan> payPlnExecuteQuery(OffenderPaymentPlan planObj) ;

	List<ReferenceCodes> cgfkPayPlnDspDescription4RecordGroup() ;

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles) ;

	List<ReferenceCodes> cgfkPayPlnDspDescription3RecordGroup() ;

	OffenderPaymentPlan whenValidateItem(OffenderPaymentPlan searchBean) throws ParseException;

	OffenderPaymentPlan whenNewRecordInsatnce(OffenderPaymentPlan searchBean);

	OffenderPaymentPlan postBlockPlan(OffenderPaymentPlan searchBean);

	int whenNewBlockInstanceSch(long paymentPlanId);

	OffenderPaymentPlan whenCheckboxChanged(OffenderPaymentPlan searchBean);

	OffenderPaymentPlan keyCommit(OffenderPaymentPlan offenderPlan);

	OffenderPaymentPlan keyListVal(OffenderPaymentPlan searchBean);

	OffenderPaymentPlan adjustForRoundoffs(OffenderPaymentPlan offenderPlan);

	ReportBean  printPlan(OffenderPaymentPlan searchBean);

	Long gettingGroupId(BigDecimal offenderId, String groupId, String caseloadId);



}
