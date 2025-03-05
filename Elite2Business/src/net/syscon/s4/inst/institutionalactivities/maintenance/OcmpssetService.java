package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsCommitBean;

public interface OcmpssetService {

	List<ReferenceCodes> iepLevelRecordGroup();

	List<ProgramPaySettingsBean> progServSettingExecuteQuery();

	Integer prgSrvSettingCommit(ProgramPaySettingsCommitBean commitBean);

	Integer getHours();

	List<ReferenceCodes> acpOutcomeCodesRecordGroup();

	String getProgramServicePayFlag();

	Integer updateSchedulePay(BigDecimal offenderBookId, Integer eventId,String modulename,String userId);

	List<ReferenceCodes> eliteFinancialsRecordGroup();

}
