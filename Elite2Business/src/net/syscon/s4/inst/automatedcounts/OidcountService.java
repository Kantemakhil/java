package net.syscon.s4.inst.automatedcounts;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;

/**
 * Interface OidcountService
 */
public interface OidcountService {

	Integer deleteInitiateRecords(AgencyCountTypes bean);

	List<AgencyReportingLocs> createRecountRecords(AgencyReportingLocs paramBean);

	List<TempOidcount> checkExistingCountSession(Integer sessionId, String caseLoadId);

	List<AgencyCountTypes> cgwhenNewItemInstance(AgencyCountTypes paramBean);

	List<ReferenceCodes> cgfkCountTypesRecordGroup(String agyLocId);

	List<ReferenceCodes> cgfkAgyLocIdRecordGroup(String caseLoadId);

	List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(String agyLocId, String countTypeId);

	Long getGlobalSessionId();

	Map<String, Object> countLockedMoulesCursor(AgencyCountTypes bean);

	List<TempOidcount> initiateCountSetup(AgencyCountTypes bean);

	Integer getCountTypeIdFromDb(AgencyCountTypes bean);

	List<TempOidcount> cgwhenNewFormInstanceCgPsessionId(String sessionId);

	Integer checkRemoveDeadJobsProcedure(Integer sessionId,String modifyUserId);

	String cgwhenNewFormInstanceLockedModules(Integer sessionId, String caseLoadId, String userId);

	TempOidcount refreshCount(Integer sessionId);

	List<TempOidcount> refreshCountOfTempOidCount(Integer sessionId);

	Integer submitCountSetTempOidcount(AgencyCountTypes paramBean);

	String refreshCountUserCancelledCur(Integer sessionId, String userId);
	
	Integer getTimerValue();

}
