package net.syscon.s4.inst.shiftlogs;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLog;
/**
 * Interface OidshlogRepository
 */
public interface OidshlogRepository {
	List<AgencyInternalLocations> defaultAgency(AgencyInternalLocations paramBean);

	StaffMemberRoles agyShilPreUpdate(StaffMemberRoles paramBean);

	List<StaffMembers> cgfkAgyShilStaffIdRecordGroup(String caseloadId);

	Integer agyShilDeleteAgencyShiftLogs(List<AgencyShiftLogs> lstAgyShLogs);

	SystemProfiles agyShilPreUpdate(SystemProfiles paramBean);

	Integer agyShilInsertAgencyShiftLogs(List<AgencyShiftLogs> lstAgyShLogs);

	Integer agyShilUpdateAgencyShiftLogs(List<AgencyShiftLogs> lstAgyShLogs);

	AgencyInternalLocations cgfkchkAgyShilAgyShil(AgencyInternalLocations paramBean);

	List<StaffMembers> agyShilPostQuery(StaffMembers paramBean);

	ReferenceCodes cgfkchkAgyShilAgyShilRef(ReferenceCodes paramBean);

	List<AgencyInternalLocations> cgfkAgyShilDspAgyLocId3RecordGroup(String agyLocId);

	List<AgencyLocations> cgfkAgyShilDspAgyLocId4RecordGroup(String caseloadId);

	StaffMembers cgfkchkAgyShilAgyShilSta(StaffMembers paramBean);

	List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup();

	List<AgencyShiftLogs> agyShilExecuteQuery(AgencyShiftLogs objAgyShLogs);

	 StaffMembers agyShilWhenNewRecordInstance(final String userId);
	 
	 BigDecimal preInsert();
	 
	 BigDecimal internalLocationId(String intLocCode,String agyLocId);
	 
	 String getObservationText(AgencyShiftLogs agyShtLog);
	 
	 BigDecimal preInsertWithoutLock();
	 
	 String getBackDateHours();
	 
	 Integer saveObservationText(AgencyShiftLogs agyShtLog);

	String checkBoxHideData();

	Integer checkBoxShlogData(String userid);
	String getLocationValue(final BigDecimal internalLocationId);
	List<AgencyInternalLocations> getLocationListPostQuery(String agyLocId);

	List<VHeaderBlock> relatedOffendersExcuteQuery(Integer internalLocationId);

	Integer offShilInsertAgencyShiftLogs(List<OffendersShiftLog> lstAgyShift);

	List<OffendersShiftLog> OffendersShiftLogExcuteQuery(BigDecimal shiftLogSeq ,  Long internalLocationId);

	Integer offShilUpdateAgencyShiftLogs(List<OffendersShiftLog> lstAgyShift);

}
