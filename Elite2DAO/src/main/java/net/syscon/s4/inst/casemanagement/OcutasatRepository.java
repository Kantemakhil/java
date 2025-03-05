package net.syscon.s4.inst.casemanagement;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Teams;
/**
 * Interface OcutasatRepository 
 */
public interface OcutasatRepository {
	List<ReferenceCodes> rgAreaTypeRecordGroup();

	List<Teams> teamsExecuteQuery(Teams objTeams);

	List<Areas> rgAreaRecordGroup(String areaType);

}
