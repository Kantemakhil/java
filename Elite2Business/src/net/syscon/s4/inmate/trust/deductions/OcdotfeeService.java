package net.syscon.s4.inmate.trust.deductions;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

/**
 * Interface OcdotfeeService
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
public interface OcdotfeeService {
	
	ReferenceCodes CgfklkpOffDedOffDedRef(ReferenceCodes paramBean);

	List<Object> CgwhenNewFormInstance();

	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String caseloadId);

	List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup();

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	DeductionTypes CgfkchkOffDedOffDedDed(DeductionTypes paramBean);

	List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts objOffenderDeductionReceipts);

	TransactionTypes CgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean);

	String offDedCommit(OffenderDeductionsCommitBean CommitBean);

	String offDrCommit(OffenderDeductionReceiptsCommitBean CommitBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	ReferenceCodes CgfkchkOffDedOffDedRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup();
	
	void checkUniqueConstraint(OffenderDeductions paramBean);
	
	List<OffenderDeductionReceipts> getPercentageAndFlatRate(final String deductionType, final String caseloadId,
			final String receiptTxnType);

}
