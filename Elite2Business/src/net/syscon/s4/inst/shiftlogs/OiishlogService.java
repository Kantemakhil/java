package net.syscon.s4.inst.shiftlogs;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogsCommitBean;
/**
 * Interface OiishlogService 
 */
public interface OiishlogService  {
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
	
	Integer agyShilCommit(AgencyShiftLogsCommitBean commitBean);
	
	List<AgencyShiftLogs> agyShil1ExecuteQuery(AgencyShiftLogs searchBean);

}
