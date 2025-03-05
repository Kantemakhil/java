package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderStgAssociations;
import net.syscon.s4.im.beans.OffenderStgAssociationsCommitBean;

/**
 * Interface OidmbrasService
 */
public interface OidmbrasService {
	List<ReferenceCodes> rgReasonCodeRecordGroup();

	List<OffenderStgAssociations> offenderStgAssociationsExecuteQuery(OffenderStgAssociations objOffenderStgAss);

	Integer offenderStgAssociationsCommit(OffenderStgAssociationsCommitBean commitBean);

}
