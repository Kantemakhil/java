package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OtdoalloRepository
 */
public interface OtdoalloRepository {
	Integer offDrInsertOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String caseLoadId);

	Integer offDedUpdateOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup();

	TransactionTypes cgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean);

	Integer offDrUpdateOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts objOffenderDeductionReceipts);

	List<ReferenceCodes> cgfkOffDedDeductionStatusRecordGroup();

	DeductionTypes cgfkchkOffDedOffDedDed(DeductionTypes paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer offDedDeleteOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	Integer offDrDeleteOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	Integer offDedInsertOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	String getModifyUserId(String deductionType);

	CaseloadDeductionProfiles offDedPostQuery(OffenderDeductions returnBean);

	OffenderDeductionReceipts offDrValidateRecieptTxnType(OffenderDeductionReceipts searchBean);

	List<OffenderDeductionReceipts> offDedFindPostInsert(OffenderDeductions returnBean);

	Long offDedPreInsert();

	String offDedFindInformationNumber();

	Integer offDedFindInCount(OffenderDeductions offDeducBean);

	List<String> offDrKeyDelrec(String caseloadId, Long offenderId, String deductionType);

	BigDecimal cntDedRcpt(BigDecimal offenderDeductionId);

}
