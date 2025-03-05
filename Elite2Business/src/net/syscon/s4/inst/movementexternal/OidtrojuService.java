package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.SysDual;

public interface OidtrojuService {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements object);

	Integer offEmPreInsert(OffenderExternalMovements paramBean);

	Integer offEmCommit(OffenderExternalMovementsCommitBean commitBean);

	List<ReferenceCodes> cgfkOffEmToProvStatCodeRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	ReferenceCodes cgfkchkOffEmOffEmRefTo(ReferenceCodes paramBean);

	Integer offEmInsertoffEm(List<OffenderExternalMovements> searchRecord);
	
	Integer checkMovementDate(OffenderExternalMovements obj);

}
