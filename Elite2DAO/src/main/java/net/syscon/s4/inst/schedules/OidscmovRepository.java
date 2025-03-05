package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

/**
 * Interface OidscmovRepository
 */
public interface OidscmovRepository {
	List<AgencyLocations> rgCtrlInstRecordGroup(String caseloadId);

	List<CourtEvents> crtEveExecuteQuery(CourtEvents objCourtEvents);

	Integer crtEveUpdateCourtEvents(List<CourtEvents> lstCourtEvents);

	List<MovementReasons> rgCourtReaRecordGroup();

	Integer crtEveDeleteCourtEvents(List<CourtEvents> lstCourtEvents);

	List<MovementReasons> rgCtrlReasonRecordGroup();

	Object crtEvePreInsert();

	Integer crtEveInsertCourtEvents(List<CourtEvents> lstCourtEvents);

	List<AgencyLocations> rgCtrlCourtRecordGroup();

	Date getCurrentDate();

	List<VNameSearch> nameSrchExecuteoffenderQuery(VNameSearch objSearchDao);

	Integer checkScheduleConflict(CourtEvents objCourts);

	List<CourtEvents> getOffenderDetails(String nbtOffDisplayId, String agyLocId, String caseloadId);
	
	BigDecimal crtEveInsertCourtEventsPreEventId(final CourtEvents courtEvents);

	//Boolean chkNaConflict(Integer offenderBookId, String agyLocId, Date eventDate);
	
	List<OffenderBookings> getNaCur(Integer offenderBookId);
	
	Integer getLvNaCount(Integer offenderBookId);
	
	Integer getLvNaCountCur(Long offenderBookId, String agyLocId, Date eventDate);

	List<Offenders> getExternalIndNonAssocationForINP(Long offenderBookId, String agyLocId, Date eventDate);
	
	List<Offenders> getExternalIndNonAssocationForVIDOrOME(Long offenderBookId, String agyLocId, Date eventDate,String caseLoad);
	
	List<Integer> getInternalIndNonAssocationForINPOrVIDOrOME(Long offenderBookId ,List<Integer> internalList);
	
	List<Offenders> getExternalGangNonAssocationForINP(Long offenderBookId, String agyLocId, Date eventDate);

	List<Offenders> getExternalGangNonAssocationForVIDOrOME(Long offenderBookId, String agyLocId, Date eventDate,String caseLoad);
	
	List<Integer> getInternalGangNonAssocationForINPOrVIDOrOME(Long offenderBookId ,List<Integer> internalList);
	
	List<Integer> getInternalIndNonAssocationForINPOrVIDOrOMELocation(CourtEvents courtEvents  ,List<Integer> internalList);


}
