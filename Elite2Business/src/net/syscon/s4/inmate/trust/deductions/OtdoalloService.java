package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

/**
 * Interface OtdoalloService
 */
public interface OtdoalloService {
	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String caseLoadId);

	List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup();

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	DeductionTypes CgfkchkOffDedOffDedDed(DeductionTypes paramBean);

	List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts objOffenderDeductionReceipts);

	TransactionTypes CgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean);

	List<ReferenceCodes> cgfkOffDedDeductionStatusRecordGroup();

	String offDedCommit(OffenderDeductionsCommitBean commitBean);

	Integer offDrCommit(OffenderDeductionReceiptsCommitBean commitBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	OffenderDeductionReceipts offDrValidateRecieptTxnType(OffenderDeductionReceipts searchBean);

	OffenderDeductions offDedValidateDeductionType(OffenderDeductions searchBean);
	
	List<String> offDrKeyDelrec(String caseloadId, Long offenderId, String deductionType);

	BigDecimal cntDedRcpt(BigDecimal offenderDeductionId);

	String insertOnNotAvaliable(OffenderDeductions offDeducBean);

}
