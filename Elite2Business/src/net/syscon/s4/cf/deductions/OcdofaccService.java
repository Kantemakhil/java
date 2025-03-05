package net.syscon.s4.cf.deductions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfilesCommitBean;
import net.syscon.s4.cf.deductions.beans.OcmofaccCommitBean;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtdocfeeService
 */
public interface OcdofaccService {

	List<ReferenceCodes> feeActTypeRecordGroup(String caseloadId);

	List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(CaseloadDeductionProfiles searchBean);

	List<CaseloadDedBeneficiaries> caseloadDedbenficExecuteQuery(CaseloadDedBeneficiaries searchBean);

	List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(CaseloadDeductionDetails searchBean);

	List<ReferenceCodes> reciptTypeRecordGroup(String caseloadType);
	List<ReferenceCodes> alAgyLocIdRgRecordGroup();

	Integer ocmofaccCommit (OcmofaccCommitBean commitBean);

	List<FeeAccountProfiles> offDedExecuteQuery(FeeAccountProfiles searchObject);

	Persons ocmofaccPersons(Long personId);

	Corporates ocmofaccCorporates(BigDecimal corporateId);

	Long feeOverrideDetailsExecuteQuery(long feeId);

	Map<String, Object> getlongSupvDate(String caseloadId);

	List<CaseloadDedBeneficiaries> caseloadFeeDedBenficExecuteQuery(final CaseloadDedBeneficiaries searchRecord);
	List<CaseloadDeductionDetails> caseloadFeeDetExecuteQuery(final CaseloadDeductionDetails searchRecord);

	Integer cslddbenCommitBean(CaseloadDedBeneficiariesCommitBean cslddbenCommitBean, String userId);

	Integer csldddCommitBean(CaseloadDeductionDetailsCommitBean csldddCommitBean, String userId);

	Integer offdedCommitBean(FeeAccountProfilesCommitBean offdedCommitBean,FeeAccountProfiles longestSupvDate, String exclude,String userId);

	BigDecimal offdedPreInsert();

	List<FeeAccountProfiles> offdedPrevExecteQuery(FeeAccountProfiles searchObject);

	List<SystemProfiles> sysPflExecuteQuery();

	List<FeeAccountProfiles> sysLongSupPflExecuteQuery(FeeAccountProfiles searchObject);

    void updateStatusForLongSupvDate();

	List<SystemProfiles> sysPflDedExecuteQuery();
	
	Date getOffenderEventDate(Long offenderBookId);
	
	Map<String, Object> offFeeBillsTrigger(String feeBillsBean);
}
