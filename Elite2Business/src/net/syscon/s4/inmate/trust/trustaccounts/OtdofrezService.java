package net.syscon.s4.inmate.trust.trustaccounts;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursements;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursementsCommitBean;

public interface OtdofrezService  {
	Object offBkgPreDelete();

	Object offFdPreInsert();

	Integer offFdCommit(OffenderFreezeDisbursementsCommitBean CommitBean);

	List<OffenderFreezeDisbursements> offFdExecuteQuery(OffenderFreezeDisbursements objOffenderFreezeDisbursements);

	List<ReferenceCodes> cgfkOffFdFreezeReasonCodeRecordGroup();

}
