package net.syscon.s4.cf.deductions.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OcmfaproCommitBean;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

import org.springframework.transaction.annotation.Transactional;

public interface OcmfaproService {
	List<ReferenceCodes> feeActTypeRecordGroup();

	List<ReferenceCodes> locationRecordGroup(String agyLocType);

	List<ReferenceCodes> creditDedToRecordGroup(String caseloadType);

	List<ReferenceCodes> frequencyRecordGroup();

	List<ReferenceCodes> codeRecordGroup();

	List<ReferenceCodes> reciptTypeRecordGroup(String caseloadType);

	List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(CaseloadDeductionProfiles searchRecord);

	Integer csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean);

	List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(CaseloadDeductionDetails searchRecord);

	List<CaseloadDedBeneficiaries> caseloadDedbenficExecuteQuery(CaseloadDedBeneficiaries searchRecord);

	Integer csldDbCommit(CaseloadDedBeneficiariesCommitBean commitBean);

	Integer csldDdCommit(CaseloadDeductionDetailsCommitBean commitBean);

	Persons cgfkchkCsldDbenCsldDben(Long personId);

	Corporates cgfkchkCsldDbenCsldDben(BigDecimal corporateId);

	String singleCommit(OcmfaproCommitBean commitBean);

    List<SystemProfiles> sysPflExecuteQuery();

    String calculateOn(String deductionType);

	List<SystemProfiles> sysPfl2ExecuteQuery();
}
