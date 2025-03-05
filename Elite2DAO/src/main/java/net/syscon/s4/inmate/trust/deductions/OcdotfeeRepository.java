package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OcdotfeeRepository {
	
	Integer offDrInsertOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String caseloadId);

	Integer offDedUpdateOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	ReferenceCodes cgfkchkOffDedOffDedRef(ReferenceCodes paramBean);

	List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup();

	TransactionTypes cgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean);

	Integer offDrUpdateOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	ReferenceCodes cgfklkpOffDedOffDedRef(ReferenceCodes paramBean);

	List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts objOffenderDeductionReceipts);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	DeductionTypes cgfkchkOffDedOffDedDed(DeductionTypes paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer offDedDeleteOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	Integer offDrDeleteOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	Integer offDedInsertOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup();

	String getDedTypeDesc(String deductionType, String caseloadId);

	String getDedStatusDesc(String deductionStatus);

	String checkUniqueConstraint(OffenderDeductions paramBean);

	String getCaseloadType(String caseloadId);

	Long recordExistsC(Long offenderDeductionId);

	List<OffenderDeductionReceipts> detailsC(String deductionType, String caseloadId, String caseloadType);

	Long getBeneficiaryId();

	BigDecimal getPayeeCorporateId(String caseloadId, String deductionType);

	BigDecimal getUnknownBenId();

	void insertIntoOffenderBeneficiaries(Long beneficiaryId, Long offenderDeductionId, Long offenderId, BigDecimal corporateId,
			BigDecimal unknownbenId,String createUserId);

	Long getDeductionId();

	Integer getInfoSeq(Long offenderId, String deductionType, String informationNumber);

	void keyDelrec(List<OffenderDeductions> deleteList);

	List<OffenderDeductionReceipts> getPercentageAndFlatRate(final String deductionType, final String caseloadId,
			final String receiptTxnType);

	OffenderDeductions offenderOldData(Long offenderDeductionId);

}
