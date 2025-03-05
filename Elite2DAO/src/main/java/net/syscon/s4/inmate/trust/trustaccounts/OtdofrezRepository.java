package net.syscon.s4.inmate.trust.trustaccounts;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursements;

public interface OtdofrezRepository {
	Object offFdPreInsert(SysDual paramBean);

	Integer offFdInsertOffenderFreezeDisbursements(List<OffenderFreezeDisbursements> lstOffenderFreezeDisbursements);

	ReferenceCodes cgfkchkOffFdOffFdRefCod(ReferenceCodes paramBean);

	List<OffenderFreezeDisbursements> offFdExecuteQuery(OffenderFreezeDisbursements objOffenderFreezeDisbursements);

	List<ReferenceCodes> cgfkOffFdFreezeReasonCodeRecordGroup();

	Integer offFdUpdateOffenderFreezeDisbursements(List<OffenderFreezeDisbursements> updateList);

	Integer offFdDeleteOffenderFreezeDisbursements(List<OffenderFreezeDisbursements> deleteList);

	Integer preInsert(OffenderFreezeDisbursements bean);


}
