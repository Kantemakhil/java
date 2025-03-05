package net.syscon.s4.inst.shiftlogs;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogsCommitBean;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLog;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLogCommitBean;

/**
 * Interface OidshlogService
 * 
 *
 */
public interface OidshlogService {
	List<StaffMembers> cgfkAgyShilStaffIdRecordGroup(String caseloadId);

	List<AgencyShiftLogs> agyShilCommit(AgencyShiftLogsCommitBean commitBean);

	SystemProfiles agyShilPreUpdate(SystemProfiles paramBean);

	StaffMembers cgfkchkAgyShilAgyShilSta(StaffMembers paramBean);

	List<AgencyInternalLocations> cgfkAgyShilDspAgyLocId3RecordGroup(String agyLocId);

	List<AgencyLocations> cgfkAgyShilDspAgyLocId4RecordGroup(String caseloadId);

	List<StaffMembers> agyShilPostQuery(StaffMembers paramBean);

	List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup();

	List<AgencyInternalLocations> defaultAgency(AgencyInternalLocations paramBean);

	List<AgencyShiftLogs> agyShilExecuteQuery(AgencyShiftLogs objAgyShiftLogs);

	AgencyInternalLocations cgfkchkAgyShilAgyShil(AgencyInternalLocations paramBean);
	
	StaffMembers agyShilWhenNewRecordInstance(String user);
	
	List<AgencyShiftLogs> agyShilWithoutLock(AgencyShiftLogsCommitBean commitBean);
	
	String getBackDateHours();

	String checkBoxHideData();

	Integer checkBoxShlogData(String userid);

	 List<VHeaderBlock> relatedOffendersExcuteQuery(Integer internalLocationId);

	List<OffendersShiftLog> offShilCommit(OffendersShiftLogCommitBean commitBean);

	List<OffendersShiftLog> OffendersShiftLogExcuteQuery(BigDecimal shiftLogSeq, Long internalLocationId);


}
