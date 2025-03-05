package net.syscon.s4.inst.schedules;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
/**
 * Interface OidstojuRepository
 */
public interface OidstojuRepository {
	List<ReferenceCodes> rgEscortRecordGroup();

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffAllSch);

	List<ReferenceCodes> rgLocationRecordGroup();

	Integer offSchUpdateVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);

	List<OffenderIndSchedules> offBkgOnCheckDeleteMaster(OffenderIndSchedules paramBean);

	Integer offSchInsertVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);

	Integer offSchDeleteVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);
	
	Date getCurrentDate();
	
	Integer offSchPreInsert();
	
	Integer offSchCheckScheduleConflict(VOffenderAllSchedules obj);
	
	Integer offSchInsNotification(OffenderIndSchedules offSch);
	
	Integer offSchUpdNotification(OffenderIndSchedules offSch);
	
	Boolean offSchDelNotification(OffenderIndSchedules offSch);
	
	List<ReferenceCodes> rgEventTypeSubTypeGroup();
}
