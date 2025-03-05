package net.syscon.s4.inst.programswithoutschedules;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;

/**
 * Interface OcuupstaService
 */
public interface OcuupstaService {
	List<ReferenceCodes> rgPsPrgStatRecordGroup(String statusDesc, String lovDomain);

	List<ReferenceCodes> rgPsPrgObstsRecordGroup(String parentCode);

	List<OffenderPrgObligationHty> offPrgOblHtyExecuteQuery(OffenderPrgObligationHty objOffenderPrgObligationHty);

	Integer updateStatus(OffenderPrgObligationHty searchBean);

	Date getRefferalDate(Integer offenderPrgObligationId);

	Date getMaxDate(Integer offenderPrgObligationId);

	Boolean updateStatusBtn(final String code);
	
	Boolean getAdministratorUserAccsess(String userName);
	
	Boolean reasonForSuspendingOrEndingProgramDisable(String code);
	
	List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes searchBean);
}
