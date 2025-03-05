package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcmdedutRepository
 */
public interface OcmdedutRepository {
	Integer dedTypeUpdateDeductionTypes(List<DeductionTypes> lstDeductionTypes);

	List<OffenderDeductions> cgrichkDeductionTypes(OffenderDeductions paramBean);

	List<DeductionTypes> dedTypeExecuteQuery(DeductionTypes objDeductionTypes);

	Integer dedTypeInsertDeductionTypes(List<DeductionTypes> lstDeductionTypes);

	List<ReferenceCodes> cgfkchkDedTypeDedTypeRef(ReferenceCodes paramBean);

	List<DeductionTypes> rgParentDeductionTypeRecordGroup(String deductionType);

	List<ReferenceCodes> cgfkDedTypeDeductionCategoRecordGroup();

	List<ReferenceCodes> cgfkDedTypeCaseloadCodeRecordGroup();

	List<ReferenceCodes> cgfkchkDedTypeDedType(ReferenceCodes paramBean);

	List<TransactionTypes> cgrichkDeductionTypes(TransactionTypes paramBean);

	Integer dedTypeDeleteDeductionTypes(List<DeductionTypes> lstDeductionTypes);

	List<ReferenceCodes> cgfkchkDedTypeDedTypededutCat(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkDedTypeFromBalanceTypRecordGroup();

	String dedCodeValidation(String dedCode);

	Integer caseloadDeductionProfiles(String deductionType);

	Integer deleteTransactionTypes(String deductionType);

	Integer commDeleteTransactionTypes(String dedCode);

	Integer deleteDeductionLimitTypes(String dedCode);

	Integer deleteOffenderSentObligations(String dedCode);

	Integer deleteOffenderDeductions(String dedCode);

}
