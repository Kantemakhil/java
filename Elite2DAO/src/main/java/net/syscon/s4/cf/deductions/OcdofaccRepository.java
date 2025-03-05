package net.syscon.s4.cf.deductions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtdocfeeRepository
 */
public interface OcdofaccRepository {

	List<ReferenceCodes> feeActTypeRecordGroup(String caseloadId);

	List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(CaseloadDeductionProfiles searchRecord);

	String calculateOn(String deductionType);

	List<CaseloadDedBeneficiaries> caseloadDedBenficExecuteQuery(CaseloadDedBeneficiaries searchRecord);

	Persons ocmofaccPersons(Long personId);

	Corporates ocmofaccCorporates(BigDecimal corporateId);

	List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(CaseloadDeductionDetails searchRecord);

	List<ReferenceCodes> reciptTypeRecordGroup(String caseloadType);

	BigDecimal offdedPreInsert();
	Integer offdedInsertQuery(List<FeeAccountProfiles> insertList);
	Integer offdedUpdateQuery(List<FeeAccountProfiles> updateList);
	Integer offdedDeleteQuery(List<FeeAccountProfiles> deleteList);


	Long cslddbenPreInsert();
	Integer cslddbenInsertQuery(List<CaseloadDedBeneficiaries> insertList);
	Integer cslddbenUpdateQuery(List<CaseloadDedBeneficiaries> updateList);
	Integer cslddbenDeleteQuery(List<CaseloadDedBeneficiaries> deleteList);

	Integer csldddInsertQuery(List<CaseloadDeductionDetails> insertList);
	Integer csldddUpdateQuery(List<CaseloadDeductionDetails> updateList);
	Integer csldddDeleteQuery(List<CaseloadDeductionDetails> deleteList);

	List<FeeAccountProfiles> offDedExecuteQuery(FeeAccountProfiles searchObject);

	Long feeOverrideDetailsExecuteQuery(Long feeId);
	List<CaseloadDedBeneficiaries> dedPriorities(CaseloadDedBeneficiaries objSearchDao);

	Integer updateCaseloadDedBeneficiariesPercentage(BigDecimal percentage,
													 Long caseloadDedBeneficiaryId,BigDecimal feeId, String userId);

	BigDecimal getPriorityAmount(String caseloadId, String deductionType,
								 BigDecimal priority, BigDecimal feeId);

	Map<String, Object> calcBenTotal(String caseloadId, String deductionType, BigDecimal feeId);

	List<ReferenceCodes> alAgyLocIdRgRecordGroup();

	List<CaseloadDeductionProfiles> getBackBill(String feeCode, String caseloadId);

	Date getSupvPeriodDate(String caseloadId);

	List<CaseloadDedBeneficiaries> caseloadFeeDedBenficExecuteQuery(final CaseloadDedBeneficiaries objSearchDao);
	List<CaseloadDeductionDetails> caseloadFeeDetExecuteQuery(final CaseloadDeductionDetails objSearchDao);

	List<FeeAccountProfiles> offdedPrevExecteQuery(FeeAccountProfiles searchObject);
	List<SystemProfiles> sysPflExecuteQuery();
	List<FeeAccountProfiles> sysLongSupPflExecuteQuery(FeeAccountProfiles objcet);
	List<CaseloadDedBeneficiaries> dedPrioritiesFeeBenf(final CaseloadDedBeneficiaries objSearchDao);

	String getDescription(String code, String domain);

	List<CaseloadDeductionProfiles> getFreequencyValues(String location, String feeCode);

	Integer updateSupvLongExpiryDate(List<FeeAccountProfiles> updateList);

	Integer insertSupvLongExpiryDate(List<FeeAccountProfiles> updateList);

	List<FeeAccountProfiles> getSupvFeeActProfilesDet();

    Integer updateFeeActStatusForLongSupv(List<FeeAccountProfiles> newFeeActList);

	List<FeeAccountProfiles> feeAccountProfileHistory(FeeAccountProfiles object);

	Integer deleteFeeAccountProfileHistory(List<FeeAccountProfiles> returnList);

	List<SystemProfiles> sysPflDedExecuteQuery();

	List<OffenderBookings> returnOffenderBookingObject(Long offenderBookId);

	List<OffenderBookings> getOffenderPreviousOffenderBoookings(BigDecimal offenderId);

    BigDecimal getMaxAmount(BigDecimal offenderFeeId);
    
    Integer offDedUpdateFullQuery(FeeAccountProfiles offDedUpdateFull);
    
    Integer offFeeAcntUpdateQuery(FeeAccountProfiles offDedUpdateFull);

	List<FeeAccountProfiles> getCurrentDataRecord(BigDecimal offenderFeeId);
	
	Integer updateFeeAcntProfileExpireDate(FeeAccountProfiles fap);

	Integer deletSupvHistoryRecord(FeeAccountProfiles object);
	
	Date getOffenderEventDate(Long offenderBookId);

	List<FeeAccountProfiles> getFeeAccountListCaseloadBased(FeeAccountProfiles supbean);
	
	OffFeeBills getOldDataOffFeeBills(String billId);


}
