package net.syscon.s4.inst.securitythreatgroups;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.StgValidationsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OidstgvlService
 */
public interface OidstgvlService {
	List<ReferenceCodes> rgDesignationRecordGroup();

	List<ReferenceCodes> rgActionRecordGroup();

	Integer stgValidationsCommit(StgValidationsCommitBean commitBean);

	List<StgValidations> stgValidationsWhenCreateRecord(StgValidations paramBean);

	List<ReferenceCodes> stgValidationsPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<StgValidations> stgValidationsExecuteQuery(StgValidations objStgValidations);

	Date reviewDateData(Long stgId);

}
