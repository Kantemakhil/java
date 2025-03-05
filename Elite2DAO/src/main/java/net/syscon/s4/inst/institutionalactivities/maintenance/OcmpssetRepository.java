package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsBean;

public interface OcmpssetRepository {

	List<ReferenceCodes> iepLevelRecordGroup();

	List<ProgramPaySettingsBean> progServSettingExecuteQuery();

	Integer offProgServSetingUpdate(List<ProgramPaySettingsBean> updateList);

	Integer offProgServSetingInsert(List<ProgramPaySettingsBean> insertList);

	Integer getHours();

	List<ReferenceCodes> acpOutcomeCodesRecordGroup();

	String getProgramServicePayFlag();

	List<VOffenderCourseAttendances> getSchedulePayRate(BigDecimal offenderBookId, Integer liReturn,String modulename);

	Integer updateSystemPay(VOffenderCourseAttendances obj);

	List<ReferenceCodes> eliteFinancialsRecordGroup();

}
