package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OidstgvlRepository
 */

public interface OidstgvlRepository {

	Long oidstgvlKeyCommit();

	List<StgValidations> stgValidationsWhenCreateRecord(StgValidations paramBean);

	List<ReferenceCodes> rgDesignationRecordGroup();

	List<ReferenceCodes> stgValidationsPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgActionRecordGroup();

	Integer stgvalidationsInsertStgValidations(List<StgValidations> lstStgValidations);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<StgValidations> stgValidationsExecuteQuery(StgValidations objStgValidations);

	BigDecimal validationSeqData(BigDecimal bigDecimal);

	Date reviewDateData(Long stgId);

}
