package net.syscon.s4.inst.movementexternal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderIndSchedule;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
/**
 * Interface OiiwltwjRepository
 * @author Arkin Software Technologies 	
 * @version 1.0 
 */
public interface OiiwltwjRepository {
	List<VTransferWaitingLists2> vTwlExecuteQuery(String caseLoadId,String userName);

	VHeaderBlock cgfkchkVTwlVTwlVOffBkg(VHeaderBlock paramBean);

	List<VTransferWaitingLists2> refreshCheckSum(VTransferWaitingLists2 paramBean);

	List<AgencyLocations> cgfkVTwlAgencyLocationToRecordGroup();

	List<ReferenceCodes> cgfkVTwlDspDescription3RecordGroup();

	List<ReferenceCodes> cgfkVTwlDspDescriptionRecordGroup();

	AgencyLocations cgfkchkVTwlVTwlAgyLoc(AgencyLocations paramBean);

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	Integer vTwlUpdateVTransferWaitingLists2(List<VTransferWaitingLists2> lstVTransferWaitingLists2);

	ReferenceCodes getParentCode(ReferenceCodes paramBean);

	Integer oiiwltwjUpdateScheduleInfoOffenderIndSchedulesSsh(List<OffenderIndSchedule> offenderSchedulesUpdateList);

	Integer oiiwltwjUpdateScheduleInfoOffenderIndSchedules(List<OffenderIndSchedule> offenderSchedulesUpdateList);

	Integer oiiwltwjUpdateScheduleInfoOffenderIndSchedulesWaitList(
			List<OffenderIndSchWaitLists> offenderSchedulesListUpdateList);
	String tagScheduleLockEventWl(Long eventId, Long checkSum);

}
