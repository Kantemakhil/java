package net.syscon.s4.inst.schedules;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderSchedulePersons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
/**
 * Interface OidsiappRepository
 */
public interface OidsiappRepository {
	List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(VOffenderAllSchedules paramBean);

	List<IntLocUsageLocations> rgInternalMoveLocationsRecordGroup(String agyLocId);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffAllSch);

	OffenderSchedulePersons cgrichkOffenderSchedules(OffenderSchedulePersons paramBean);

	Integer offSchUpdateVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);

	List<VHeaderBlock> checkNa(VHeaderBlock paramBean);

	List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup();

	Integer offSchInsertVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);

	Integer offSchDeleteVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);
	
	Integer checkScheduleConflict(VOffenderAllSchedules objVOffAllSch);
	
	Integer offSchPreInsert();
	
    Integer offSchInternalLocationId(String intLocCode,String agyLocId);
    List<OffenderIndSchedules> getOldRecord(Integer eventId);

	String gettingLovDescription(String code);
	
	public List<OffenderIndSchedules> getNonAssociationScheduleConflicts(final BigDecimal offenderBookID, final Date eventDate, final BigDecimal toAgyLocId);

	public Integer getLocationId(String userDesc, String agyLocId);

}
