package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.DeductionTypesCommitBean;

/**
 * Interface OcmdedutService
 */
public interface OcmdedutService {
	List<DeductionTypes> dedTypeExecuteQuery(DeductionTypes objDeductionTypes);

	List<ReferenceCodes> cgfkchkDedTypeDedType(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkDedTypeCaseloadCodeRecordGroup();

	Integer dedTypeCommit(DeductionTypesCommitBean CommitBean);

	List<DeductionTypes> rgParentDeductionTypeRecordGroup(String deductionType);

	List<ReferenceCodes> cgfkchkDedTypeDedTypeRef(ReferenceCodes paramBean);

	List<TransactionTypes> cgrichkDeductionTypes(TransactionTypes paramBean);

	List<ReferenceCodes> cgfkDedTypeDeductionCategoRecordGroup();

	List<ReferenceCodes> cgfkDedTypeFromBalanceTypRecordGroup();

	String dedCodeValidation(String dedCode);

	Integer deleteDedTypeValidation(String dedType);

}
