package net.syscon.s4.inst.programswithoutschedules;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;

/**
 * Interface OcuupstaRepository
 */
public interface OcuupstaRepository {
	List<ReferenceCodes> rgPsPrgStatRecordGroup(String statusDesc, String lovDomain);

	List<ReferenceCodes> rgPsPrgObstsRecordGroup(String parentCode);

	List<OffenderPrgObligationHty> offPrgOblHtyExecuteQuery(OffenderPrgObligationHty objOffenderPrgObligationHty);

	String getDescription(String statusChangeReason);

	String getStatusDescription(String status);

	Integer updateStatus(OffenderPrgObligationHty searchBean);

	Date getRefferalDate(Integer offenderPrgObligationId);

	Date getMaxDate(Integer offenderPrgObligationId);

	String updateStatusBtn(final String code);
		
	SystemProfiles sysPflExecuteQuery();
	
	String reasonForSuspendingOrEndingProgramDisable(String code);
	
	List<String> getRoleIdList(String userName);
}
