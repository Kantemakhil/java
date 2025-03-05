package net.syscon.s4.cm.programsservices;
import java.util.List;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
/**
 * Interface OcumultiRepository
 */
public interface OcumultiRepository {
	List<VOffenderAllSchedules> offBlockExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules) ;

	Integer offBlockInsertVOffenderAllSchedules(List<VOffenderAllSchedules> lstVOffenderAllSchedules) ;

}
