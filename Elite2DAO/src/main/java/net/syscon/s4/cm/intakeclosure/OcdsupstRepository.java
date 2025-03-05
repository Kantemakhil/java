package net.syscon.s4.cm.intakeclosure;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
//import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
//import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.common.beans.OffSupervisionStsHty;

public interface OcdsupstRepository {

	List<OffenderBookingAgyLocs> offBkgAgyLocExecuteQuery(OffenderBookingAgyLocs searchBean);

	String getProfileValue();

	ReferenceCodes getBillableFlag(String code);

	Integer suphstyInsertQuery(List<OffSupervisionStsHty> insertList);

	Integer suphstyUpdateQuery(List<OffSupervisionStsHty> updateList);

	List<OffSupervisionStsHty> supHistoryExecuteQuery(OffSupervisionStsHty searchBean);

	FeeAccountProfiles getsupStartDate(BigDecimal offenderBookId);

	Integer updateFeeAccounts(List<FeeAccountProfiles> updateList);

	List<FeeAccountProfiles> getCurrentSupData(BigDecimal offenderBookId);

	List<OffSupervisionStsHty> supHisExecuteQuery(OffSupervisionStsHty searchBean);

	Integer getIntakeRevCount(final VHeaderBlock bean);

	Integer validateStartEndDates(OffSupervisionStsHty bean);

	Integer validateStartDate(OffSupervisionStsHty bean);

	Integer getActiveCount(OffSupervisionStsHty bean);

	Integer preUpdateSupvAccount(OffSupervisionStsHty bean);

	List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(String  caseloadId);

	BigDecimal getMaxOdp(Integer offenderBookId, String feeId, String caseloadId);

//	Integer offFeeBillsInsertQuery(OffFeeBills ofFeeBillBean);

	Integer updFeeAccounts(FeeAccountProfiles bean);

	Integer getFeeBillSeq(String string);

	Integer getstaffId(String userId);

//	Integer offFeeBillTransInsertQuery(OffFeeBillTransactions ofFeeBillBean);

	Integer getBillTranId(String billId);

	Integer getFeeBillCount(Integer offenderBookId);

	Integer getSupvRecCount(Integer offenderBookId, String caseloadId);

	List<FeeAccountProfiles> getCurrentSupRecordsData(BigDecimal offenderBookId);

	List<OffSupervisionStsHty> getActiveSUpvStHstryRecord(FeeAccountProfiles feeBean);

	BigDecimal feeOverrodeExists(BigDecimal offenderFeeId);

	Integer feeOverrodeExists(String feeCode);

	 String checkAccountSatus(OffSupervisionStsHty bean);
	
}
