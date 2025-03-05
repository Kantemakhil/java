package net.syscon.s4.inst.shiftlogs;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
/**
 * Interface OiishlogRepository
 */
public interface OiishlogRepository {
	ReferenceCodes cgfkchkAgyShilAgyShilRef(ReferenceCodes paramBean);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<AgencyLocations> rgAgencyRecordGroup( String caseloadId);

	List<StaffMembers> rgStaffRecordGroup(String caseloadId);

	List<AgencyInternalLocations> rgLocationRecordGroup(String agyLocId);

	Integer agyShilInsertAgencyShiftLogs(List<AgencyShiftLogs> lstAgyShLogs);

	AgencyShiftLogs cgfkchkAgyShilAgyShilAgy(AgencyShiftLogs paramBean);

	List<Object> cgfkchkAgyShilAgyShilc(AgencyInternalLocations paramBean);

	List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup();

	List<AgencyShiftLogs> agyShilExecuteQuery(AgencyShiftLogs objAgyShLogs);

	List<AgencyInternalLocations> cgfklkpAgyShilAgyShilAgy(AgencyInternalLocations paramBean);

	AgencyInternalLocations cgfkchkAgyShilAgyShil(AgencyInternalLocations paramBean);

}
